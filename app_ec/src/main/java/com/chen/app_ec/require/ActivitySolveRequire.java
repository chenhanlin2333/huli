package com.chen.app_ec.require;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.chen.app_core.app.BaseActivity;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

public class ActivitySolveRequire extends BaseActivity {

    @BindView(R2.id.tv_name_require)
    TextView tv_name;

    @BindView(R2.id.tv_time_start)
    TextView tv_start;

    @BindView(R2.id.tv_time_end)
    TextView tv_end;

    @OnClick(R2.id.bt_agree)
    void onagree(){
        //TODO 网络操作
    }

    @OnClick(R2.id.bt_couju)
    void couju(){
        //TODO 网络操作

    }

    private void setResult(){
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
    }



    @Override
    public int getLayoutid() {
        return R.layout.activity_solve_require;
    }

    @Override
    public void havebind() {

    }


}
