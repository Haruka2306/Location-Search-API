package com.example.crudapi.service;

import com.example.crudapi.controller.form.LocationForm;
import com.example.crudapi.dto.LocationDto;

public interface LocationSearchService {
    LocationDto findByCorner(String corner);

    LocationDto createLocation(LocationForm form);

    void updateLocation(LocationForm form);
}
