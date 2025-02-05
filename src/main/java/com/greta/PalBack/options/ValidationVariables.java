package com.greta.PalBack.options;

@Component
public class ValidationVariables {
        private int isbnMinSize = 10;
        private int isbnMaxSize = 13;
        private String isbnMessage = "ISBN invalide";


    public ValidationVariables(int isbnMinSize, int isbnMaxSize, String isbnMessage) {
        this.isbnMinSize = isbnMinSize;
        this.isbnMaxSize = isbnMaxSize;
        this.isbnMessage = isbnMessage;
    }

    public int getIsbnMinSize() {
        return isbnMinSize;
    }

    public void setIsbnMinSize(int isbnMinSize) {
        this.isbnMinSize = isbnMinSize;
    }

    public int getIsbnMaxSize() {
        return isbnMaxSize;
    }

    public void setIsbnMaxSize(int isbnMaxSize) {
        this.isbnMaxSize = isbnMaxSize;
    }

    public String getIsbnMessage() {
        return isbnMessage;
    }

    public void setIsbnMessage(String isbnMessage) {
        this.isbnMessage = isbnMessage;
    }
}
