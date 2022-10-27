package com.gd.config;


import com.gd.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * description: InterceptorConfig <br>
 * date: 2022/5/15 15:01 <br>
 * author: DaTao <br>
 * version: 1.0 <br>
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new JWTInterceptor());
        registration.addPathPatterns("/admin/**");                      //所有/admin/路径都被拦截
        registration.excludePathPatterns(                         //添加不拦截路径
                "你的登陆路径",            //登录
                "/admin/**/*.html",            //html静态资源
                "/admin/**/*.js",              //js静态资源
                "/admin/**/*.css",             //css静态资源
                "/admin/**/*.woff",
                "/admin/**/*.ttf",
                "/admin/**/*.jpg",
                "/admin/**/*.png",
                "/admin/**/verify",
                "/admin/**/login",
                "/admin/**/userLogin",
                "/admin/**/getteacherlist"
        );
    }
}
