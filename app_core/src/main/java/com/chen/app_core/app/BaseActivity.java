package com.chen.app_core.app;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutid());
        ButterKnife.bind(this);
//        ActionBar actionBar = getSupportActionBar();
//
//        if (actionBar != null){
//            actionBar.hide();
//        }
        havebind();
        //状态栏修改为透明
        setStausbar();
    }


    public abstract int getLayoutid();

    public abstract void havebind();


    private void setStausbar(){
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow()
                .getDecorView()
                .setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
}


}
