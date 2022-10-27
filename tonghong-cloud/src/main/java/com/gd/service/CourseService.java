package com.gd.service;

import com.gd.mapper.CourseMapper;
import com.gd.model.Course;
import com.gd.model.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

/**
 * description: CourseService <br>
 * date: 2022-10-13 10:38 <br>
 * author: DaTao <br>
 * version: 1.0 <br>
 */
@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;


    /**
     * 分页
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public Pages<Course> getPagers(Integer pageIndex,Integer pageSize,String courseName,String teacherName) {


        //起始条数
        Integer recordStart = (pageIndex-1)*pageSize;

        //获取data
        List<Course> data = courseMapper.queryCourse(recordStart,pageSize,courseName,teacherName);

        for(Course course : data){
            if(course.getAttribute().equals("1")){
                course.setAttribute("公开课");
            }else if(course.getAttribute().equals("2")){
                course.setAttribute("标准课");
            }
        }

        int totalRecord = courseMapper.getRecordCount(courseName,teacherName);

        //计算总页数
        Integer totalPage = totalRecord%pageSize == 0 ? totalRecord/pageSize : totalRecord/pageSize + 1;

        //组装page对象
        Pages<Course> pages = new Pages(pageSize,totalPage,totalRecord,pageIndex,data);


        return pages;
    }


    public void add(Course course) {

        courseMapper.add(course);
    }

    public void delete(Integer id) {
        courseMapper.delete(id);
    }

    public void update(Course course) {

        courseMapper.update(course);
    }

    public List<Course> getlist(){
        return courseMapper.getlist();
    };

    public List<Course> getrandlist(int index){return courseMapper.getrandlist(index);};
}
