package com.gd.mapper;

import com.gd.model.Course;
import com.gd.model.Customer;
import com.gd.model.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * description: CustomerMapper <br>
 * date: 2022-10-14 11:35 <br>
 * author: DaTao <br>
 * version: 1.0 <br>
 */
@Mapper
public interface TeacherMapper {

    @Select({"select * from teacher LIMIT #{recordStart},#{pageSize} "})
    List<Teacher> queryCourse(@Param("recordStart") Integer recordStart,@Param("pageSize") Integer pageSize);

    @Select({"SELECT count(*) FROM teacher"})
    int getRecordCount();

    @Insert("insert into teacher(name,phone,mail,level,photo) value(#{teacher.name},#{teacher.phone},#{teacher.mail},#{teacher.level},#{teacher.photo}) ")
    void add(@Param("teacher") Teacher teacher);

    @Delete("delete from teacher where id=#{id}")
    void delete(@Param("id") Integer id);

    @Update("update teacher set name=#{teacher.name},phone=#{teacher.phone}," +
            "mail=#{teacher.mail},level=#{teacher.level},photo=#{teacher.photo} where id=#{teacher.id}")
    void update(@Param("teacher") Teacher teacher);

    @Select("select * from teacher")
    List<Teacher> getlist();

}
