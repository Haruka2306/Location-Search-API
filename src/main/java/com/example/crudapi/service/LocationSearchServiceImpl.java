package com.example.crudapi.service;

import com.example.crudapi.controller.form.LocationForm;
import com.example.crudapi.dto.LocationDto;
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
    public LocationDto findByCorner(String corner) {
        Optional<LocationDto> locationSearch = locationSearchMapper.findByCorner(corner);
        //該当cornerがDBに無い場合は例外とする
        return locationSearch.orElseThrow(() -> new NoCornerFoundException("No record found for corner"));
    }

    @Override
    public LocationDto createLocation(LocationForm form) {
        try {
            locationSearchMapper.insertLocation(form.convertToLocationDto());
        } catch (DuplicateKeyException e) {
            throw new DuplicateCornerException(form.convertToLocationDto().getCorner() + " is already created");
        }
        return form.convertToLocationDto();
    }

    @Override
    public void updateLocation(LocationDto locationDto) {
        locationSearchMapper.findByCorner(locationDto.getCorner()).orElseThrow(() -> new NoCornerFoundException("No record found for corner"));
        locationSearchMapper.updateLocation(locationDto);
    }

    @Override
    public void deleteLocation(String corner) {
        locationSearchMapper.findByCorner(corner).orElseThrow(() -> new NoCornerFoundException("No record found for corner"));
        locationSearchMapper.deleteLocation(corner);
    }
}
