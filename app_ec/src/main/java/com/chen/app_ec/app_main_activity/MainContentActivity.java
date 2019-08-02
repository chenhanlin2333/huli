package com.chen.app_ec.app_main_activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chen.app_core.app.BaseActivity;
import com.chen.app_ec.R;
import com.chen.app_ec.R2;
import com.chen.app_ec.app_main_activity.adapter.MyViewPageAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class MainContentActivity extends BaseActivity {

    @BindView(R2.id.vp_fragment_container)
    ViewPager mViewPager;

    @BindView(R2.id.tl_nav)
    TabLayout mTlnav;

    private ArrayList<String> mTitlelist = new ArrayList<>();
    private ArrayList<Integer> mIndectorList = new ArrayList<>();
    MyViewPageAdapter adapter ;

    ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    public void havebind() {
        initTablayout();
    }


    private void initTablayout(){
        mTitlelist.add("管理");
        mTitlelist.add("新闻");
        mTitlelist.add("资料");

        mIndectorList.add(R.drawable.rb_manage_select);
        mIndectorList.add(R.drawable.rb_new_select);
        mIndectorList.add(R.drawable.rb_person_select);

        mFragments.add(new FragmentManage());
        mFragments.add(new FragmentNews());
        mFragments.add(new FragmentMe());


        adapter = new MyViewPageAdapter(getSupportFragmentManager());
        adapter.setFragments(mFragments);
        mViewPager.setAdapter(adapter);
        mTlnav.setupWithViewPager(mViewPager);
        for (int i = 0; i < mTlnav.getTabCount(); i++) {
            TabLayout.Tab tab = mTlnav.getTabAt(i);
            if (tab != null){
                tab.setCustomView(getTabView(mTitlelist.get(i),mIndectorList.get(i)));
            }
        }

        mTlnav.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ImageView imageView = tab.getCustomView().findViewById(R.id.imageview);
                TextView textView = tab.getCustomView().findViewById(R.id.textview);
                textView.setTextColor(Color.parseColor("#ffffbb33"));
                imageView.setSelected(true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ImageView imageView = tab.getCustomView().findViewById(R.id.imageview);
                imageView.setSelected(false);
                TextView textView = tab.getCustomView().findViewById(R.id.textview);
                textView.setTextColor(Color.parseColor("#a1a1a1"));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mViewPager.setCurrentItem(1);
        mViewPager.setOffscreenPageLimit(3);
    }


    public View getTabView(String title, int src) {
        View v = LayoutInflater.from(this).inflate(R.layout.tab_item_view, null);
        TextView textView =  v.findViewById(R.id.textview);
        textView.setText(title);
        ImageView imageView =  v.findViewById(R.id.imageview);
        imageView.setImageResource(src);
        return v;
    }

    public static void start(Activity activity){
        Intent intent = new Intent(activity, MainContentActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_main_content;
    }
}
