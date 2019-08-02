package com.chen.app_ec.loginandregister.javabean;


public class Userhelp {
    private int careLevel;
    private int careTime;
    private int useInfo;
    private int id;
    private String name;
    private String pwd;

    public Userhelp() {
    }

    public Userhelp(int careLevel, int careTime, int useInfo, int id, String name, String pwd) {
        this.careLevel = careLevel;
        this.careTime = careTime;
        this.useInfo = useInfo;
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }

    public int getCareLevel() {
        return careLevel;
    }

    public void setCareLevel(int careLevel) {
        this.careLevel = careLevel;
    }

    public int getCareTime() {
        return careTime;
    }

    public void setCareTime(int careTime) {
        this.careTime = careTime;
    }

    public int getUseInfo() {
        return useInfo;
    }

    public void setUseInfo(int useInfo) {
        this.useInfo = useInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
