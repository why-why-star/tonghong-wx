package com.gd.controller;

import com.gd.model.*;
import com.gd.service.CourseService;
import com.gd.service.CustomerService;
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
@RequestMapping("/Customer")
public class CustomerController {
    @Resource
    private CustomerService customerService;

    //物理路径
    @Value("${file.upload.path}")
    private String filePath;

    /**显示虚拟地址*/
    @Value("${file.upload.relative}")
    private String fileRelativePath;


    @GetMapping("/getPage")
    public Pages<Teacher> getPage(Integer pageIndex, Integer pageSize){
        System.out.println("???");
        Pages<Teacher> pagers = customerService.getPage(pageIndex, pageSize);
        System.out.println(pagers);
        return pagers;
    }


    @GetMapping("/delete")
    public void delete(Integer id){

        customerService.delete(id);
    }

    @GetMapping("/update")
    public void update(int id,String password){
        System.out.println("我要修改:"+id);
        Customer tempcustomer  = new Customer();
        tempcustomer.setId(id);
        tempcustomer.setPassword(password);
        customerService.update(tempcustomer);
    }
}
