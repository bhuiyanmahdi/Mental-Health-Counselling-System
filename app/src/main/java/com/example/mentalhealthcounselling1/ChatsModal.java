package com.example.mentalhealthcounselling1;

public class ChatsModal {

    private String message;
    private String sender;
    private String video;

    public ChatsModal(String message, String video, String sender) {
        this.message = message;
        this.sender = sender;
        this.video = video;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
