package com.gd.model;

public class Teacher {
    int id;
    String account;
    String name;
    String phone;
    String add_time;
    String mail;
    String level;
    String photo;

    public Teacher() {
    }

    public Teacher(String name, String phone, String mail, String level, String photo) {
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.level = level;
        this.photo = photo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAdd_time() {
        return add_time;
    }

    public String getMail() {
        return mail;
    }

    public String getLevel() {
        return level;
    }

    public String getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", add_time='" + add_time + '\'' +
                ", mail='" + mail + '\'' +
                ", level='" + level + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
