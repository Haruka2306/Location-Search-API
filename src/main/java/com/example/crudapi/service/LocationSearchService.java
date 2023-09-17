package com.example.crudapi.service;

import com.example.crudapi.controller.form.Locationform;
import com.example.crudapi.entity.Location;

public interface LocationSearchService {
    Location findByCorner(String corner);

    Location createLocation(Locationform form);
}
