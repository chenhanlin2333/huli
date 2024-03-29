package com.chen.app_core.net;

import java.util.Calendar;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface RestService {


    @GET
    Call<String> get(@Url String url, @QueryMap Map<String, Object> map);


    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String, Object> map);


    @POST
    Call<String> postRaw(@Url String url, @Body RequestBody body);


    @PUT
    Call<String> put(@Url String url, @FieldMap Map<String, Object> map);

    @PUT
    Call<String> putRaw(@Url String url, @Body RequestBody body);

    @DELETE
    Call<String> delete(@Url String url, @FieldMap Map<String, Object> map);

    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url, @FieldMap Map<String, Object> map);


    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);


    @Multipart
    @POST
    Call<String> uploadFileandText(@Url String url, @PartMap Map<String,String> map,@Part MultipartBody.Part file);


}
