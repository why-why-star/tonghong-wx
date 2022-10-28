package com.gd.model;

public class Message {
    int id;
    String text;
    String time;
    int userid;

    public Message() {
    }

    public Message(String text, int userid) {
        this.text = text;
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                ", userid=" + userid +
                '}';
    }
}
