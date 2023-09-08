package com.example.crudapi.dto;

public class LocationSearchDto {
    private String corner;
    private String locationName;
    private String place;

    public LocationSearchDto(String corner, String locationName, String place) {
        this.corner = corner;
        this.locationName = locationName;
        this.place = place;
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
}
