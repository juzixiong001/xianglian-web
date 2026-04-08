package com.xianglian.mapper;

import com.xianglian.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(Integer id);

    @Update("UPDATE user SET nickname = #{nickname}, avatar = #{avatar}, phone = #{phone}, email = #{email}, update_time = NOW() WHERE id = #{id}")
    void update(User user);
}