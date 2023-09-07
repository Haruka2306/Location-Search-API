package com.example.crudapi.controller;

import com.example.crudapi.controller.response.SubscriberResponse;
import com.example.crudapi.entity.Subscriber;
import com.example.crudapi.service.SubscriberService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class SubscriberController {

    private SubscriberService subscriberService;

    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @GetMapping("/subscribers")
    public SubscriberResponse findByName(@RequestParam("name") @NotBlank String name) {
        Subscriber subscriber = subscriberService.findByName(name);
        return new SubscriberResponse(subscriber.convertToSubscriberDto());
    }
}


