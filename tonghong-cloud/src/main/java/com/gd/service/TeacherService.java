package com.gd.service;

import com.gd.mapper.CustomerMapper;
import com.gd.mapper.TeacherMapper;
import com.gd.model.Course;
import com.gd.model.Customer;
import com.gd.model.Pages;
import com.gd.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * description: CustomerService <br>
 * date: 2022-10-14 11:34 <br>
 * author: DaTao <br>
 * version: 1.0 <br>
 */
@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @RequestMapping(value="/getPage")
    public Pages<Teacher> getPage(Integer pageIndex,Integer pageSize){
        //起始条数
        Integer recordStart = (pageIndex-1)*pageSize;

        //获取data
        List<Teacher> data = teacherMapper.queryCourse(recordStart,pageSize);


        int totalRecord = teacherMapper.getRecordCount();

        //计算总页数
        Integer totalPage = totalRecord%pageSize == 0 ? totalRecord/pageSize : totalRecord/pageSize + 1;

        //组装page对象
        Pages<Teacher> pages = new Pages(pageSize,totalPage,totalRecord,pageIndex,data);


        return pages;
    }

    public void add(Teacher teacher) {

        teacherMapper.add(teacher);
    }

    public void delete(Integer id) {
        teacherMapper.delete(id);
    }

    public void update(Teacher teacher) {

        teacherMapper.update(teacher);
    }

    public List<Teacher> getlist(){
        return teacherMapper.getlist();
    };
}
