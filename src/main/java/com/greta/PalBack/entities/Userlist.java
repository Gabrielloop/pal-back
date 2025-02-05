package com.greta.PalBack.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Userlist {
    private int userlistId;
    private int userId;
    private String userlistName;
    private String userlistDescription;
    private String userlistType;
    private LocalDateTime userlistCreatetime;

    public Userlist(int userlistId, int userId, String userlistName, String userlistDescription, String userlistType, LocalDateTime userlistCreatetime) {
        this.userlistId = userlistId;
        this.userId = userId;
        this.userlistName = userlistName;
        this.userlistDescription = userlistDescription;
        this.userlistType = userlistType;
        this.userlistCreatetime = userlistCreatetime;
    }

    public Userlist() {
    }

    public int getUserlistId() {
        return userlistId;
    }

    public void setUserlistId(int userlistId) {
        this.userlistId = userlistId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserlistName() {
        return userlistName;
    }

    public void setUserlistName(String userlistName) {
        this.userlistName = userlistName;
    }

    public String getUserlistDescription() {
        return userlistDescription;
    }

    public void setUserlistDescription(String userlistDescription) {
        this.userlistDescription = userlistDescription;
    }

    public String getUserlistType() {
        return userlistType;
    }

    public void setUserlistType(String userlistType) {
        this.userlistType = userlistType;
    }

    public LocalDateTime getUserlistCreatetime() {
        return userlistCreatetime;
    }

    public void setUserlistCreatetime(LocalDateTime userlistCreatetime) {
        this.userlistCreatetime = userlistCreatetime;
    }
}
