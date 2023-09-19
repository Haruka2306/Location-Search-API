package com.example.crudapi.dto;

import lombok.Getter;

@Getter
public class LocationSearchDto {
    private String corner;
    private String locationName;
    private String place;

    public LocationSearchDto(String corner, String locationName, String place) {
        this.corner = corner;
        this.locationName = locationName;
        this.place = place;
    }
}
