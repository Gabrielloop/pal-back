package com.greta.PalBack.entities;

import java.time.LocalDateTime;

public class UserlistBook {
    private Integer userListId;
    private String isbn;
    private LocalDateTime createTime;

    public UserlistBook(Integer userList_id, String isbn, LocalDateTime createTime) {
        this.userListId = userList_id;
        this.isbn = isbn;
        this.createTime = createTime;
    }
    public UserlistBook() {
    }

    public Integer getUserListId() {
        return userListId;
    }

    public void setUserListId(Integer userList_id) {
        this.userListId = userList_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
