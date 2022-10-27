package com.gd.mapper;

import com.gd.model.Candition;
import com.gd.model.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * description: CourseMapper <br>
 * date: 2022-10-13 10:43 <br>
 * author: DaTao <br>
 * version: 1.0 <br>
 */
@Mapper
public interface CourseMapper {


    @Select({"<script> SELECT c.id,c.name,c.teacher_id,t.name teacherName,c.attribute,c.face_img,c.ks,c.add_time " +
            "FROM course c left JOIN teacher t ON c.teacher_id=t.id " +
            "<where>" +
            "<if test='courseName != null'> and c.name like '%${courseName}%' </if> " +
            "<if test='teacherName != null'> and t.name like '%${teacherName}%' </if> " +
            "</where>" +
            "LIMIT #{recordStart},#{pageSize} "+
            "</script>"})
    List<Course> queryCourse(@Param("recordStart") Integer recordStart,
                             @Param("pageSize") Integer pageSize,
                             @Param("courseName") String courseName,
                             @Param("teacherName") String teacherName);

    @Select({"<script>  SELECT count(*) FROM course c left JOIN teacher t ON c.teacher_id=t.id "+
            "<where>" +
            "<if test='courseName != null'> and c.name like '%${courseName}%' </if> " +
            "<if test='teacherName != null'> and t.name like '%${teacherName}%' </if> " +
            "</where> " +
            "</script>"})
    int getRecordCount(
            @Param("courseName") String courseName,
            @Param("teacherName") String teacherName
        );


    //insert into course set name=#{course.name}," +
    //            "teacher_id=#{course.teacherId},attribute=#{course.attribute},face_img=#{course.faceImg}
    @Insert("insert into course(name,teacher_id,attribute,ks,face_img) value(#{course.name},#{course.teacherId},#{course.attribute},#{course.ks},#{course.faceImg}) ")
    void add(@Param("course") Course course);

    @Delete("delete from course where id=#{id}")
    void delete(@Param("id") Integer id);

    @Update("update course set name=#{course.name},teacher_id=#{course.teacherId}," +
            "attribute=#{course.attribute},ks=#{course.ks},face_img=#{course.faceImg} where id=#{course.id}")
    void update(@Param("course") Course course);

    @Select("select * from course")
    List<Course> getlist();


    @Select("SELECT * FROM course ORDER BY RAND() LIMIT #{index}")
    List<Course> getrandlist(@Param("index") Integer index);

}
