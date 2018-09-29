package com.xbdl.xinushop.activity.mine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.xbdl.xinushop.R;
/*
* 版权说明用户协议
* */
public class UserProtocolActivity extends AppCompatActivity {
    private String URL="file:///android_asset/usercode.html";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_protocol);
        WebView webView = (WebView)findViewById(R.id.user_webview);
        webView.loadUrl(URL);
    }
}
