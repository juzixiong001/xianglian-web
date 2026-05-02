package com.xianglian.mapper;

import com.xianglian.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT id, username, nickname, avatar, phone, email, create_time, update_time FROM user WHERE id = #{id}")
    User findById(Long id);

    @Insert("INSERT INTO user (username, password, phone, nickname, create_time, update_time) VALUES (#{username}, #{password}, #{phone}, #{nickname}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Select("SELECT id, username, password, nickname, avatar, phone, email, create_time, update_time FROM user WHERE username = #{username}")
    User findByUsername(String username);

    @Select("SELECT id, username, password, nickname, avatar, phone, email, create_time, update_time FROM user WHERE phone = #{phone}")
    User findByPhone(String phone);

    @Update("UPDATE user SET phone = #{phone}, nickname = #{nickname}, avatar = #{avatar}, email = #{email}, password = #{password}, update_time = NOW() WHERE id = #{id}")
    void update(User user);

    @Select("SELECT COUNT(*) FROM user")
    Integer countAll();

    @Select("SELECT DATE(create_time) as date, COUNT(*) as count FROM user WHERE create_time >= DATE_SUB(NOW(), INTERVAL 30 DAY) GROUP BY DATE(create_time) ORDER BY date")
    java.util.List<java.util.Map<String, Object>> countByDateLast30Days();
}