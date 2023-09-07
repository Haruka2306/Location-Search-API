package com.example.crudapi.service;

import com.example.crudapi.entity.Subscriber;
import com.example.crudapi.mapper.SubscriberMapper;
import org.springframework.stereotype.Service;

@Service
public class SubscriberServiceImpl implements SubscriberService {
    private final SubscriberMapper subscriberMapper;

    public SubscriberServiceImpl(SubscriberMapper subscriberMapper) {
        this.subscriberMapper = subscriberMapper;
    }

    @Override
    public Subscriber findByName(String name) {
        return subscriberMapper.findByName(name);
    }
}
