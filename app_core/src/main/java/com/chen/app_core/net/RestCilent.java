package com.chen.app_core.net;

import android.content.Context;
import android.util.Log;


import com.chen.app_core.net.callback.IError;
import com.chen.app_core.net.callback.IFailure;
import com.chen.app_core.net.callback.IRequest;
import com.chen.app_core.net.callback.ISuccess;
import com.chen.app_core.net.callback.LatteCallBack;
import com.chen.app_core.ui.LatteLaoder;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.PartMap;

public class RestCilent {


    private final String URL;
    private final Map<String, Object> PARAMS = RestCreater.getParams();
    private final ISuccess ISUCCESS;
    private final IError IERROR;
    private final IRequest IREQUEST;
    private final IFailure IFAILURE;
    private final RequestBody REQUESTBODY;
    private final Context MCONTENXT;
    private final File FILE;
    private final Map<String,String> PARTPARAM ;

    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;


    //我们不直接创建RestCilent 而是通过建造者模式创建
    public RestCilent(String URLc,
                      ISuccess ISUCCESS,
                      Map<String, Object> map,
                      IError IERROR,
                      IRequest IREQUEST,
                      IFailure IFAILURE,
                      RequestBody REQUESTBODY,
                      File file,
                      Context Context,
                      String download_dir,
                      String extension,
                      String name,
                      Map<String,String> partmap) {
        this.URL = URLc;
        this.PARAMS.putAll(map);
        this.ISUCCESS = ISUCCESS;
        this.IERROR = IERROR;
        this.IREQUEST = IREQUEST;
        this.IFAILURE = IFAILURE;
        this.REQUESTBODY = REQUESTBODY;
        this.FILE = file;
        this.MCONTENXT = Context;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.PARTPARAM = partmap;
    }


    public static RestCilentBuilder builder() {
        return new RestCilentBuilder();
    }


    // 每个请求的例如get post 都要执行 ①得到网路服务接口 ②显示loadding
    private void request(HttpMethod method) {


        final RestService service = RestCreater.getRrest_Service();
        Call<String> call = null;
        if (IREQUEST != null) {
            IREQUEST.onRequeststrat();
            if (MCONTENXT != null) {
                LatteLaoder.showLoading(MCONTENXT);
            }
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                Log.w("Tag",call.toString());
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, REQUESTBODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, REQUESTBODY);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
                break;
            case UPLAODTEXTFILE:
                final RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/png"),FILE);
                MultipartBody.Part photo = MultipartBody.Part.createFormData("file",FILE.getName(),requestBody1);
                call = service.uploadFileandText(URL, PARTPARAM,photo);
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getCallback());
    }
    }

    //LatteCallBack是 Callback<String> 的子类 Callback 这四个接口再callback的response去执行
    public LatteCallBack getCallback() {
        return new LatteCallBack(ISUCCESS, IERROR, IREQUEST, IFAILURE);
    }


    public void get() {
        request(HttpMethod.GET);
    }

    public void post() {
        if (REQUESTBODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) throw new RuntimeException("pararm must be null");
            request(HttpMethod.POST_RAW);
        }
    }

    public void postTextAndFile(){
       request(HttpMethod.UPLAODTEXTFILE);
    }


    public void put() {
        if (REQUESTBODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) throw new RuntimeException("pararm must be null");
            request(HttpMethod.PUT_RAW);
        }
    }


    public void delete() {
        request(HttpMethod.DELETE);
    }





}
