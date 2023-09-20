package com.example.crudapi.mapper;

import com.example.crudapi.dto.LocationDto;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LocationSearchMapperTest {

    @Autowired
    LocationSearchMapper locationSearchMapper;

    @Test
    @DataSet(value = "datasets/locations.yml")
    @Transactional
    void 指定したcorner名のlocationが取得できること() {
        assertThat(locationSearchMapper.findByCorner("food")).contains(
                new LocationDto("food", "A", "left-back", "yamada")
        );
    }

    @Test
    @DataSet(value = "datasets/locations.yml")
    @Transactional
    void 指定したcorner名が存在しないときにOptionalが返されること() {
        assertThat(locationSearchMapper.findByCorner("i")).isEmpty();
    }

    @Test
    @DataSet(value = "datasets/locations.yml")
    @ExpectedDataSet(value = "datasets/insert_location.yml", ignoreCols = "corner")
    @Transactional
    void 新規のcornerを登録できること() {
        LocationDto locationDto = new LocationDto("game", "G", "right-front", "tanaka");
        locationSearchMapper.insertLocation(locationDto);
    }

    @Test
    @DataSet(value = "datasets/locations.yml")
    @Transactional
    void 既に登録されているcorner名が新規登録で渡された場合にDuplicateKeyExceptionにスローされること() {
        LocationDto locationDto = new LocationDto("toy", "G", "right-front", "tanaka");
        assertThrows(DuplicateKeyException.class, () -> locationSearchMapper.insertLocation(locationDto));
    }
}
