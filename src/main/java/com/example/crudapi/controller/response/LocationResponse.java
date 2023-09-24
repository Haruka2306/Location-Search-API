package com.example.crudapi.controller.response;

import com.example.crudapi.dto.LocationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LocationResponse {

    private String corner;
    private String location_name;
    private String place;
    private String creator;
    private String date_created;

    public LocationResponse(LocationDto locationDto) {
        this.corner = locationDto.getCorner();
        this.location_name = locationDto.getLocation_name();
        this.place = locationDto.getPlace();
        this.creator = locationDto.getCreator();
        this.date_created = locationDto.getDate_created();
    }
}
