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

    @Insert("INSERT INTO locations(corner, locationName, place, creator, dateCreated) VALUES(#{corner}, #{locationName}, #{place}, #{creator}, #{dateCreated})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertLocation(LocationDto locationDto);

    @Update("UPDATE locations SET locationName = #{locationName}, place = #{place}, creator = #{creator}, dateCreated = #{dateCreated} WHERE corner = #{corner}")
    void updateLocation(LocationDto locationDto);

    @Delete("DELETE FROM locations WHERE corner = #{corner}")
    void deleteLocation(String corner);
}
