package com.chen.app_ec.app_main_activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chen.app_core.app.BaseFragment;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;
import com.chen.app_ec.anpai.ActivityDayarrange;
import com.chen.app_ec.app_main_activity.adapter.AdapterOncllicListener;
import com.chen.app_ec.app_main_activity.adapter.RecyclerAdapter_action;
import com.chen.app_ec.leaveword.ActivityLeaveword;
import com.chen.app_ec.oldmaninfo.ActivityOldManInfo;
import com.chen.app_ec.require.ActivityRequire;
import com.chen.app_ec.user.User;

import java.util.ArrayList;

import butterknife.BindView;

public class FragmentManage extends BaseFragment {

    public static final String ACTION_0 = "今日安排";
    public static final String ACTION_1 = "留言管理";
    public static final String ACTION_2 = "老人信息";
    public static final String ACTION_3 = "探访申请";
    public static final String ACTION_4 = "请假申请";


    @BindView(R2.id.tv_name_manage)
    TextView tv_name_mange;


    @BindView(R2.id.rv_action)
    RecyclerView mRecyclerView_action;

    ArrayList<String > actions = new ArrayList<>();
    RecyclerAdapter_action adapter;
    GridLayoutManager mGridLayoutManager;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        actions.add(ACTION_0);
        actions.add(ACTION_1);
        if (User.getInstance().getType() == User.HELPPEOPLE){
            actions.add(ACTION_2);
            actions.add(ACTION_3);
        }else if (User.getInstance().getType() == User.RELATIVE_PEOPLE){
            actions.add(ACTION_2);
            actions.add(ACTION_3);
        }else if (User.getInstance().getType() == User.OLDMAN){
            actions.add(ACTION_4);
        }
        mGridLayoutManager = new GridLayoutManager(getContext(),2);
        adapter = new RecyclerAdapter_action(getContext());
        adapter.setData(actions);


        adapter.setOnClickListener(new AdapterOncllicListener() {
            @Override
            public void onClick(View view, int postition) {
               TextView tv_action = view.findViewById(R.id.tv_action);
               String s = tv_action.getText().toString();
               if (s.equals(ACTION_0)){
                   ActivityDayarrange.start(getActivity());
               }else if (s.equals(ACTION_1)){
                   ActivityLeaveword.strat(getActivity());
               }else if (s.equals(ACTION_2)){
                   ActivityOldManInfo.start(getActivity());
               }else if (s.equals(ACTION_3)){
                   ActivityRequire.start(getActivity());
               }else if (s.equals(ACTION_4)){
                   ActivityRequire.start(getActivity());
               }
            }
        });
        mRecyclerView_action.setAdapter(adapter);
        mRecyclerView_action.setLayoutManager(mGridLayoutManager);

    }



    @Override
    public void bindview(Bundle savedInstance, View rootview) {
        User user = User.getInstance();
        tv_name_mange.setText(user.getName()+",你好");
    }

    @Override
    public Object setlayout() {
        return R.layout.fragment_manage;
    }
}
