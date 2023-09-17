package com.example.crudapi.mapper;

import com.example.crudapi.entity.Location;
import com.example.crudapi.exception.DuplicateCornerException;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
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
    @DataSet(value = "datasets/location.yml")
    @Transactional
    void 指定したcorner名のlocationが取得できること() {
        assertThat(locationSearchMapper.findByCorner("food")).contains(
                new Location("food", "A", "left-back", "yamada")
        );
    }

    @Test
    @DataSet(value = "datasets/location.yml")
    @Transactional
    void 指定したcorner名が存在しないときにOptionalが返されること() {
        assertThat(locationSearchMapper.findByCorner("i")).isEmpty();
    }

    @Test
    @DataSet(value = "datasets/location.yml")
    @ExpectedDataSet(value = "datasets/insert_location.yml", ignoreCols = "corner")
    @Transactional
    void 新規のcornerを登録できること() {
        Location location = new Location("game", "G", "right-front", "tanaka");
        locationSearchMapper.insertLocation(location);
    }

    @Test
    @DataSet(value = "datasets/location.yml")
    @Transactional
    void 既に登録されているcorner名が新規登録で渡された場合にDuplicateCornerExceptionにスローされること() {
        Location location = new Location("toy", "G", "right-front", "tanaka");
        assertThrows(DuplicateCornerException.class, () -> locationSearchMapper.insertLocation(location));
    }
}
