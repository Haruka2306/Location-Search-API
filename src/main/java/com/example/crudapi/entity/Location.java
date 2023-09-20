package com.example.crudapi.entity;

import com.example.crudapi.dto.LocationDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {

    private int id;
    private String corner;
    private String locationName;
    private String place;
    private String creator;

    public Location(int id, String corner, String locationName, String place, String creator) {
        this.id = id;
        this.corner = corner;
        this.locationName = locationName;
        this.place = place;
        this.creator = creator;
    }

    //entityからdtoへの変換
    public LocationDto convertToLocationSearchDto() {
        LocationDto locationDto = new LocationDto(corner, locationName, place, creator);
        return locationDto;
    }
}
