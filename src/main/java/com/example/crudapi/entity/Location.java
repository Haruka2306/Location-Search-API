package com.example.crudapi.entity;

import com.example.crudapi.dto.LocationSearchDto;

import java.util.Objects;

public class Location {

    private String corner;
    private String locationName;
    private String place;
    private String creator;

    //entityからdtoへの変換
    public LocationSearchDto convertToLocationSearchDto() {
        LocationSearchDto locationSearchDto = new LocationSearchDto(corner, locationName, place);
        return locationSearchDto;
    }

    public Location(String corner, String locationName, String place, String creator) {
        this.corner = corner;
        this.locationName = locationName;
        this.place = place;
        this.creator = creator;
    }

    public String getCorner() {
        return corner;
    }

    public void setCorner(String corner) {
        this.corner = corner;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Location)) {
            return false;
        }
        Location location = (Location) o;
        return corner == location.corner
                && Objects.equals(locationName, location.locationName)
                && Objects.equals(place, location.place)
                && Objects.equals(creator, location.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(corner, locationName, place, creator);
    }
}
