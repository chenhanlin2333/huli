package com.chen.app_ec.require.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chen.app_ec.R;
import com.chen.app_ec.require.data.Require;

import java.util.List;

public class RecleradapterRequire  extends BaseQuickAdapter<Require, BaseViewHolder> {
    public RecleradapterRequire(int layoutResId, @Nullable List<Require> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Require item) {
        helper.setText(R.id.tv_name_require,item.getName());
        helper.setText(R.id.tv_start,item.getDaystrat());
        helper.setText(R.id.tv_end,item.getDayend());
    }
}
