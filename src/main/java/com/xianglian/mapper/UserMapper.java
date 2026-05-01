package com.xianglian.mapper;

import com.xianglian.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(Long id);

    @Insert("INSERT INTO user (username, password, phone, create_time, update_time) VALUES (#{username}, #{password}, #{phone}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User findByUsernameAndPassword(String username, String password);

    @Select("SELECT * FROM user WHERE phone = #{phone} AND password = #{password}")
    User findByPhoneAndPassword(String phone, String password);

    @Select("SELECT * FROM user WHERE phone = #{phone}")
    User findByPhone(String phone);

    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);

    @Update("UPDATE user SET phone = #{phone}, update_time = NOW() WHERE id = #{id}")
    void update(User user);

    @Select("SELECT COUNT(*) FROM user")
    Integer countAll();

    @Select("SELECT DATE(create_time) as date, COUNT(*) as count FROM user WHERE create_time >= DATE_SUB(NOW(), INTERVAL 30 DAY) GROUP BY DATE(create_time) ORDER BY date")
    java.util.List<java.util.Map<String, Object>> countByDateLast30Days();
}