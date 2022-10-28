package com.gd.model;

/**
 * description: Chapter <br>
 * date: 2022-10-13 15:01 <br>
 * author: DaTao <br>
 * version: 1.0 <br>
 */
public class Chapter {

    public Integer id;
    public String name;
    public Integer courseId;
    public String courseName;
    public String info;
    public String video;
    public Integer status;
    public String statusInfo;

    public Integer getId() {
        return id;
    }

    public Chapter(String name, Integer courseId, String info, String video) {
        this.name = name;
        this.courseId = courseId;
        this.info = info;
        this.video = video;
    }

    public Chapter(Integer id, String name, String info, String video) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.video = video;
    }

    public Chapter(Integer id, String name, Integer courseId, String info, String video, Integer status) {
        this.id = id;
        this.name = name;
        this.courseId = courseId;
        this.info = info;
        this.video = video;
        this.status = status;
    }

    public Chapter(Integer id, String name, Integer courseId, String courseName, String info, String video, Integer status, String statusInfo) {
        this.id = id;
        this.name = name;
        this.courseId = courseId;
        this.courseName = courseName;
        this.info = info;
        this.video = video;
        this.status = status;
        this.statusInfo = statusInfo;
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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", info='" + info + '\'' +
                ", video='" + video + '\'' +
                ", status=" + status +
                ", statusInfo='" + statusInfo + '\'' +
                '}';
    }
}
