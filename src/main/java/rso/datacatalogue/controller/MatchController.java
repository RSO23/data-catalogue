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

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import rso.datacatalogue.annotation.ApiPageable;
import rso.datacatalogue.dto.orianna.MatchDto;
import rso.datacatalogue.service.MatchService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/matches", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Basic operations", produces = "application/json", consumes = "application/json")
public class MatchController
{
    private final Logger log = LoggerFactory.getLogger(MatchController.class);

    private final MatchService matchService;

    @GetMapping("/update/{accountId}")
    public void updateMatches(@PathVariable("accountId") String accountId) {
        log.info("updateMatches called for accountId: " + accountId);
        matchService.updateMatchesByAccountId(accountId);
    }

    @ApiPageable
    @PostMapping("/{accountId}")
    public Page<MatchDto> getByAccountId(@PathVariable("accountId") String accountId,
                                      @PageableDefault(size = 20)
                                      @SortDefault.SortDefaults({
                                        @SortDefault(sort = "gameId", direction = Sort.Direction.ASC)
                                      })
                                      @ApiIgnore Pageable pageable) {
        log.info("getByAccountId called for accountId: " + accountId);
        return matchService.getPageOfMatchesById(accountId, pageable);
    }
}
