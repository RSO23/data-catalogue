package rso.datacatalogue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import rso.datacatalogue.dto.orianna.SummonerDto;
import rso.datacatalogue.service.SummonerService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Basic operations", produces = "application/json", consumes = "application/json")
public class SummonerController
{
    private final Logger log = LoggerFactory.getLogger(SummonerController.class);

    private final SummonerService summonerService;

    @GetMapping("/summoner/{username}")
    public SummonerDto getSummonerByUsername(@PathVariable("username") String username) {
        log.info("getSummonerByUsername called for username: " + username);
        return summonerService.getSummonerByUsername(username);
    }


}
