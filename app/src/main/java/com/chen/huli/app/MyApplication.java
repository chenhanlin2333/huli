package com.chen.huli.app;


import android.app.Application;

import com.chen.app_core.app.AppCore;
import com.chen.app_core.app.ConfigType;
import com.chen.app_core.app.Configurator;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppCore.init(this)
                .withApiHost("http://192.168.2.167:8080/huli/api/")
                .configure();

    }
}
