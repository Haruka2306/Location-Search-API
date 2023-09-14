package com.example.crudapi.service;


import com.example.crudapi.entity.Location;
import com.example.crudapi.exception.NoCornerFoundException;
import com.example.crudapi.mapper.LocationSearchMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
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
        doReturn(Optional.of(new Location("food", "A", "left-back", "yamada"))).when(locationSearchMapper).findByCorner("food");

        Location actual = locationSearchServiceImpl.findByCorner("food");
        assertThat(actual).isEqualTo(new Location("food", "A", "left-back", "yamada"));
        verify(locationSearchMapper, times(1)).findByCorner("food");
    }

    @Test
    public void 存在しないcorner名を指定した時にNoCornerFoundExceptionがスローされること() {
        doReturn(Optional.empty()).when(locationSearchMapper).findByCorner("i");

        assertThatThrownBy(() -> locationSearchServiceImpl.findByCorner("i")).isInstanceOfSatisfying(NoCornerFoundException.class, e -> assertThat(e.getMessage()).isEqualTo("No record found for corner"));
        verify(locationSearchMapper, times(1)).findByCorner("i");
    }
}
