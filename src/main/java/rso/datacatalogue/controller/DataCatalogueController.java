package rso.datacatalogue.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import rso.datacatalogue.config.ConfigProperties;
import rso.datacatalogue.service.DataCatalogueService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Basic operations", produces = "application/json", consumes = "application/json")
public class DataCatalogueController
{
    private final Logger log = LoggerFactory.getLogger(DataCatalogueController.class);

    private final ConfigProperties configProperties;

    private final DataCatalogueService dataCatalogueService;

    @GetMapping("/ping")
    public String ping() {
        log.info("Ping!");
        return "Pong pong!";
    }

    @GetMapping("/config")
    public String testConfig() {
        return configProperties.getTestConfig();
    }

    @GetMapping(value = "/info", produces = "application/json")
    public String info() {
        ClassPathResource resource = new ClassPathResource("/static/info.json");

        String result = "";

        try {
            InputStream inputStream = resource.getInputStream();

            result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

        }
        catch (IOException e)
        {
            log.error(e.getMessage());
        }

        return result;
    }

    @GetMapping("/testFeign")
    public String testFeign() {
        return dataCatalogueService.testFeign();
    }

    @GetMapping("/test")
    @Async
    public String test() {
        log.info(LocalDateTime.now().toString());
        CompletableFuture<String> test = dataCatalogueService.test();
        try
        {
            return test.get();
        }
        catch (InterruptedException | ExecutionException e)
        {
            log.error(e.getLocalizedMessage());
        }
        return "Nope!";
    }
}
