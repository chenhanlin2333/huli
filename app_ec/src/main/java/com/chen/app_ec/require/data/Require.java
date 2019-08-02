package com.chen.app_ec.require.data;


public class Require {
    private String name;
    private String daystrat;
    private String dayend;

    public Require(String name, String daystrat, String dayend) {
        this.name = name;
        this.daystrat = daystrat;
        this.dayend = dayend;
    }

    public String getName() {
        return name;
    }

    public String getDaystrat() {
        return daystrat;
    }

    public String getDayend() {
        return dayend;
    }
}
