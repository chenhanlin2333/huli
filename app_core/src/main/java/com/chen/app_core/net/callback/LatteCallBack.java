package com.chen.app_core.net.callback;

import android.os.Handler;

import com.chen.app_core.ui.LatteLaoder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//retrofit 异步回调接口 就是当retrofit执行网路请求后执行的接口
public class LatteCallBack implements Callback<String> {

    private  final ISuccess ISUCCESS;
    private  final IError IERROR;
    private  final IRequest IREQUEST;
    private  final IFailure IFAILURE;
    private  final Handler mHandler = new Handler();


    public LatteCallBack(ISuccess ISUCCESS, IError IERROR, IRequest IREQUEST, IFailure IFAILURE) {
        this.ISUCCESS = ISUCCESS;
        this.IERROR = IERROR;
        this.IREQUEST = IREQUEST;
        this.IFAILURE = IFAILURE;
    }

    //onResponse 就是网络请求后retrofit调用的方法
    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()){
            if (call.isExecuted()){
                if (ISUCCESS != null){
                    ISUCCESS.onSuccess(response.body());
                }
                if (IREQUEST != null){
                    IREQUEST.onRequestend();
                }
            }
        }else {
            if (IERROR != null){
                IERROR.onError(response.code(),response.message());
            }
            if (IREQUEST != null){
                IREQUEST.onRequestend();
            }
        }
        LatteLaoder.stopLoade();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (IFAILURE !=null){
            IFAILURE.onFailure();
        }
        if (IREQUEST != null){
            IREQUEST.onRequestend();
        }
    }


}
