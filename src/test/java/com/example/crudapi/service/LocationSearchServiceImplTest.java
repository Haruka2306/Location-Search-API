package com.example.crudapi.service;


import com.example.crudapi.controller.form.LocationForm;
import com.example.crudapi.dto.LocationDto;
import com.example.crudapi.exception.DuplicateCornerException;
import com.example.crudapi.exception.NoCornerFoundException;
import com.example.crudapi.mapper.LocationSearchMapper;
import org.junit.jupiter.api.Nested;
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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LocationSearchServiceImplTest {

    @InjectMocks
    LocationSearchServiceImpl locationSearchServiceImpl;

    @Mock
    LocationSearchMapper locationSearchMapper;

    @Nested
    class FindByCorner {
        @Test
        public void 存在するcorner名を指定した時にMappernのfindByCornerメソッドが呼び出されること() {
            doReturn(Optional.of(new LocationDto("food", "A", "left-back", "yamada", "2023/08/01"))).when(locationSearchMapper).findByCorner("food");

            LocationDto actual = locationSearchServiceImpl.findByCorner("food");
            assertThat(actual).isEqualTo(new LocationDto("food", "A", "left-back", "yamada", "2023/08/01"));
            verify(locationSearchMapper, times(1)).findByCorner("food");
        }

        @Test
        public void 存在しないcorner名を指定した時にNoCornerFoundExceptionがスローされること() {
            doReturn(Optional.empty()).when(locationSearchMapper).findByCorner("i");

            assertThatThrownBy(() -> locationSearchServiceImpl.findByCorner("i")).isInstanceOfSatisfying(NoCornerFoundException.class, e -> assertThat(e.getMessage()).isEqualTo("No record found for corner"));
            verify(locationSearchMapper, times(1)).findByCorner("i");
        }
    }

    @Nested
    class CreateLocationTest {
        @Test
        public void formから取得した内容でlocationが登録できること() {
            LocationForm form = new LocationForm("game", "G", "right-front", "tanaka", "2023/09/01");
            LocationDto expectedLocation = new LocationDto("game", "G", "right-front", "tanaka", "2023/09/01");
            doNothing().when(locationSearchMapper).insertLocation(expectedLocation);

            assertThat(locationSearchServiceImpl.createLocation(form)).isEqualTo(expectedLocation);
            verify(locationSearchMapper, times(1)).insertLocation(expectedLocation);
        }

        @Test
        public void insertLocationメソッドでDuplicateCornerExceptionがスローされること() {
            LocationDto locationDto = new LocationDto("Duplicate Corner", "G", "right-front", "tanaka", "2023/09/01");
            doThrow(new DuplicateKeyException("Duplicate Corner")).when(locationSearchMapper).insertLocation(locationDto);

            assertThrows(DuplicateCornerException.class, () -> {
                locationSearchServiceImpl.createLocation(new LocationForm("Duplicate Corner", "G", "right-front", "tanaka", "2023/09/01"));
            });
            verify(locationSearchMapper, times(1)).insertLocation(locationDto);
        }
    }

    @Nested
    class UpdateLocationTest {
        @Test
        public void formから取得した内容でLocationを更新できること() {
            doReturn(Optional.of(new LocationForm("toy", "H", "2F-right-front", "suzuki", "2023/09/08")
                    .convertToLocationDto())).when(locationSearchMapper).findByCorner("toy");

            locationSearchServiceImpl.updateLocation(new LocationDto("toy", "H", "2F-right-front", "suzuki", "2023/09/08"));

            verify(locationSearchMapper, times(1)).findByCorner("toy");
            verify(locationSearchMapper, times(1)).updateLocation(new LocationDto("toy", "H", "2F-right-front", "suzuki", "2023/09/08"));
        }

        @Test
        public void 更新対象のcornerが存在しない場合に例外がスローされること() {
            doReturn(Optional.empty()).when(locationSearchMapper).findByCorner("music");

            assertThatThrownBy(() -> locationSearchServiceImpl.updateLocation(new LocationDto("music", "H", "2F-right-front", "suzuki", "2023/09/08")))
                    .isInstanceOfSatisfying(NoCornerFoundException.class, e -> {
                        assertThat(e.getMessage()).isEqualTo("No record found for corner");
                    });
            verify(locationSearchMapper, times(1)).findByCorner("music");
            verify(locationSearchMapper, never()).updateLocation(new LocationDto("music", "H", "2F-right-front", "suzuki", "2023/09/08"));
        }
    }

    @Nested
    class DeleteLocationTest {
        @Test
        public void 存在するcornerのLocationを削除できること() {
            doReturn(Optional.of(new LocationDto("outdoor-product", "E", "center-front", "yamada", "2023/08/01"))).when(locationSearchMapper).findByCorner("outdoor-product");

            locationSearchServiceImpl.deleteLocation("outdoor-product");
            verify(locationSearchMapper, times(1)).findByCorner("outdoor-product");
            verify(locationSearchMapper, times(1)).deleteLocation("outdoor-product");
        }

        @Test
        public void 削除対象のcornerが存在しない場合に例外がスローされること() {
            doReturn(Optional.empty()).when(locationSearchMapper).findByCorner("music");

            assertThatThrownBy(() -> locationSearchServiceImpl.deleteLocation("music"))
                    .isInstanceOfSatisfying(NoCornerFoundException.class, e -> {
                        assertThat(e.getMessage()).isEqualTo("No record found for corner");
                    });
            verify(locationSearchMapper, times(1)).findByCorner("music");
            verify(locationSearchMapper, never()).deleteLocation("music");
        }
    }
}
