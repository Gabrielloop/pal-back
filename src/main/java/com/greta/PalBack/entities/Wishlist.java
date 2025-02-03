package com.greta.PalBack.entities;

public class Wishlist {
    private Integer user_id;
    private String isbn;
    private String createTime;

    public Wishlist(Integer user_id, String isbn, String createTime) {
        this.user_id = user_id;
        this.isbn = isbn;
        this.createTime = createTime;
    }

    public Wishlist() {
    }

    public Integer getUserId() {
        return user_id;
    }

    public void setUserId(Integer user_id) {
        this.user_id = user_id;
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
