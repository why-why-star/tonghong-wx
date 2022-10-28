package com.gd.controller;

import com.gd.model.Course;
import com.gd.model.Pages;
import com.gd.model.Response;
import com.gd.model.Teacher;
import com.gd.service.CourseService;
import com.gd.service.CustomerService;
import com.gd.service.TeacherService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: CourseController <br>
 * date: 2022-10-13 9:54 <br>
 * author: DaTao <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("/Wxdata")
public class Wxdata {


    @Resource
    private TeacherService teacherService;

    @Resource
    private CourseService courseService;

    @Resource
    private CustomerService customerService;

    @RequestMapping("/index")
    public Map<String, Object> index() {
        Map<String, Object> map = new HashMap<>();
        map.put("kc", courseService.getPagers(1, 10000,"",""));
        map.put("bj", teacherService.getlist());
        map.put("cp", "");
        map.put("px", ")");
        map.put("zq", "");
        return map;
    }
    @RequestMapping("/course")
    public Map<String, Object> course() {
        Map<String, Object> map = new HashMap<>();
        map.put("kc", courseService.getPagers(1, 10000,"",""));
        return map;
    }

    @RequestMapping("/getmessagebynickname")
    public Map<String, Object> getmessagebynickname(String nickName) {
        int userid =  customerService.getidbynickname(nickName);
        Map<String, Object> map = new HashMap<>();
        map.put("messagelist", customerService.getmessage(userid));
        return map;
    }








}
