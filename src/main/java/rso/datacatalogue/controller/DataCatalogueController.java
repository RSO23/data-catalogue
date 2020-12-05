package rso.datacatalogue.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rso.datacatalogue.config.ConfigProperties;

@RestController
@RequiredArgsConstructor
public class DataCatalogueController
{
    private final ConfigProperties configProperties;

    @GetMapping("/ping")
    public String ping() {
        return "Pong pong!";
    }

    @GetMapping("/config")
    public String testConfig() {
        return configProperties.getTestConfig();
    }

    @GetMapping("/info")
    public String info() {
        ClassPathResource resource = new ClassPathResource("/static/info.json");

        String result = "";

        try {
            InputStream inputStream = resource.getInputStream();

            result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return result;
    }
}
