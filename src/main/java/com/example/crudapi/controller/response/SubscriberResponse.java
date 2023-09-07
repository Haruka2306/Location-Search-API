package com.example.crudapi.controller.response;

import com.example.crudapi.dto.SubscriberDto;

public class SubscriberResponse {
    public SubscriberResponse(SubscriberDto subscriberDto) {
        this.id = subscriberDto.getId();
        this.name = subscriberDto.getName();
        this.dateOfBirth = subscriberDto.getDateOfBirth();
    }

    private int id;
    private String name;
    private String dateOfBirth;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
