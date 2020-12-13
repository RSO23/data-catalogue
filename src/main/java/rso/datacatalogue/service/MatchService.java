package rso.datacatalogue.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rso.datacatalogue.dto.orianna.MatchDto;
import rso.datacatalogue.dto.requests.MatchesRegionDto;
import rso.datacatalogue.dto.riotApi.MatchReferenceDto;
import rso.datacatalogue.entity.Match;
import rso.datacatalogue.feign.RiotApiServiceFeign;
import rso.datacatalogue.mapper.MatchMapper;
import rso.datacatalogue.repository.MatchRepository;

@Service
@RequiredArgsConstructor
public class MatchService
{
    Logger log = LoggerFactory.getLogger(MatchService.class);

    private final RiotApiServiceFeign riotApiServiceFeign;

    private final MatchRepository matchRepository;

    public Page<MatchDto> getPageOfMatchesById(String accountId, Pageable pageable)
    {
        return matchRepository.getPageOfMatchesByAccountId(accountId, pageable)
                .map(MatchMapper::mapToDto);
    }

    public void updateMatchesByAccountId(String accountId)
    {
        try
        {
            getMatchReferencesFromApiAsync(accountId).thenAcceptBoth(getMatchReferencesFromDbAsync(accountId), (matchReferenceDtos, matches) -> {

                Set<Long> ids = matches.stream()
                        .map(Match::getGameId)
                        .collect(Collectors.toSet());

                Map<Long, MatchReferenceDto> diff = matchReferenceDtos.stream()
                        .filter(matchReferenceDto -> !ids.contains(matchReferenceDto.getGameId()))
                        .collect(Collectors.toMap(MatchReferenceDto::getGameId, matchReferenceDto -> matchReferenceDto));

                MatchesRegionDto matchesRegionDto = new MatchesRegionDto();
                matchesRegionDto.setMatchIds(diff.keySet());

                Set<Match> collect = riotApiServiceFeign.getMatchesByIds(matchesRegionDto).stream()
                        .map(matchDto -> {
                            long gameId = matchDto.getGameId();
                            MatchReferenceDto matchReferenceDto = diff.get(gameId);
                            return MatchMapper.mapToEntity(matchReferenceDto, matchDto);
                        })
                        .collect(Collectors.toSet());

                matchRepository.saveAll(collect);

            }).get(120, TimeUnit.SECONDS);
        }
        catch (InterruptedException | ExecutionException | TimeoutException e)
        {
            log.error(e.getLocalizedMessage());
        }
        finally
        {
            log.info("Matches were successfully updated!");
        }
    }

    @Async
    public CompletableFuture<List<MatchReferenceDto>> getMatchReferencesFromApiAsync(String accountId) {
        log.info("getMatchReferencesFromApiAsync called from thread: " + Thread.currentThread().getName());
        return CompletableFuture.completedFuture(
                Arrays.stream(riotApiServiceFeign.getMatchReferencesByAccountId(accountId, 0, 10).getMatches())
                .collect(Collectors.toList())
        );
    }

    @Async
    public CompletableFuture<List<Match>> getMatchReferencesFromDbAsync(String accountId) {
        log.info("getMatchReferencesFromDbAsync called from thread: " + Thread.currentThread().getName());
        return CompletableFuture.completedFuture(matchRepository.getAllByAccountId(accountId));
    }


}
