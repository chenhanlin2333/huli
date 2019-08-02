package com.chen.app_ec.user;


public class User {
    public static final int OLDMAN= 0;
    public static final int RELATIVE_PEOPLE = 1;
    public static final int HELPPEOPLE = 2;
    private static volatile User INSTANCE;
    private int oldmanid;
    private int helpid;
    private int  relativeid;

    // 老人
    private String nameold;
    private int roomid;
    private int marry;
    private int zu;
    private String birth;
    private int carelevel;

    //护工
    private int carelevelhelp;
    private int caretimehelp;
    private String namehelp;

    //家人
    private int relation;
    private String namefamily;

    //密码
    private String pwd;
    private String headurl;

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getNameold() {
        return nameold;
    }

    public void setNameold(String nameold) {
        this.nameold = nameold;
    }

    public int getCarelevelhelp() {
        return carelevelhelp;
    }

    public void setCarelevelhelp(int carelevelhelp) {
        this.carelevelhelp = carelevelhelp;
    }

    public int getCaretimehelp() {
        return caretimehelp;
    }

    public void setCaretimehelp(int caretimehelp) {
        this.caretimehelp = caretimehelp;
    }

    public String getNamehelp() {
        return namehelp;
    }

    public void setNamehelp(String namehelp) {
        this.namehelp = namehelp;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public String getNamefamily() {
        return namefamily;
    }

    public void setNamefamily(String namefamily) {
        this.namefamily = namefamily;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public static void setINSTANCE(User INSTANCE) {
        User.INSTANCE = INSTANCE;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
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

    public void setCarelevel(int carelevel) {
        this.carelevel = carelevel;
    }


    public static int getOLDMAN() {
        return OLDMAN;
    }

    public static int getHELPPEOPLE() {
        return HELPPEOPLE;
    }

    public static int getRelativePeople() {
        return RELATIVE_PEOPLE;
    }

    public static User getINSTANCE() {
        return INSTANCE;
    }

    public int getRoomid() {
        return roomid;
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

    public int getCarelevel() {
        return carelevel;
    }

    public int getOldmanid() {
        return oldmanid;
    }

    public int getHelpid() {
        return helpid;
    }

    public int getRelativeid() {
        return relativeid;
    }

    private int type;

    private User(){

    }

    public int getID(){
        int id ;
        if (type == OLDMAN){
            id = oldmanid;
        }else if (type == HELPPEOPLE){
            id = helpid;
        }else {
            id = relativeid;
        }
        return id;
    }


    public static User getInstance(){
        if (INSTANCE == null){
            synchronized (User.class){
                INSTANCE = new User();
            }
        }
        return INSTANCE;
    }

    public int getType() {
        return type;
    }

    public void setOldmanid(int oldmanid) {
        this.oldmanid = oldmanid;
    }

    public void setHelpid(int helpid) {
        this.helpid = helpid;
    }

    public void setRelativeid(int relativeid) {
        this.relativeid = relativeid;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName(){
        if (type == User.OLDMAN){
            return nameold;
        }else if (type == User.HELPPEOPLE){
            return namehelp;
        }else {
            return namefamily;
        }
    }
}
