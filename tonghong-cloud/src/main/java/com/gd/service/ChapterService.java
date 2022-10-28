package com.gd.service;

import com.gd.mapper.ChapterMapper;
import com.gd.model.Chapter;
import com.gd.model.Course;
import com.gd.model.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: ChapterService <br>
 * date: 2022-10-13 15:04 <br>
 * author: DaTao <br>
 * version: 1.0 <br>
 */
@Service
public class ChapterService {

    @Autowired
    private ChapterMapper chapterMapper;

    /**
     * 分页
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public Pages<Chapter> getPagers(Integer pageIndex, Integer pageSize,Integer courseId, String chapterName) {

        //起始条数
        Integer recordStart = (pageIndex-1)*pageSize;
        System.out.println("Service拿到了"+recordStart+"-"+pageSize+"-"+courseId+"-"+chapterName);
        //获取data
        List<Chapter> data = chapterMapper.queryCourse(recordStart,pageSize,courseId,chapterName);

        String[] statusInfos = {"未学习","学习中","已学完"};

        for(Chapter chapter : data){
            chapter.setStatusInfo(statusInfos[chapter.getStatus()]);
        }

        int totalRecord = chapterMapper.getRecordCount(courseId);

        //计算总页数
        Integer totalPage = totalRecord%pageSize == 0 ? totalRecord/pageSize : totalRecord/pageSize + 1;

        //组装page对象
        Pages<Chapter> pages = new Pages(pageSize,totalPage,totalRecord,pageIndex,data);


        return pages;

    }


    public void add(Chapter chapter) {
        chapterMapper.add(chapter);
    }

    public void delete(Integer id) {
        chapterMapper.delete(id);
    }


    public void update(Chapter chapter) {
        chapterMapper.update(chapter);
    }

    public List<Chapter> getlistbyid(int course_id){
        return chapterMapper.getlistbyid(course_id);
    }

}
