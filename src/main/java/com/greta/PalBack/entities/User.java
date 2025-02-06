package com.greta.PalBack.entities;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class User {
    private Integer userId;
    private String userName;
    private String userMail;
    private String userPassword;
    private String userRole;
    private LocalDateTime userLastLogin;
    private LocalDateTime createTime;

    public User(Integer userId, String userName, String userMail, String userPassword, String userRole, LocalDateTime userLastLogin, LocalDateTime createTime) {
        this.userId = userId;
        this.userName = userName;
        this.userMail = userMail;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.userLastLogin = userLastLogin;
        this.createTime = createTime;
    }

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public LocalDateTime getUserLastLogin() {
        return userLastLogin;
    }

    public void setUserLastLogin(LocalDateTime userLastLogin) {
        this.userLastLogin = userLastLogin;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
