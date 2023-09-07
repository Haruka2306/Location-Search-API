package com.example.crudapi.entity;

import com.example.crudapi.dto.SubscriberDto;

public class Subscriber {

    //entityからdtoへの変換
    public SubscriberDto convertToSubscriberDto() {
        SubscriberDto subscriberDto = new SubscriberDto(id, name, dateOfBirth);
        return subscriberDto;
    }

    private int id;
    private String name;
    private String dateOfBirth;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
