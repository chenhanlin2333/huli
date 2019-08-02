package com.chen.app_ec.require;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chen.app_core.app.BaseActivity;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;
import com.chen.app_ec.require.adapter.RecleradapterRequire;
import com.chen.app_ec.require.data.Require;

import java.util.ArrayList;

import butterknife.BindView;

public class ActivityRequireSum extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R2.id.rc_require)
    RecyclerView mRecyclerView;
    ArrayList<Require> mArrayList ;

    RecleradapterRequire mRecleradapterRequire;

    public static final int REQUIRE = 1;


    @Override
    public int getLayoutid() {
        return R.layout.activity_require_sum;
    }

    @Override
    public void havebind() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecleradapterRequire = new RecleradapterRequire(R.layout.iteview_require,mArrayList);
        mRecleradapterRequire.setOnItemClickListener(this);
        //TODO 网络请求
        mRecyclerView.setAdapter(mRecleradapterRequire);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent(this,ActivityRequire.class);
        startActivityForResult(intent,REQUIRE);
    }
}
