package com.chen.app_ec.loginandregister;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.chen.app_core.app.BaseActivity;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;

import butterknife.BindView;

public class ActivityLogin extends BaseActivity {


    @BindView(R2.id.frame_container)
    FrameLayout container;

    FragmentManager mFragmentManager =null;
    FragmentLogin mLoginFragment = new FragmentLogin();
    FragmentRegister mRegistFragment = new FragmentRegister();
    private boolean isLogin = true;




    @Override
    public int getLayoutid() {
        return R.layout.activity_login;
    }

    @Override
    public void havebind() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .add(R.id.frame_container,mRegistFragment)
                .add(R.id.frame_container,mLoginFragment)
                .hide(mRegistFragment)
                .commit();
    }


    public void replacetoregist(){
        mFragmentManager
                .beginTransaction()
                .hide(mLoginFragment)
                .show(mRegistFragment)
                .addToBackStack(null)
                .commit();
        isLogin = false;
    }

    public void replacetoLoginin(){
        mFragmentManager
                .beginTransaction()
                .hide(mRegistFragment)
                .show(mLoginFragment)
                .commit();
        isLogin = true;
    }

    @Override
    public void onBackPressed() {
        if (!isLogin) {
            isLogin = true;
        }
        super.onBackPressed();
    }

    public static void start(Context context){
        Intent intent =  new Intent(context,ActivityLogin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


}
