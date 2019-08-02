package com.chen.app_core.app;


import android.content.Context;

import java.util.HashMap;

public class Configurator {
    private static final HashMap<String,Object> APPCONFIG = new HashMap<>();


    private Configurator(){
        APPCONFIG.put(ConfigType.CONFIG_READY.name(),false);
    }

    public final void configure(){
        APPCONFIG.put(ConfigType.CONFIG_READY.name(),true);
    }

    @SuppressWarnings("unchecked")
    public static Configurator getInstance(){
        return Holder.Instance;
    }


    public HashMap<String,Object> getConfigs(){
        return APPCONFIG;
    }

    private static class Holder{
        private static final Configurator Instance = new Configurator();
    }


    public final Configurator withApiHost(String host){
        APPCONFIG.put(ConfigType.API_HOST.name(),host);
        return this;
    }


    public final Configurator withContext(Context context){
        APPCONFIG.put(ConfigType.APPLICATION_CONTEXT.name(),context);
        return this;
    }

    private void checkfiguration(){
        final boolean isReady = (boolean) APPCONFIG.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configuration is not ready,call config");
        }
    }

    public final <T> T getConfiguration(Enum<ConfigType> key){
        checkfiguration();
        return (T)APPCONFIG.get(key.name());
    }


}
