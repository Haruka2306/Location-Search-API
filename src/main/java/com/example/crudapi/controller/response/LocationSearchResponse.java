package com.example.crudapi.controller.response;

import com.example.crudapi.dto.LocationSearchDto;
import lombok.Getter;

@Getter
public class LocationSearchResponse {

    private String corner;
    private String locationName;
    private String place;

    public LocationSearchResponse(LocationSearchDto locationSearchDto) {
        this.corner = locationSearchDto.getCorner();
        this.locationName = locationSearchDto.getLocationName();
        this.place = locationSearchDto.getPlace();
    }
}
