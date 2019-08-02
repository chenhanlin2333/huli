package com.chen.app_core.app;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    Unbinder mUnbinder = null;
    public abstract void bindview(Bundle savedInstance, View rootview);
    public abstract Object setlayout();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        if (setlayout() instanceof Integer){
            view = inflater.inflate((Integer) setlayout(),container,false);
        }
        if (setlayout() instanceof View){
            view = (View) setlayout();
        }
        if (view != null){
            mUnbinder = ButterKnife.bind(this,view);
            bindview(savedInstanceState,view);
        }
        return view;
    }


    @Override
    public void onDestroy() {
        if (mUnbinder!=null) {
            mUnbinder.unbind();
        }
        super.onDestroy();
    }

}
