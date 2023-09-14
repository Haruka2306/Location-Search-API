package com.example.crudapi.mapper;

import com.example.crudapi.entity.Location;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LocationSearchMapperTest {

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
}
