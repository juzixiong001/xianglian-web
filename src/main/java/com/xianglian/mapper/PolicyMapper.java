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

    @Insert("INSERT INTO policy (title, content, type, area, money, conditions, materials, process, contact, create_time, update_time) VALUES (#{title}, #{content}, #{type}, #{area}, #{money}, #{conditions}, #{materials}, #{process}, #{contact}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Policy policy);

    @Update("UPDATE policy SET title = #{title}, content = #{content}, type = #{type}, area = #{area}, money = #{money}, conditions = #{conditions}, materials = #{materials}, process = #{process}, contact = #{contact}, update_time = NOW() WHERE id = #{id}")
    void update(Policy policy);

    @Delete("DELETE FROM policy WHERE id = #{id}")
    void deleteById(Integer id);

    @Delete("DELETE FROM policy WHERE id IN (${ids})")
    void deleteBatch(@Param("ids") String ids);

    List<Policy> searchPolicies(@Param("title") String title, @Param("type") String type, @Param("area") String area, @Param("sort") String sort);


}