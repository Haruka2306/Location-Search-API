package com.example.crudapi.controller;

import com.example.crudapi.controller.form.Locationform;
import com.example.crudapi.controller.response.LocationSearchResponse;
import com.example.crudapi.entity.Location;
import com.example.crudapi.service.LocationSearchService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

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

    @PostMapping("/location-search")
    public ResponseEntity<Map<String, String>> createLocation(@RequestBody @Validated Locationform form, UriComponentsBuilder uriBuilder) {
        Location location = locationSearchService.createLocation(form);
        URI url = uriBuilder
                .path("/location-search/" + location.getCreator())
                .build()
                .toUri();
        return ResponseEntity.created(url).body(Map.of("message", "location successfully created"));
    }
}
