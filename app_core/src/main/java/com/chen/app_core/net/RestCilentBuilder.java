package com.chen.app_core.net;

import android.content.Context;

import com.chen.app_core.net.callback.IError;
import com.chen.app_core.net.callback.IFailure;
import com.chen.app_core.net.callback.IRequest;
import com.chen.app_core.net.callback.ISuccess;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RestCilentBuilder {
    private String mURL ;
    private Map<String,Object> mPARAMS = RestCreater.getParams();
    private ISuccess mISUCCESS;
    private IError mIERROR;
    private IRequest mIREQUEST;
    private IFailure mIFAILURE;

    private RequestBody mREQUESTBODY;
    private Context mContext;
    private File mFile;
    private String DOWNLOAD_DIR;
    private String EXTENSION;
    private String NAME;
    private HashMap<String,String> MPARTMAP ;

    //然后生成一个restcilent
    public RestCilent build(){
        return new RestCilent(mURL,mISUCCESS,mPARAMS,mIERROR,mIREQUEST,mIFAILURE,mREQUESTBODY,mFile,mContext,DOWNLOAD_DIR,EXTENSION,NAME,MPARTMAP);
    }

    //基础的url
    public RestCilentBuilder url(String URL) {
        mURL = URL;
        return this;
    }


    //网络请求的参数
    public RestCilentBuilder params(HashMap<String, Object> PARAMS) {
        mPARAMS.clear();
        mPARAMS.putAll(PARAMS);
        return this;
    }
    public RestCilentBuilder params(String key, String value) {
        mPARAMS.put(key,value);
        return this;
    }

    //context 用于显示loading
    public RestCilentBuilder addcontextandload(Context context){
        this.mContext = context;
        return this;
    }


    //传文件用
    public RestCilentBuilder setFile(File file){
        mFile = file;
        return this;
    }

    public RestCilentBuilder setFile(String file){
        File file1 = new File(file);
        mFile =file1;
        return this;
    }


    //四个接口回调
    public RestCilentBuilder success(ISuccess ISUCCESS) {
        mISUCCESS = ISUCCESS;
        return this;
    }

    public RestCilentBuilder error(IError IERROR) {
        mIERROR = IERROR;
        return this;
    }


    public RestCilentBuilder request(IRequest IREQUEST) {
        mIREQUEST = IREQUEST;
        return this;
    }

    public RestCilentBuilder failure(IFailure IFAILURE) {
        mIFAILURE = IFAILURE;
        return this;
    }

    //上传文件
    public RestCilentBuilder raw(String raw) {
        mPARAMS.clear();
        this.mREQUESTBODY =  RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }



    public final RestCilentBuilder dir(String dir){
        this.DOWNLOAD_DIR = dir;
        return this;
    }

    public final RestCilentBuilder extension(String extension){
        this.DOWNLOAD_DIR = extension;
        return this;
    }


    public final RestCilentBuilder filename(String name){
        this.NAME = name;
        return this;
    }

    public final RestCilentBuilder PARTMAP(HashMap<String ,String> map){
        MPARTMAP =map;
        return this;
    }


}
