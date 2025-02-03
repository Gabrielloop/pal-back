package com.greta.PalBack.entities;

public class UserList {
    private Integer userList_id;
    private Integer user_id;
    private String userList_name;
    private String userList_description;
    private String userList_type;
    private String createTime;

    public UserList(Integer userList_id, Integer user_id, String userList_name, String userList_description, String userList_type, String createTime) {
        this.userList_id = userList_id;
        this.user_id = user_id;
        this.userList_name = userList_name;
        this.userList_description = userList_description;
        this.userList_type = userList_type;
        this.createTime = createTime;
    }

    public UserList() {
    }

    public Integer getUserListId() {
        return userList_id;
    }

    public void setUserListId(Integer list_id) {
        this.userList_id = list_id;
    }

    public Integer getUserId() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUserListName() {
        return userList_name;
    }

    public void setUserListName(String list_name) {
        this.userList_name = list_name;
    }

    public String getUserList_description() {
        return userList_description;
    }

    public void setUserList_description(String list_description) {
        this.userList_description = list_description;
    }

    public String getUserList_type() {
        return userList_type;
    }

    public void setUserList_type(String list_type) {
        this.userList_type = list_type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
