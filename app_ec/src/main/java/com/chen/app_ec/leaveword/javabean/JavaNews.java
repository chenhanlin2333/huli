package com.chen.app_ec.leaveword.javabean;


public class JavaNews {
    private int send;
    private int receive;
    private String content;
    private String time;

    public JavaNews() {
    }

    public JavaNews(int send, int receive, String content, String time) {
        this.send = send;
        this.receive = receive;
        this.content = content;
        this.time = time;
    }

    public int getSend() {
        return send;
    }

    public void setSend(int send) {
        this.send = send;
    }

    public int getReceive() {
        return receive;
    }

    public void setReceive(int receive) {
        this.receive = receive;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
