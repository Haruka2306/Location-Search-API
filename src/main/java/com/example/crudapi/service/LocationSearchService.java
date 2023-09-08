package com.example.crudapi.service;

import com.example.crudapi.entity.LocationSearch;

public interface LocationSearchService {
    LocationSearch findByCorner(String corner);
}
