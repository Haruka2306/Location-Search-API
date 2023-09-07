package com.example.crudapi.service;

import com.example.crudapi.entity.Subscriber;

public interface SubscriberService {
    Subscriber findByName(String name);
}
