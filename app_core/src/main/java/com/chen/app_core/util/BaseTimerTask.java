package com.chen.app_core.util;

import java.util.TimerTask;

public class BaseTimerTask extends TimerTask {

    TimeListener mTimeListener ;

    public BaseTimerTask(TimeListener timeListener) {
        mTimeListener = timeListener;
    }

    public void setTimeListener(TimeListener timeListener) {
        mTimeListener = timeListener;
    }

    @Override
    public void run() {
        if (mTimeListener!=null){
            mTimeListener.onTimer();
        }
    }
}
