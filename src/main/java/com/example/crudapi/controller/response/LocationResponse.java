package com.example.crudapi.controller.response;

import com.example.crudapi.dto.LocationDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LocationResponse {

    private String corner;
    @JsonProperty("location_name")
    private String locationName;
    private String place;
    private String creator;
    @JsonProperty("date_created")
    private String dateCreated;

    public LocationResponse(LocationDto locationDto) {
        this.corner = locationDto.getCorner();
        this.locationName = locationDto.getLocationName();
        this.place = locationDto.getPlace();
        this.creator = locationDto.getCreator();
        this.dateCreated = locationDto.getDateCreated();
    }
}
