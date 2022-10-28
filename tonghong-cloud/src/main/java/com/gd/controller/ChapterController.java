package com.gd.controller;

import com.gd.model.Chapter;
import com.gd.model.Course;
import com.gd.model.Pages;
import com.gd.model.Response;
import com.gd.service.ChapterService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: ChapterController <br>
 * date: 2022-10-13 15:00 <br>
 * author: DaTao <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("/chapter")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    //物理路径
    @Value("${file.upload.path}")
    private String filePath;

    /**显示虚拟地址*/
    @Value("${file.upload.relative}")
    private String fileRelativePath;


    @GetMapping("/getPage")
    public Pages<Chapter> getPage(Integer pageIndex, Integer pageSize,Integer courseId, String chapterName){

        System.out.println("我拿到了"+pageIndex+"-"+pageSize+"-"+courseId+"-"+chapterName);
        Pages<Chapter> pagers = chapterService.getPagers(pageIndex, pageSize,courseId,chapterName);
        return pagers;
    }

    @RequestMapping("/add")
    public Response add(String name, Integer courseId,String info, String video){
        System.out.println("我接收到到章节"+name+"+"+courseId+"-"+info+"+"+video);
        Chapter tempchapter = new Chapter(name,courseId,info,video);
        System.out.println(tempchapter);
        chapterService.add(tempchapter);

        return new Response(200,"success");
    }


    @GetMapping("/delete")
    public void delete(Integer id){

        chapterService.delete(id);
    }


    /**
     * 上传课程封面
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/upload",method = RequestMethod.POST)
    public Response upload(MultipartFile file) throws IOException {

        //获取文件原名
        String fileName = file.getOriginalFilename();
        //创建要上传的文件对象
        File newFile = new File(filePath,fileName);
        //将文件copy到目标地址
        file.transferTo(newFile);

        String filePath = fileRelativePath.replace("*","")+fileName;

        return new Response(200,"success",filePath);
    }


    @RequestMapping(value="/getlistbyid")
    public List<Chapter> getlistbyid(int courseId){
        return chapterService.getlistbyid(courseId);
    }



}
