package com.example.crudapi.mapper;

import com.example.crudapi.entity.Location;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface LocationSearchMapper {
    @Select("SELECT * FROM location WHERE corner = #{corner}")
    Optional<Location> findByCorner(String corner);
}
