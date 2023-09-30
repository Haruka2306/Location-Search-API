package com.example.crudapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("date_created")
    private String dateCreated;

    public LocationDto(String corner, String location_name, String place, String creator, String dateCreated) {
        this.id = 0;
        this.corner = corner;
        this.location_name = location_name;
        this.place = place;
        this.creator = creator;
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationDto that = (LocationDto) o;
        return Objects.equals(corner, that.corner) && Objects.equals(location_name, that.location_name) && Objects.equals(place, that.place) && Objects.equals(creator, that.creator) && Objects.equals(dateCreated, that.dateCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(corner, location_name, place, creator, dateCreated);
    }
}
