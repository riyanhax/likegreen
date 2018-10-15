package com.xbdl.xinushop.fragment.home;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.base.BaseFragment;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.utils.SharedPreferencesUtil;
import com.xbdl.xinushop.view.BackHandledFragment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by theWind on 2018/8/1.
 */

public class HomeFindFragment extends BackHandledFragment {
    String token = "";
    WebView myWebView;
ProgressBar pb;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_find, container, false);
        view.findViewById(R.id.view_line).setVisibility(View.GONE);
        String userjson = SharedPreferencesUtil.getString(getActivity(), MyConstants.User, "");
        
        PersonBean personBean = new Gson().fromJson(userjson, PersonBean.class);
        token = personBean.getLoginToken();
        initView(view);

        return view;
    }


    private void initView(View v) {
        pb=v.findViewById(R.id.pb);
        myWebView = v.findViewById(R.id.webview);
        initWebViewSettings();
    }

    /**
     * init WebView Settings
     */
    private void initWebViewSettings() {

//String urls="file:///android_asset/index.html";
      String urls = "file:///android_asset/index.html";
      //String urls = "file:///android_asset/index.html#/shoppingCart";
     //   String urls = "http://www.baidu.com";
//String urls="http://mall.depforlive.com/test/hybrid.php";
        myWebView.loadUrl(urls);


        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                view.loadUrl(url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);


            }
        });
        myWebView.getSettings().setJavaScriptEnabled(true);


        // 设置编码
        myWebView.getSettings().setDefaultTextEncodingName("utf-8");
        // 支持js
        WebSettings ws = myWebView.getSettings();
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

        myWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        // webview.addJavascriptInterface(MyWealthActivity.this, "android");
        myWebView.addJavascriptInterface(new FamilyPowerRank(), "android");
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.setWebChromeClient(new MyWebviewChromeClient());
    }


    /**
     * JS调用android的方法
     *
     * @param str
     * @return
     */
    @JavascriptInterface //仍然必不可少
    public void getClient(String str) {
        Log.i("ansen", "html调用客户端:" + str);
    }

    class MyWebviewChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView webView, int i) {
            if (i == 100) {
                pb.setVisibility(View.GONE);
            } else {
                    pb.setVisibility(View.VISIBLE);
            }


            super.onProgressChanged(webView, i);
        }


    }

    private class FamilyPowerRank {
        @JavascriptInterface
        public String getSession() {

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("token", MyApplication.user.getLoginToken());
            } catch (JSONException e) {

            }

            return jsonObject.toString();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


        myWebView.clearHistory();
        myWebView.clearMatches();
        myWebView.clearCache(true);
        myWebView.destroy();
    }
    @Override
    public boolean onBackPressed() {
        if(myWebView.canGoBack()){
            myWebView.goBack();
            Log.v("webView.goBack()", "webView.goBack()");
            return true;

        }else{
            Log.v("Conversatio退出","Conversatio退出");
            return false;
        }
    }
}


















/*    private void initView(View v) {
        tabLayout = (SlidingTabLayout)v.findViewById(R.id.st_home_find);
        viewPager = (ViewPager)v.findViewById(R.id.viewpager_home_find);
        equipment = (ImageView)v.findViewById(R.id.iv_find_equipment);
        RadioGroup radioGroup = (RadioGroup)v.findViewById(R.id.rg_find);
         selected =(RadioButton) v.findViewById(R.id.selected);
         forum =(RadioButton) v.findViewById(R.id.forum);
         shoppingmall =(RadioButton) v.findViewById(R.id.shoppingmall);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                setTab(checkedId);
            }
        });
    }*/





/*    private void initFragment() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (selectedFragment == null) {
            selectedFragment = new SelectedFragment();
            transaction.add(R.id.fragment_container, selectedFragment);
        }
        transaction.commitAllowingStateLoss();
    }*/



/*    public void setTab(int tab) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (tab){
            case R.id.selected:
                if (selectedFragment == null) {
                    selectedFragment = new SelectedFragment();
                    transaction.add(R.id.fragment_container, selectedFragment);
                } else {
                    transaction.show(selectedFragment);
                }
                break;
            case R.id.forum:
                if (forumFragment == null) {
                    forumFragment = new ForumFragment();
                    transaction.add(R.id.fragment_container, forumFragment);
                } else {
                    transaction.show(forumFragment);
                }
                break;
            case R.id.shoppingmall:
                if (shoppingMallFragment == null) {
                    shoppingMallFragment = new ShoppingMallFragment();
                    transaction.add(R.id.fragment_container, shoppingMallFragment);
                } else {
                    transaction.show(shoppingMallFragment);
                }
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        if (selectedFragment != null) {
            transaction.hide(selectedFragment);
        }
        if (forumFragment != null) {
            transaction.hide(forumFragment);
        }
        if (shoppingMallFragment != null) {
            transaction.hide(shoppingMallFragment);
        }

        selected.setChecked(false);
        forum.setChecked(false);
        shoppingmall.setChecked(false);
    }*/

