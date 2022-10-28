package com.gd.controller;

import com.gd.model.Customer;
import com.gd.model.Wxauth;
import com.gd.service.CustomerService;
import com.gd.utils.JWTUtils;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * description: LoginController <br>
 * date: 2022-10-14 9:50 <br>
 * author: DaTao <br>
 * version: 1.0 <br>
 */
@RestController
public class LoginController {

    @Resource
    private CustomerService customerService;

    @GetMapping("/createOpenKey")
    public String createOpenKey(String code,String nickname,String avatarUrl, HttpSession session) throws IOException {

        System.out.println("接收到的code:"+code);
        System.out.println("接收到的nickname:"+nickname);
        System.out.println("接收到的avatarUrl:"+avatarUrl);
        System.out.println(code);
//
//        //https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
//
//        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx6fbf4481834e6e8c&secret=7f72b9a41dcd745d0f0b26fbbd60b780&js_code="+code+"&grant_type=authorization_code";
//        //okhttp3 库 用来发送请求
//        OkHttpClient client = new OkHttpClient();
//        //构建请求对象
//        Request request = new Request.Builder().url(url).build();
//        //执行请求获取响应
//        Response response = client.newCall(request).execute();
//        //从响应对象中取出 json字符串
//        String result = response.body().string();
//
//        //用Gson将json转换为对象
//        Wxauth wxauth = new Gson().fromJson(result, Wxauth.class);
//        System.out.println(wxauth);
//        //创建uuid和openid及session_key关联
//        String token = UUID.randomUUID().toString();



        Map<String, String> mymap = new HashMap<>();
        mymap.put("code",code);
        mymap.put("nickname",nickname);
        mymap.put("avatarUrl",avatarUrl);


        JWTUtils jwtUtils = new JWTUtils();
        String token =  jwtUtils.getToken(mymap);



        //servletContext的作用域对应 整个应用。
        ServletContext servletContext = session.getServletContext();
        servletContext.setAttribute(token,mymap);


        //持久化用户数据
        Customer customer = new Customer(null,null,nickname,avatarUrl);

        //检查用户是否存在，没有就创建，已存在只返回新的token
        if(customerService.check(customer)){
            return token;
        }

        customerService.add(customer);
        return token;
    }

    @RequestMapping("/login")
    public boolean login(String nickname, String name, String password){

        System.out.println("接收到的数据nickname:"+nickname);
        System.out.println("接收到的数据name:"+name);
        System.out.println("接收到的数据password:"+password);

        Customer customer = new Customer(nickname,name,password);
        if (customerService.checkuser(customer)){
            System.out.println("先填充");
            customerService.fill(customer);
        }

        customer = customerService.login(customer);
        if (customer==null){
            System.out.println("登录失败");
            return false;
        }
        System.out.println("登录成功");
        return true;
    }



    @RequestMapping("/customerList")
    public List<Customer> list(){
        return customerService.list();
    }


    @RequestMapping("/checktoken")
    public boolean checktoken(String token){

        JWTUtils jwtUtils = new JWTUtils();
        try {
            jwtUtils.decode(token);
            return true;
        }
        catch(Exception e){
            return false;
        }

    }

}
