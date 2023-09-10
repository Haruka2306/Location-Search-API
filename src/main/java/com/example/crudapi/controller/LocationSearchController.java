package com.example.crudapi.controller;

import com.example.crudapi.controller.response.LocationSearchResponse;
import com.example.crudapi.entity.Location;
import com.example.crudapi.service.LocationSearchService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class LocationSearchController {

    private LocationSearchService locationSearchService;

    public LocationSearchController(LocationSearchService locationSearchService) {
        this.locationSearchService = locationSearchService;
    }

    @GetMapping("/location-search")
    public LocationSearchResponse findByCorner(@RequestParam("corner") @NotBlank String corner) {
        Location location = locationSearchService.findByCorner(corner);
        return new LocationSearchResponse(location.convertToLocationSearchDto());
    }
}


