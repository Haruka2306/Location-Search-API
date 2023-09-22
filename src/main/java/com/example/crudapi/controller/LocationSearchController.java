package com.example.crudapi.controller;

import com.example.crudapi.controller.form.LocationForm;
import com.example.crudapi.controller.response.LocationResponse;
import com.example.crudapi.dto.LocationDto;
import com.example.crudapi.service.LocationSearchService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/locations/{corner}")
    public LocationResponse findByCorner(@PathVariable("corner") @NotBlank String corner) {
        LocationDto locationDto = locationSearchService.findByCorner(corner);
        return new LocationResponse(locationDto);
    }

    @PostMapping("/locations")
    public ResponseEntity<Map<String, String>> createLocation(@RequestBody @Validated LocationForm form, UriComponentsBuilder uriBuilder) {
        LocationDto locationDto = locationSearchService.createLocation(form);
        URI url = uriBuilder
                .path("/locations/" + locationDto.getCorner())
                .build()
                .toUri();
        return ResponseEntity.created(url).body(Map.of("message", "location successfully created"));
    }

    @PatchMapping("/locations/{corner}")
    public ResponseEntity<Map<String, String>> updateLocation(@PathVariable("corner") String corner, @RequestBody @Validated LocationForm form) {
        locationSearchService.updateLocation(form.convertToLocationDto());
        return ResponseEntity.ok(Map.of("message", "location successfully updated"));
    }
}
