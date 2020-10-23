package rso.datacatalogue.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataCatalogueController
{
    @GetMapping("/ping")
    public String ping() {
        return "Pong!";
    }
}
