package com.example.crudapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class LocationDto {
    private int id;
    private String corner;
    private String location_name;
    private String place;
    private String creator;
    private String date_created;

    public LocationDto(String corner, String location_name, String place, String creator, String date_created) {
        this.id = 0;
        this.corner = corner;
        this.location_name = location_name;
        this.place = place;
        this.creator = creator;
        this.date_created = date_created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationDto that = (LocationDto) o;
        return Objects.equals(corner, that.corner) && Objects.equals(location_name, that.location_name) && Objects.equals(place, that.place) && Objects.equals(creator, that.creator) && Objects.equals(date_created, that.date_created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(corner, location_name, place, creator, date_created);
    }
}
