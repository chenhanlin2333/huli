package com.chen.app_ec.app_main_activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chen.app_core.app.BaseFragment;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;
import com.chen.app_ec.app_main_activity.adapter.NewsAdapter;
import com.chen.app_ec.app_main_activity.data.News;

import java.util.ArrayList;

import butterknife.BindView;

public class FragmentNews extends BaseFragment {

    @BindView(R2.id.rv_cycle)
    RecyclerView rv_new;
    NewsAdapter mNewsAdapter ;
    ArrayList<News> mNews ;
    LinearLayoutManager mLinearLayoutManager;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mNews = new ArrayList<>();
        mNewsAdapter = new NewsAdapter(R.layout.itemview_new,mNews);
        rv_new.setAdapter(mNewsAdapter);
        mLinearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rv_new.setLayoutManager(mLinearLayoutManager);
        mNewsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(),WebViewActivity.class);
                intent.putExtra(WebViewActivity.URL,mNews.get(position).getUrl());
                startActivity(intent);
            }
        });
        getData();
    }

    public void getData(){
        mNews.clear();
        String[] data = getResources().getStringArray(R.array.url_new);
        for (int i = 0; i < data.length; i++) {
            String[] result = data[i].split("\\$\\$");
            mNews.add(new News(result[1],result[0]));
        }
        mNewsAdapter.notifyDataSetChanged();
    }


    @Override
    public Object setlayout()
    {
        return R.layout.fragment_new;
    }

    @Override
    public void bindview(Bundle savedInstance, View rootview) {

    }

}
