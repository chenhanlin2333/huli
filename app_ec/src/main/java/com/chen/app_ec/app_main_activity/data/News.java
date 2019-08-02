package com.chen.app_ec.app_main_activity.data;


public class News {
    private String url;
    private String text;

    public News(String url, String text) {
        this.url = url;
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }
}
