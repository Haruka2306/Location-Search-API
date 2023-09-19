package com.example.crudapi.mapper;

import com.example.crudapi.entity.Location;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface LocationSearchMapper {
    @Select("SELECT * FROM locations WHERE corner = #{corner}")
    Optional<Location> findByCorner(String corner);

    @Insert("INSERT INTO locations(corner, locationName, place, creator) VALUES(#{corner}, #{locationName}, #{place}, #{creator})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertLocation(Location location);
}
