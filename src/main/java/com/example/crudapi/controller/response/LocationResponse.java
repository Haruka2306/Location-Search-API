package com.example.crudapi.controller.response;

import com.example.crudapi.dto.LocationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LocationResponse {

    private String corner;
    private String locationName;
    private String place;
    private String creator;

    public LocationResponse(LocationDto locationDto) {
        this.corner = locationDto.getCorner();
        this.locationName = locationDto.getLocationName();
        this.place = locationDto.getPlace();
        this.creator = locationDto.getCreator();
    }
}
