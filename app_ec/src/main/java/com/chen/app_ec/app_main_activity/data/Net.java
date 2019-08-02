package com.chen.app_ec.app_main_activity.data;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface Net {
    @Multipart
    @POST()
    Call<String> upload6(@Url String url , @Part("username") RequestBody userName,
                         @Part("password") RequestBody passWord,
                         @Part MultipartBody.Part file);
}
