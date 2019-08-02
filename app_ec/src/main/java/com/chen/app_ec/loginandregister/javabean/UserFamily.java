package com.chen.app_ec.loginandregister.javabean;


public class UserFamily {
    private int relation;
    private int olderId;
    private int id;
//    private String name;
    private String pwd;

    public UserFamily() {

    }

    public UserFamily(int relation, int oldid, int id, String pwd) {
        this.relation = relation;
        this.olderId = oldid;
        this.id = id;
//        this.name = name;
        this.pwd = pwd;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public int getOlderId() {
        return olderId;
    }

    public void setOlderId(int olderId) {
        this.olderId = olderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
