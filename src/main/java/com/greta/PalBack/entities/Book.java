package com.greta.PalBack.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class Book {

    @Size(min = 10, max = 13, message = "ISBN invalide")
    @NotNull(message = "ISBN requis")
    @NotBlank(message = "ISBN requis")
    private String isbn;

    @NotBlank(message = "Titre requis")
    private String title;

    @NotBlank(message = "Auteur requis")
    private String author;

    private String publisher;
    private Long year;
    private LocalDateTime updatedTime;
    private LocalDateTime createTime;

    public Book(String isbn, String title, String author, String publisher, Long year, LocalDateTime updatedTime, LocalDateTime createTime) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.updatedTime = updatedTime;
        this.createTime = createTime;
    }

    public Book() {
    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public Long getYear() {
        return year;
    }
    public void setYear(Long year) {
        this.year = year;
    }
    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }
    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

}
