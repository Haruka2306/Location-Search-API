package com.example.crudapi.mapper;

import com.example.crudapi.entity.Subscriber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SubscriberMapper {
    @Select("SELECT * FROM subscribers WHERE name = #{name}")
    Subscriber findByName(String name);
}
