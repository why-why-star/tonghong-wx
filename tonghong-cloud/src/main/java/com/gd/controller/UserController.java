package com.gd.controller;

import com.gd.model.*;
import com.gd.service.CustomerService;
import com.gd.service.TeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * description: UserController <br>
 * date: 2022-10-18 14:40 <br>
 * author: DaTao <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Resource
    private CustomerService customerService;

    @RequestMapping("/login")
    public Response login(String nickname,String name,String password){


        /**
         * 登陆逻辑
         *
         *  验证用户密码，如果验证成功。
         *
         *  创建一个随机字符串（UUId） 将  uuid作为ikey 和 用户信息以及 token 存储到redis中  （servletContext）
         *
         *  将 key 返回给客户端。
         *
         */
        return null;
    }



}
