package com.example.crudapi.service;

import com.example.crudapi.entity.Location;
import com.example.crudapi.exception.NoCornerFoundException;
import com.example.crudapi.mapper.LocationSearchMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationSearchServiceImpl implements LocationSearchService {
    private final LocationSearchMapper locationSearchMapper;

    public LocationSearchServiceImpl(LocationSearchMapper locationSearchMapper) {
        this.locationSearchMapper = locationSearchMapper;
    }

    @Override
    public Location findByCorner(String corner) {
        Optional<Location> locationSearch = locationSearchMapper.findByCorner(corner);
        //該当cornerがDBに無い場合は例外とする
        return locationSearch.orElseThrow(() -> new NoCornerFoundException(0, "No record found for corner"));
    }
}
