package com.chen.app_ec.leaveword;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.chen.app_core.app.AppCore;
import com.chen.app_core.app.BaseActivity;
import com.chen.app_core.app.ConfigType;
import com.chen.app_core.net.RestCilent;
import com.chen.app_core.net.callback.IError;
import com.chen.app_core.net.callback.ISuccess;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;
import com.chen.app_ec.leaveword.adapter.ReclycleAdapterDuihua;
import com.chen.app_ec.leaveword.data.JavaNewNews;
import com.chen.app_ec.leaveword.javabean.JavaNews;
import com.chen.app_ec.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ActivityLeaveword extends BaseActivity implements TabLayout.BaseOnTabSelectedListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String[] titlesofold = new String[]{"家属","护工"};
    private static final String[] titlesofrelative = new String[]{"老人","护工"};
    private static final String[] titlesofhelp = new String[]{"家属","老人"};



    @BindView(R2.id.rc_leaveword)
    RecyclerView rc_duihua;

    @BindView(R2.id.et_duihua)
    EditText et_duihua;

    @BindView(R2.id.et_maksure)
    Button et_makesure;

    @BindView(R2.id.tl_nav)
    TabLayout mTabLayout;

    @BindView(R2.id.srl_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R2.id.tl_back)
    Toolbar mToolbar;


    ReclycleAdapterDuihua mRecycleDayAdapter ;
    ArrayList<JavaNews> duihus ;

    @OnClick(R2.id.et_maksure)
    void makesure(){
        String content = et_duihua.getText().toString();
        //TODO 对话
        InputMethodManager methodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        methodManager.hideSoftInputFromWindow(et_makesure.getWindowToken(),0);
        et_duihua.setText("");
        JavaNewNews javaNewNews = new JavaNewNews();
        javaNewNews.setSendId(sendid);
        javaNewNews.setReceiveId(reciveid);
        javaNewNews.setContent(content);
        String raw = JSON.toJSONString(javaNewNews);
        RestCilent.builder()
                .url((String) AppCore.getConfigurations().get(ConfigType.API_HOST.name())+  "msg/send")
                .raw(raw)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(ActivityLeaveword.this,"成功"+result, Toast.LENGTH_SHORT).show();


                        getNews();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int id, String mesg) {
                        Toast.makeText(ActivityLeaveword.this,"失败"+id,Toast.LENGTH_SHORT).show();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                })
                .build()
                .post();
    }

    private int sendid;
    private int reciveid;




    private void chanagereciveid(String name){
        if (name.equals("家属")){
            reciveid = User.getInstance().getRelativeid();
        }else if(name.equals("护工")){
            reciveid = User.getInstance().getHelpid();
        }else {
            reciveid = User.getInstance().getOldmanid();
        }
    }



    @Override
    public int getLayoutid() {
        return R.layout.activity_leaveword;
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


        String[] title = new String[0];
        if (User.getInstance().getType() == User.OLDMAN){
            title = titlesofold;
        }else if (User.getInstance().getType() == User.HELPPEOPLE){
            title = titlesofhelp;
        }else if (User.getInstance().getType() == User.RELATIVE_PEOPLE){
            title = titlesofrelative;
        }
        for (int i = 0; i < title.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(title[i]));
        }
        duihus = new ArrayList<>();


        //id 确认
        sendid = User.getInstance().getID();
        String name = mTabLayout.getTabAt(0).getText().toString();
        // 改变接收方id
        chanagereciveid(name);
        mRecycleDayAdapter = new ReclycleAdapterDuihua(this,duihus,reciveid,sendid);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rc_duihua.setLayoutManager(manager);
        rc_duihua.setAdapter(mRecycleDayAdapter);

        mTabLayout.addOnTabSelectedListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_light));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        getNews();

    }


    public static void strat(Context context){
        Intent intent = new Intent(context,ActivityLeaveword.class);
        context.startActivity(intent);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        String name = tab.getText().toString();
        chanagereciveid(name);
        // TODO 网络操作
        getNews();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onRefresh() {
        getNews();
    }

    private void getNews(){
        HashMap hashMap = new HashMap();
        hashMap.put("sendId",sendid);
        hashMap.put("receiveId",reciveid);
        mRecycleDayAdapter.setGiveid(sendid);
        mRecycleDayAdapter.setReciverid(reciveid);
        RestCilent.builder()
                .url((String) AppCore.getConfigurations().get(ConfigType.API_HOST.name())+ "msg/getChatting")
                .params(hashMap)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(ActivityLeaveword.this,"成功"+result, Toast.LENGTH_SHORT).show();
                        String mesage = JSON.parseObject(result).getString("message");
                        if (!mesage.equals("ok")){
//                            Toast.makeText(ActivityLeaveword.this,mesage,Toast.LENGTH_SHORT);
                            return;
                        }



                        JSONArray array = JSON.parseObject(result).getJSONArray("result");
                        final List<JavaNews> data = JSON.parseArray(array.toJSONString(), JavaNews.class);
                        duihus.clear();
                        duihus.addAll(data);
                        mRecycleDayAdapter.notifyDataSetChanged();
                        rc_duihua.scrollToPosition(data.size()-1);
                        mSwipeRefreshLayout.setRefreshing(false);

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int id, String mesg) {
                        Toast.makeText(ActivityLeaveword.this,"失败"+id,Toast.LENGTH_SHORT).show();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                })
                .build()
                .get();

    }


}
