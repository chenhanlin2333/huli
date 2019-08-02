package com.chen.app_ec.app_main_activity.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chen.app_ec.R;
import com.chen.app_ec.anpai.DateArrange;
import com.chen.app_ec.app_main_activity.data.DayAction;

import java.util.List;

public class RecycleDayAdapter extends BaseQuickAdapter<DateArrange, BaseViewHolder> {


    public RecycleDayAdapter(int layoutResId, @Nullable List<DateArrange> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DateArrange item) {
        helper.setText(R.id.day_start,item.getDate1());
        helper.setText(R.id.day_end,item.getDate2());
        String s = "";
        if (item.getTag() == 0){
            s = "老人休息";
        }else if (item.getTag() == 1){
            s = "家人探访";
        }
        helper.setText(R.id.tv_content_day,s);
    }
}
