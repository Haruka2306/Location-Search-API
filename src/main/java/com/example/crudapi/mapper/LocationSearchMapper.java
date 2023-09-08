package com.example.crudapi.mapper;

import com.example.crudapi.entity.LocationSearch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LocationSearchMapper {
    @Select("SELECT * FROM locationsearch WHERE corner = #{corner}")
    LocationSearch findByCorner(String corner);
}
