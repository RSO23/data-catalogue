package rso.datacatalogue.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rso.datacatalogue.dto.orianna.SummonerDto;
import rso.datacatalogue.dto.requests.UsernameRegionDto;
import rso.datacatalogue.entity.Summoner;
import rso.datacatalogue.feign.RiotApiServiceFeign;
import rso.datacatalogue.mapper.SummonerMapper;
import rso.datacatalogue.repository.SummonerRepository;

@Service
@RequiredArgsConstructor
public class SummonerService
{
    private final Logger log = LoggerFactory.getLogger(SummonerService.class);

    private final RiotApiServiceFeign riotApiServiceFeign;

    private final SummonerRepository summonerRepository;

    public SummonerDto getSummonerByUsername(String username) {

        UsernameRegionDto usernameRegionDto = new UsernameRegionDto();
        usernameRegionDto.setUsername(username);

        SummonerDto summonerDto = riotApiServiceFeign.getSummonerByUsername(usernameRegionDto);
        saveOrUpdateSummonerAsync(SummonerMapper.mapToEntity(summonerDto));

        return summonerDto;

    }

    @Async
    void saveOrUpdateSummonerAsync(Summoner summoner) {
        summonerRepository.findById(summoner.getId())
                .ifPresentOrElse(existingSummoner -> summonerRepository
                                .save(SummonerMapper.updateEntity(existingSummoner, summoner)),
                        () -> summonerRepository.save(summoner));
    }
}
