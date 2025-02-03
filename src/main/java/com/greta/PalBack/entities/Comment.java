package com.greta.PalBack.entities;

public class Comment {
    private String isbn;
    private Integer user_id;
    private String comment_content;
    private String createTime;

    public Comment(String isbn, Integer user_id, String comment_content, String createTime) {
        this.isbn = isbn;
        this.user_id = user_id;
        this.comment_content = comment_content;
        this.createTime = createTime;
    }

    public Comment() {
    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getUserId() {
        return user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getCommentContent() {
        return comment_content;
    }
    public void setAuthor(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
