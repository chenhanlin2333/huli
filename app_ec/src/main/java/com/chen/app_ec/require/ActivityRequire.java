package com.chen.app_ec.require;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;


import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chen.app_core.app.AppCore;
import com.chen.app_core.app.BaseActivity;
import com.chen.app_core.app.ConfigType;
import com.chen.app_core.net.RestCilent;
import com.chen.app_core.net.callback.IError;
import com.chen.app_core.net.callback.ISuccess;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;
import com.chen.app_ec.loginandregister.FragmentRegister;
import com.chen.app_ec.require.javabean.RequireDate;
import com.chen.app_ec.user.User;

import java.util.Calendar;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;


public class ActivityRequire extends BaseActivity implements DatePickerDialog.OnDateSetListener {

    @BindView(R2.id.tl_back)
    Toolbar mToolbar;

    @BindView(R2.id.tv_time_start)
    TextView tv_start;

    @BindView(R2.id.tv_time_end)
    TextView tv_end;

    DatePickerDialog mDatePickerDialog ;

    @OnClick(R2.id.img_start_add)
    void onclicstart(){
        isstart = true;
        mDatePickerDialog.show();
    }

    boolean isstart = true;

    @OnClick(R2.id.img_end_add)
    void onclicend(){
        isstart = false;
        mDatePickerDialog.show();
    }

    @OnClick(R2.id.bt_login)
    void oncickrequire(){
        //TODO 网络申请

        RequireDate requireDate = new RequireDate();
        requireDate.setDate1(dayStart);
        requireDate.setDate2(dayEnd);
        requireDate.setTag(User.getInstance().getType());
        requireDate.setUserId(User.getInstance().getID());
        String raw = JSON.toJSONString(requireDate);

        String baseurl =  (String) AppCore.getConfigurations().get(ConfigType.API_HOST.name());
        RestCilent.builder()
                .url(baseurl+"date/apply")
                .raw(raw)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(ActivityRequire.this,result+"申请成功",Toast.LENGTH_SHORT).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int id, String mesg) {
                        Toast.makeText(ActivityRequire.this,mesg,Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .post();

    }

    private Calendar mCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_require;
    }

    @Override
    public void havebind() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        if (User.getInstance().getType() == User.OLDMAN){
            mToolbar.setTitle("申请请假");
        }else {
            mToolbar.setTitle("申请探访");
        }

        mCalendar = Calendar.getInstance();
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        String date = ""+year +"-"+(month+1)+"-"+day;
        dayStart = FragmentRegister.getDate(year,month,day);
        dayEnd = FragmentRegister.getDate(year,month,day);
        tv_start.setText(date);
        tv_end.setText(date);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mDatePickerDialog = new DatePickerDialog(this,this,year,month,day);
        }

    }

    private String dayStart;
    private String dayEnd;

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String date = ""+i +"-"+(i1+1)+"-"+i2;
        if (isstart){
            dayStart = FragmentRegister.getDate(i,i1,i2);
            tv_start.setText(date);
        }else {
            dayEnd  = FragmentRegister.getDate(i,i1,i2);
            tv_end.setText(date);
        }
    }

    public static  void start(Activity activity){
        Intent intent = new Intent(activity,ActivityRequire.class);
        activity.startActivity(intent);
    }

}
