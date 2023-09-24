package com.example.crudapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Location {

    private int id;
    private String corner;
    private String location_name;
    private String place;
    private String creator;
    private String date_created;
}
