package com.chen.app_core.net;

import com.chen.app_core.app.AppCore;
import com.chen.app_core.app.ConfigType;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

//主要功能生成restservice的网络接口，过程①先获取一个okhttpcilent，②创建retrofit对象
public class RestCreater {

    public static WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARMAS;
    }

    //参数列表单列且多线程安全
    private static final class ParamsHolder{
        public static WeakHashMap<String,Object> PARMAS = new WeakHashMap<>();
    }


    //
    public static Retrofit getRrtrofit(){
        return RertrofitHolder.RETROFITINSTANCE;
    }

    public static RestService getRrest_Service(){
        return RestServiceHolder.REST_SERVICE;
    }


    //获取retfofit  Baseurl（放在同一app注册表里），基础解析库（ScalarsConverterFactory.create()），celint
    private static final class RertrofitHolder{
        private static final String BASE_URL = (String) AppCore.getConfigurations().get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFITINSTANCE = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(Okhttpholder.cilent)
                .build();
    }


    // 建造一个单例okhttpcilent
    private static final class Okhttpholder{
        private static long timeout = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static OkHttpClient cilent = BUILDER
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .build();
    }

    // 得到一个网路接口的服务
    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE = RertrofitHolder.RETROFITINSTANCE.create(RestService.class);
    }
}
