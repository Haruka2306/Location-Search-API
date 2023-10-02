package com.example.crudapi.mapper;

import com.example.crudapi.dto.LocationDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Optional;

@Mapper
public interface LocationSearchMapper {
    @Select("SELECT * FROM locations WHERE corner = #{corner}")
    Optional<LocationDto> findByCorner(String corner);

    @Insert("INSERT INTO locations(corner, location_name, place, created_by, created_date) VALUES(#{corner}, #{locationName}, #{place}, #{createdBy}, #{createdDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertLocation(LocationDto locationDto);

    @Update("UPDATE locations SET location_name = #{locationName}, place = #{place}, created_by = #{createdBy}, created_date = #{createdDate} WHERE corner = #{corner}")
    void updateLocation(LocationDto locationDto);

    @Delete("DELETE FROM locations WHERE corner = #{corner}")
    void deleteLocation(String corner);
}
