package com.greta.PalBack.entities;

public class ListBook {
    private Integer userList_id;
    private String isbn;
    private String list_name;
    private String createTime;

    public ListBook(Integer list_id, String isbn, String list_name, String createTime) {
        this.userList_id = userList_id;
        this.isbn = isbn;
        this.list_name = list_name;
        this.createTime = createTime;
    }
    public ListBook() {
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

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
