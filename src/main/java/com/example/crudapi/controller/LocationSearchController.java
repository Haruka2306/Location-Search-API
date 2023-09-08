package com.example.crudapi.controller;

import com.example.crudapi.controller.response.LocationSearchResponse;
import com.example.crudapi.entity.LocationSearch;
import com.example.crudapi.exception.CustomExceptionHandler;
import com.example.crudapi.service.LocationSearchService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@Validated
public class LocationSearchController {

    private LocationSearchService locationSearchService;

    public LocationSearchController(LocationSearchService locationSearchService) {
        this.locationSearchService = locationSearchService;
    }

    @GetMapping("/location-search")
    public LocationSearchResponse findByCorner(@RequestParam("corner") @NotBlank String corner) {
        LocationSearch locationSearch = locationSearchService.findByCorner(corner);

        //該当cornerがDBに無い場合は例外とする
        if (Objects.isNull(locationSearch)) {
            throw new CustomExceptionHandler(0, "No record found for corner");
        }
        return new LocationSearchResponse(locationSearch.convertToLocationSearchDto());
    }
}


