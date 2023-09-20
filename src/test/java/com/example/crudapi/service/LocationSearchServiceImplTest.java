package com.example.crudapi.service;


import com.example.crudapi.controller.form.LocationForm;
import com.example.crudapi.dto.LocationDto;
import com.example.crudapi.exception.DuplicateCornerException;
import com.example.crudapi.exception.NoCornerFoundException;
import com.example.crudapi.mapper.LocationSearchMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LocationSearchServiceImplTest {

    @InjectMocks
    LocationSearchServiceImpl locationSearchServiceImpl;

    @Mock
    LocationSearchMapper locationSearchMapper;

    @Test
    public void 存在するcorner名を指定した時にMappernのfindByCornerメソッドが呼び出されること() {
        doReturn(Optional.of(new LocationDto("food", "A", "left-back", "yamada"))).when(locationSearchMapper).findByCorner("food");

        LocationDto actual = locationSearchServiceImpl.findByCorner("food");
        assertThat(actual).isEqualTo(new LocationDto("food", "A", "left-back", "yamada"));
        verify(locationSearchMapper, times(1)).findByCorner("food");
    }

    @Test
    public void 存在しないcorner名を指定した時にNoCornerFoundExceptionがスローされること() {
        doReturn(Optional.empty()).when(locationSearchMapper).findByCorner("i");

        assertThatThrownBy(() -> locationSearchServiceImpl.findByCorner("i")).isInstanceOfSatisfying(NoCornerFoundException.class, e -> assertThat(e.getMessage()).isEqualTo("No record found for corner"));
        verify(locationSearchMapper, times(1)).findByCorner("i");
    }

    @Test
    public void formから取得した内容でlocationが登録できること() {
        LocationForm form = new LocationForm("game", "G", "right-front", "tanaka");
        LocationDto expectedLocation = new LocationDto("game", "G", "right-front", "tanaka");
        doNothing().when(locationSearchMapper).insertLocation(expectedLocation);

        assertThat(locationSearchServiceImpl.createLocation(form)).isEqualTo(expectedLocation);
        verify(locationSearchMapper, times(1)).insertLocation(expectedLocation);
    }

    @Test
    public void insertLocationメソッドでDuplicateCornerExceptionがスローされること() {
        LocationDto locationDto = new LocationDto("Duplicate Corner", "G", "right-front", "tanaka");
        doThrow(new DuplicateKeyException("Duplicate Corner")).when(locationSearchMapper).insertLocation(locationDto);

        assertThrows(DuplicateCornerException.class, () -> {
            locationSearchServiceImpl.createLocation(new LocationForm("Duplicate Corner", "G", "right-front", "tanaka"));
        });
        verify(locationSearchMapper, times(1)).insertLocation(locationDto);
    }
}
