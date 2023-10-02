package com.example.crudapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Location {

    private int id;
    private String corner;
    @JsonProperty("location_name")
    private String locationName;
    private String place;
    private String creator;
    @JsonProperty("date_created")
    private String dateCreated;
}
