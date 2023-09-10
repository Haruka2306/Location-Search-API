package com.example.crudapi.service;

import com.example.crudapi.entity.Location;

public interface LocationSearchService {
    Location findByCorner(String corner);
}
