package com.example.crudapi.service;

import com.example.crudapi.controller.form.Locationform;
import com.example.crudapi.entity.Location;
import com.example.crudapi.exception.DuplicateCornerException;
import com.example.crudapi.exception.NoCornerFoundException;
import com.example.crudapi.mapper.LocationSearchMapper;
import org.springframework.dao.DuplicateKeyException;
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

    @Override
    public Location createLocation(Locationform form) {
        try {
            locationSearchMapper.insertLocation(form.convertToLocation());
        } catch (DuplicateKeyException e) {
            throw new DuplicateCornerException(form.convertToLocation().getCorner() + " is already created");
        }
        return form.convertToLocation();
    }
}
