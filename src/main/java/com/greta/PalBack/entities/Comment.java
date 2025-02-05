package com.greta.PalBack.entities;

import java.time.LocalDateTime;

public class Comment {
    private String isbn;
    private Integer userId;
    private String commentContent;
    private LocalDateTime createTime;

    public Comment(String isbn, Integer userId, String commentContent, LocalDateTime createTime) {
        this.isbn = isbn;
        this.userId = userId;
        this.commentContent = commentContent;
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
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
