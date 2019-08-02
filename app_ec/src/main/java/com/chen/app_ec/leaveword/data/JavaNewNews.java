package com.chen.app_ec.leaveword.data;


public class JavaNewNews {
    private int sendId;
    private int receiveId;
    private String content;

    public JavaNewNews() {
    }

    public JavaNewNews(int sendId, int receiveId, String content) {
        this.sendId = sendId;
        this.receiveId = receiveId;
        this.content = content;
    }

    public int getSendId() {
        return sendId;
    }

    public void setSendId(int sendId) {
        this.sendId = sendId;
    }

    public int getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(int receiveId) {
        this.receiveId = receiveId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
