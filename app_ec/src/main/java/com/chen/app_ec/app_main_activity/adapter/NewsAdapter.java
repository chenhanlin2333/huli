package com.chen.app_ec.app_main_activity.adapter;


import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chen.app_ec.R;
import com.chen.app_ec.app_main_activity.data.News;

import java.util.List;

public class NewsAdapter extends BaseQuickAdapter<News, BaseViewHolder> {


    public NewsAdapter(int layoutResId, @Nullable List<News> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, News item) {
        helper.setText(R.id.tv_text,item.getText());
    }

}
