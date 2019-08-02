package com.chen.huli;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.app_core.app.BaseActivity;
import com.chen.app_core.net.RestCilent;
import com.chen.app_core.net.callback.IError;
import com.chen.app_core.net.callback.ISuccess;
import com.chen.app_core.storage.AppSharedpreference;
import com.chen.app_core.util.BaseTimerTask;
import com.chen.app_core.util.TimeListener;
import com.chen.app_ec.app_main_activity.WebViewActivity;
import com.chen.app_ec.loginandregister.ActivityLogin;

import java.text.MessageFormat;
import java.util.Timer;
import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements TimeListener {

    private int mCount = 5 ;
    private Timer mTimer = null;


    @BindView(R2.id.time_down)
    TextView tv_timedown;


    @OnClick(R2.id.time_down)
    void gotoLogin(){
        if (mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
        wherego();
    }



    class  click implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        }
    }


    private void wherego(){
//        if (AppSharedpreference.isLogin()){
//            //TODO 直接登录  获取全局用户 跳转到主页
//        }else {
            ActivityLogin.start(this);
//        }
    }


    @Override
    public int getLayoutid() {
        return R.layout.activity_main;
    }

    @Override
    public void havebind() {






        initTimer();
    }

    private void initTimer(){
        mTimer = new Timer();
        BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);
    }

    @Override
    public void onTimer() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tv_timedown !=null){
                    tv_timedown.setText(MessageFormat.format("跳过\n{0}s",mCount));
                    mCount--;
                    if (mCount<0){
                        if (mTimer != null){
                            mTimer.cancel();
                            mTimer = null;
                            wherego();
                            finish();
                        }
                    }
                }
            }
        });
    }

}

