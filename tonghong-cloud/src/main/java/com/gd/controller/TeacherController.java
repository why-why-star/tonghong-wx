package com.gd.controller;

import com.gd.model.Course;
import com.gd.model.Pages;
import com.gd.model.Response;
import com.gd.model.Teacher;
import com.gd.service.CourseService;
import com.gd.service.TeacherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * description: CourseController <br>
 * date: 2022-10-13 9:54 <br>
 * author: DaTao <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    private TeacherService teacherService;

    //物理路径
    @Value("${file.upload.path}")
    private String filePath;

    /**显示虚拟地址*/
    @Value("${file.upload.relative}")
    private String fileRelativePath;


    @GetMapping("/getPage")
    public Pages<Teacher> getPage(Integer pageIndex, Integer pageSize){
        System.out.println("???");
        Pages<Teacher> pagers = teacherService.getPage(pageIndex, pageSize);
        System.out.println(pagers);
        return pagers;
    }

    /**
     * 保存课程
     //     * @param teacher
     */
    @RequestMapping("/add")
    public String add(String name,String phone,String mail,String level,String photo){

        System.out.println("正在添加教师"+name);
        teacherService.add(new Teacher(name,phone,mail,level,photo));

        return "success";
    }

    /**
     * 上传课程封面
     * @param photo
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/upload",method = RequestMethod.POST)
    public Response upload(MultipartFile photo) throws IOException {

        //获取文件原名
        String fileName = photo.getOriginalFilename();
        //创建要上传的文件对象
        File newFile = new File(filePath,fileName);
        //将文件copy到目标地址
        photo.transferTo(newFile);

        String filePath = fileRelativePath.replace("*","")+fileName;

        return new Response(200,"success",filePath);
    }


    @GetMapping("/delete")
    public void delete(Integer id){

        teacherService.delete(id);
    }


    @GetMapping("/update")
    public void update(String name,String phone,String mail,String level,String photo,int id){
        System.out.println("我要修改:"+name);
        Teacher teacher = new Teacher(name,phone,mail,level,photo);
        teacher.setId(id);
        teacherService.update(teacher);
    }

    @RequestMapping(value="/getlist")
    public List<Teacher> getlist(){
        return teacherService.getlist();
    }








}
