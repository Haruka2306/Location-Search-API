package com.example.crudapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class LocationDto {
    private int id;
    private String corner;
    private String locationName;
    private String place;
    private String creator;

    public LocationDto(String corner, String locationName, String place, String creator) {
        this.id = 0;
        this.corner = corner;
        this.locationName = locationName;
        this.place = place;
        this.creator = creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationDto that = (LocationDto) o;
        return Objects.equals(corner, that.corner) && Objects.equals(locationName, that.locationName) && Objects.equals(place, that.place) && Objects.equals(creator, that.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(corner, locationName, place, creator);
    }
}
