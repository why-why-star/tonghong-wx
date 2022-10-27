package com.gd.model;

/**
 * description: Customer <br>
 * date: 2022-10-14 11:30 <br>
 * author: DaTao <br>
 * version: 1.0 <br>
 */
public class Customer {

    private Integer id;
    private String openid;
    private String sessionKey;
    private String nickname;
    private String avatarUrl;
    private String addTime;
    private String account;
    private String phone;
    //用于登录
    private String name;
    private String password;

    public Customer() {
    }

    public Customer( String openid, String sessionKey, String nickname, String avatarUrl) {
        this.openid = openid;
        this.sessionKey = sessionKey;
        this.nickname = nickname;
        this.avatarUrl = avatarUrl;
    }

    public Customer(String nickname, String name, String password) {
        this.nickname = nickname;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", openid='" + openid + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", addTime='" + addTime + '\'' +
                ", account='" + account + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
