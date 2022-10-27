package com.gd.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * description: Course <br>
 * date: 2022-10-13 10:39 <br>
 * author: DaTao <br>
 * version: 1.0 <br>
 */
public class Course {

    private Integer id;
    private String name;
    private Integer teacherId;
    private String teacherName;
    private String attribute;
    private String faceImg;
    private Integer ks;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    public Course() {
    }

    public Course(Integer id, String name, Integer teacherId, String attribute, String faceImg, Date addTime) {
        this.id = id;
        this.name = name;
        this.teacherId = teacherId;
        this.attribute = attribute;
        this.faceImg = faceImg;
        this.addTime = addTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getKs() {
        return ks;
    }

    public void setKs(Integer ks) {
        this.ks = ks;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", attribute='" + attribute + '\'' +
                ", faceImg='" + faceImg + '\'' +
                ", ks=" + ks +
                ", addTime=" + addTime +
                '}';
    }
}
