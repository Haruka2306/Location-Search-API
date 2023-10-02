package com.example.crudapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class LocationDto {
    private int id;
    private String corner;
    @JsonProperty("location_name")
    private String locationName;
    private String place;
    @JsonProperty("created_by")
    private String createdBy;
    @JsonProperty("created_date")
    private String createdDate;

    public LocationDto(String corner, String locationName, String place, String createdBy, String createdDate) {
        this.id = 0;
        this.corner = corner;
        this.locationName = locationName;
        this.place = place;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationDto that = (LocationDto) o;
        return Objects.equals(corner, that.corner) && Objects.equals(locationName, that.locationName) && Objects.equals(place, that.place) && Objects.equals(createdBy, that.createdBy) && Objects.equals(createdDate, that.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(corner, locationName, place, createdBy, createdDate);
    }
}
