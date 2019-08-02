package com.chen.app_ec.anpai;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chen.app_core.app.AppCore;
import com.chen.app_core.app.BaseActivity;
import com.chen.app_core.app.ConfigType;
import com.chen.app_core.net.RestCilent;
import com.chen.app_core.net.callback.IError;
import com.chen.app_core.net.callback.ISuccess;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;
import com.chen.app_ec.app_main_activity.adapter.RecycleDayAdapter;
import com.chen.app_ec.app_main_activity.adapter.RecyclerAdapter_action;
import com.chen.app_ec.app_main_activity.data.DayAction;
import com.chen.app_ec.leaveword.javabean.JavaNews;
import com.chen.app_ec.require.ActivityRequire;
import com.chen.app_ec.user.User;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class ActivityDayarrange extends BaseActivity {

    @BindView(R2.id.rc_day)
    RecyclerView rc_day;

    @BindView(R2.id.tl_back)
    Toolbar mToolbar;


    LinearLayoutManager mLinearLayoutManager;
    RecycleDayAdapter mRecycleDayAdapter;
    ArrayList<DateArrange> mDayActions;



    @Override
    public int getLayoutid() {
        return R.layout.activity_dayarrange;
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
        mDayActions = new ArrayList<>();
        mLinearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecycleDayAdapter = new RecycleDayAdapter(R.layout.itemview_day,mDayActions);
        rc_day.setLayoutManager(mLinearLayoutManager);
        rc_day.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        rc_day.setAdapter(mRecycleDayAdapter);
        getData();
    }

    private void getData(){
        String baseurl =  (String) AppCore.getConfigurations().get(ConfigType.API_HOST.name());
        HashMap<String,Object> map = new HashMap();
        map.put("id", User.getInstance().getID());
        RestCilent.builder()
                .url(baseurl+"date/get")
                .params(map)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String result) {
                        if (result != null){
//                            Toast.makeText(ActivityDayarrange.this,result,Toast.LENGTH_SHORT);
                            JSONArray array = JSON.parseObject(result).getJSONArray("result");
                            final List<DateArrange> data = JSON.parseArray(array.toJSONString(),DateArrange.class);
                            mDayActions.clear();
                            for (int i = 0; i < data.size(); i++) {
                                mDayActions.add(data.get(i));
                            }
                            mRecycleDayAdapter.notifyDataSetChanged();
                        }
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int id, String mesg) {
                        Toast.makeText(ActivityDayarrange.this,mesg,Toast.LENGTH_SHORT);
                    }
                })
                .build()
                .get();

    }


    public static void start(Activity activity){
        Intent intent = new Intent(activity,ActivityDayarrange.class);
        activity.startActivity(intent);
    }
}
