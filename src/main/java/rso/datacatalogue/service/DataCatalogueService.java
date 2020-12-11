package rso.datacatalogue.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rso.datacatalogue.feign.RiotApiServiceFeign;

@Service
@RequiredArgsConstructor
public class DataCatalogueService {
    Logger log = LoggerFactory.getLogger(DataCatalogueService.class);

    private final RiotApiServiceFeign riotApiServiceFeign;


    public String testFeign() {
        return riotApiServiceFeign.getPing();
    }

    @Async
    public CompletableFuture<String> test() {
        sleep(2);
        return CompletableFuture.completedFuture("Test!");
    }

    private void sleep(int n) {
        try {
            TimeUnit.SECONDS.sleep(n);
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage());
        }
    }


}
