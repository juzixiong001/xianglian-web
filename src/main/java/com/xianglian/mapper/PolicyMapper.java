package com.xianglian.mapper;

import com.xianglian.pojo.Policy;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface PolicyMapper {

    @Select("SELECT * FROM policy")
    List<Policy> findAll();

    @Select("SELECT * FROM policy WHERE id = #{id}")
    Policy findById(Integer id);

    @Insert("INSERT INTO policy (title, content, create_time, update_time) VALUES (#{title}, #{content}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Policy policy);

    @Update("UPDATE policy SET title = #{title}, content = #{content}, update_time = NOW() WHERE id = #{id}")
    void update(Policy policy);

    @Delete("DELETE FROM policy WHERE id = #{id}")
    void deleteById(Integer id);

    @Delete("DELETE FROM policy WHERE id IN (#{ids})")
    void deleteBatch(@Param("ids") String ids);


}
