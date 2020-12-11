package rso.datacatalogue.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import rso.datacatalogue.dto.orianna.MatchDto;
import rso.datacatalogue.dto.orianna.SummonerDto;
import rso.datacatalogue.dto.requests.MatchesRegionDto;
import rso.datacatalogue.dto.requests.UsernameRegionDto;
import rso.datacatalogue.dto.riotApi.MatchlistDto;

@FeignClient(name = "riot-api")
public interface RiotApiServiceFeign
{
    @GetMapping(value = "/ping")
    String getPing();

    @PostMapping("/summoner")
    SummonerDto getSummonerByUsername(@RequestBody UsernameRegionDto usernameRegionDto);

    @GetMapping("/matches/references/{accountId}")
    MatchlistDto getMatchReferencesByAccountId(@PathVariable String accountId);

    @PostMapping("/matches")
    List<MatchDto> getMatchesByIds(@RequestBody MatchesRegionDto matchesRegionDto);
}
