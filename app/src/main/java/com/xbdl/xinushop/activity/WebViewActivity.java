package com.xbdl.xinushop.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;

import org.json.JSONException;
import org.json.JSONObject;

public class WebViewActivity extends AppCompatActivity {
   private int intUrl;//0购物车  1订单
   private  WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Intent intent = getIntent();
        intUrl= intent.getIntExtra("webview", 0);
        webview = findViewById(R.id.webview);
     /*   findViewById(R.id.iv_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
        initData();
    }

    private void initData() {
        switch (intUrl){
            case 0:
                //例如：加载assets文件夹下的test.html页面
                webview.loadUrl("file:///android_asset/index.html#/shoppingCart");
                break;
            case 1:
                //例如：加载assets文件夹下的test.html页面
                webview.loadUrl("file:///android_asset/test.html");
                break;
        }
        //设置在app打开流量器
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webview.loadUrl(url);
                return true;
            }
    });

        webview.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        WebSettings ws = webview.getSettings();
        //设置为可调用js方法
        ws.setJavaScriptEnabled(true);

        // 是否允许脚本支持
        ws.setJavaScriptEnabled(true);
        ws.setJavaScriptCanOpenWindowsAutomatically(true);
        ws.setPluginState(WebSettings.PluginState.ON);
        ws.setAppCacheMaxSize(10240);
        // 设置 缓存模式
        ws.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        ws.setDomStorageEnabled(true);
        ws.setAllowFileAccess(true);
        ws.setLoadWithOverviewMode(true);
        ws.setUseWideViewPort(true);
        //js中的方法
        /*webview.evaluateJavascript("", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                finish();
            }
        });*/

        //js调用Android本地Java方法
        webview.addJavascriptInterface(new JsInteration(), "android");


    }
    public class JsInteration {
        @JavascriptInterface
        public String userMsg() {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("state",1);
                jsonObject.put("token", MyApplication.user.getLoginToken());
                jsonObject.put("userId",MyApplication.user.getUserId());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.v("nihaoma",jsonObject.toString());
            return jsonObject.toString();
        }
        @JavascriptInterface
        public String colseActivity() {
            finish();
            return "1";
        }
    }
}
