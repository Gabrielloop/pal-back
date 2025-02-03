package com.greta.PalBack.entities;

import org.springframework.format.annotation.DateTimeFormat;

public class User {
    private Integer user_id;
    private String user_name;
    private String user_mail;
    private String user_password;
    private String user_last_login;
    private String create_time;

    public User(Integer user_id, String user_name, String user_mail, String user_password, String user_last_login, String create_time) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_mail = user_mail;
        this.user_password = user_password;
        this.user_last_login = user_last_login;
        this.create_time = create_time;
    }

    public User() {
    }

    public Integer getUserId() {
        return user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return user_name;
    }
    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_mail() {
        return user_mail;
    }
    public void setUserMail(String user_mail) {
        this.user_mail = user_mail;
    }

    public String getUserPassword() {
        return user_password;
    }
    public void setUserPassword(String user_password) {
        this.user_password = user_password;
    }

    public String getUserLastLogin() {
        return user_last_login;
    }
    public void setUserLastLogin(String user_last_login) {
        this.user_last_login = user_last_login;
    }

    public String getCreateTime() {
        return create_time;
    }
    public void setCreateTime(String create_time) {
        this.create_time = create_time;
    }

}
