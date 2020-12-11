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
import rso.datacatalogue.service.MatchService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/matches", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Basic operations", produces = "application/json", consumes = "application/json")
public class MatchController
{
    private final Logger log = LoggerFactory.getLogger(DataCatalogueController.class);

    private final MatchService matchService;

    @GetMapping("/update/{accountId}")
    public void updateMatches(@PathVariable("accountId") String accountId) {
        log.info("updateMatches called for accountId: " + accountId);
        matchService.updateMatchesByAccountId(accountId);
    }
}
