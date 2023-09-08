package com.example.crudapi.controller.response;

import com.example.crudapi.dto.LocationSearchDto;

public class LocationSearchResponse {
    public LocationSearchResponse(LocationSearchDto locationSearchDto) {
        this.corner = locationSearchDto.getCorner();
        this.locationName = locationSearchDto.getLocationName();
        this.place = locationSearchDto.getPlace();
    }

    private String corner;
    private String locationName;
    private String place;


    public String getCorner() {
        return corner;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getPlace() {
        return place;
    }
}
