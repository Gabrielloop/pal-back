package com.greta.PalBack.entities;

public class UserlistBook {
    private Integer userList_id;
    private String isbn;
    private String createTime;

    public UserlistBook(Integer userList_id, String isbn, String createTime) {
        this.userList_id = userList_id;
        this.isbn = isbn;
        this.createTime = createTime;
    }
    public UserlistBook() {
    }


    public Integer getList_id() {
        return userList_id;
    }

    public void setList_id(Integer list_id) {
        this.userList_id = list_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
