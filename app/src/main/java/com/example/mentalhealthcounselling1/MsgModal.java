package com.example.mentalhealthcounselling1;

public class MsgModal {
    private String chatBotReply;
    private String videoUrl;

    public MsgModal(String chatBotReply, String videoUrl) {
        this.chatBotReply = chatBotReply;
        this.videoUrl = videoUrl;
    }

    public String getChatBotReply() {
        return chatBotReply;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setChatBotReply(String chatBotReply) {
        this.chatBotReply = chatBotReply;
    }
}
