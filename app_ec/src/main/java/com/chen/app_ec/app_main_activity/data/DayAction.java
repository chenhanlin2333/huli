package com.chen.app_ec.app_main_activity.data;


import java.sql.Time;

public class DayAction {
    public static final String[] ACTIONS = {"家属探访","老人生日","家属探访\n老人生日"};
    private String time;
    private int action;


    public DayAction(String time, int action) {
        this.time = time;
        this.action = action;
    }

    public String getTime() {
        return time;
    }

    public String getAction(){
        return ACTIONS[action];
    }
}
