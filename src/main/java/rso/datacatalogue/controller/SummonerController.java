package rso.datacatalogue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import rso.datacatalogue.annotation.ApiPageable;
import rso.datacatalogue.dto.orianna.SummonerDto;
import rso.datacatalogue.service.SummonerService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/summoner", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Basic operations", produces = "application/json", consumes = "application/json")
public class SummonerController
{
    private final Logger log = LoggerFactory.getLogger(SummonerController.class);

    private final SummonerService summonerService;

    @HystrixCommand(fallbackMethod = "getSummonerByUsernameFallback",
                    commandProperties = {
                        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "3"),
                        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "3000")
                    })
    @GetMapping("/{username}")
    public SummonerDto getSummonerByUsername(@PathVariable("username") String username) {
        log.info("getSummonerByUsername called for username: " + username);
        return summonerService.getSummonerByUsername(username);
    }

    @ApiPageable
    @PostMapping("/")
    public Page<SummonerDto> getAllSummoners(@PageableDefault(size = 20)
                                             @SortDefault.SortDefaults({
                                                @SortDefault(sort = "username", direction = Sort.Direction.ASC),
                                                @SortDefault(sort = "accountId", direction = Sort.Direction.ASC)
                                             })
                                             @ApiIgnore Pageable pageable) {
        return summonerService.getAllSummoners(pageable);
    }

    public SummonerDto getSummonerByUsernameFallback(@PathVariable("username") String username) {
        log.info("getSummonerByUsername called for username: " + username);
        return summonerService.getByUsername(username);
    }




}
