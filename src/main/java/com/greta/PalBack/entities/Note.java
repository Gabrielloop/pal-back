package com.greta.PalBack.entities;

import java.time.LocalDateTime;

public class Note {
    private String isbn;
    private Integer userId;
    private Integer noteContent;
    private LocalDateTime createTime;

    public Note(String isbn, Integer userId, Integer noteContent, LocalDateTime createTime) {
        this.isbn = isbn;
        this.userId = userId;
        this.noteContent = noteContent;
        this.createTime = createTime;
    }

    public Note() {
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

    public Integer getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(Integer noteContent) {
        this.noteContent = noteContent;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
