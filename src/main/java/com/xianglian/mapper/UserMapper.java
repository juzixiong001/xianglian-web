package com.xianglian.mapper;

import com.xianglian.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(Integer id);
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);
    @Insert("INSERT INTO user (username, password, nickname, create_time, update_time) VALUES (#{username}, #{password}, #{nickname}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);
    @Update("UPDATE user SET nickname = #{nickname}, avatar = #{avatar}, phone = #{phone}, email = #{email}, update_time = NOW() WHERE id = #{id}")
    void update(User user);

}