package com.chen.app_core.app;


import android.content.Context;

import java.util.HashMap;

public class AppCore {
    public static Configurator init(Context context){
        Configurator configurator = Configurator.getInstance();
        configurator.withContext(context);
        return configurator;
    }


    public final static Context getApplication(){
        return Configurator.getInstance().getConfiguration(ConfigType.APPLICATION_CONTEXT);
    }

    public static HashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getConfigs();
    }
}
