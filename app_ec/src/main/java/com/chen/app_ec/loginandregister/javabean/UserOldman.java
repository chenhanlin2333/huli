package com.chen.app_ec.loginandregister.javabean;



public class UserOldman {

    private int marry;


    private int zu;

    private String birth;

    private int careLevel;

    private String more;

    private int id;

    private String name;

    private String pwd;

    public UserOldman() {
    }

    public UserOldman(int marry, int zu, String birth, int careLevel, String more, int id, String name, String pwd) {
        this.marry = marry;
        this.zu = zu;
        this.birth = birth;
        this.careLevel = careLevel;
        this.more = more;
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }

    public void setMarry(int marry) {
        this.marry = marry;
    }

    public void setZu(int zu) {
        this.zu = zu;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setCareLevel(int careLevel) {
        this.careLevel = careLevel;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    public int getMarry() {
        return marry;
    }

    public int getZu() {
        return zu;
    }

    public String getBirth() {
        return birth;
    }

    public int getCareLevel() {
        return careLevel;
    }

    public String getMore() {
        return more;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }
}
