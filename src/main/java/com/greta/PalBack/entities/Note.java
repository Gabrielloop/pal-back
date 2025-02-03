package com.greta.PalBack.entities;

import org.springframework.format.annotation.DateTimeFormat;

public class Note {
    private String isbn;
    private Integer user_id;
    private Integer note_content;
    private String createTime;

    public Note(String isbn, Integer user_id, Integer note_content, String createTime) {
        this.isbn = isbn;
        this.user_id = user_id;
        this.note_content = note_content;
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
        return user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getNoteContent() {
        return note_content;
    }
    public void setAuthor(Integer note_content) {
        this.note_content = note_content;
    }

    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
