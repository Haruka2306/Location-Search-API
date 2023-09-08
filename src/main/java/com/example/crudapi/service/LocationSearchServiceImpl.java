package com.example.crudapi.service;

import com.example.crudapi.entity.LocationSearch;
import com.example.crudapi.mapper.LocationSearchMapper;
import org.springframework.stereotype.Service;

@Service
public class LocationSearchServiceImpl implements LocationSearchService {
    private final LocationSearchMapper locationSearchMapper;

    public LocationSearchServiceImpl(LocationSearchMapper locationSearchMapper) {
        this.locationSearchMapper = locationSearchMapper;
    }

    @Override
    public LocationSearch findByCorner(String corner) {
        return locationSearchMapper.findByCorner(corner);
    }
}
