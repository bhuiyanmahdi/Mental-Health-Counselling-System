package com.example.mentalhealthcounselling1;

public class MainRV_item {
    private Integer Image;
    private String text;

    public MainRV_item(Integer image, String text) {
        Image = image;
        this.text = text;
    }

    public Integer getImage() {
        return Image;
    }

    public void setImage(Integer image) {
        Image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
