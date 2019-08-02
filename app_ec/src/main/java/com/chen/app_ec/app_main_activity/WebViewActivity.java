package com.chen.app_ec.app_main_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.chen.app_core.app.BaseActivity;
import com.chen.app_ec.R;

public class WebViewActivity extends BaseActivity {

    WebView mWebView;
    public static final String URL = "url";

    @Override
    public int getLayoutid() {
        return R.layout.activity_web_view;
    }

    @Override
    public void havebind() {
        Intent intent  = getIntent();
        String url = intent.getStringExtra(URL);
        mWebView = findViewById(R.id.web);
        mWebView.loadUrl(url);
    }
}
