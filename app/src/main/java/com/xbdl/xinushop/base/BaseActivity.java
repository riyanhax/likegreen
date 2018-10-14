package com.xbdl.xinushop.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.xbdl.xinushop.BroadcastReceiver.NetWorkStateReceiver;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.utils.Judge;
import com.xbdl.xinushop.view.LoadingDialog;

import butterknife.ButterKnife;


/**
 * Created by theWind on 2018/8/3.
 */

public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 该Activity实例，命名为context是因为大部分方法都只需要context，写成context使用更方便
     *
     * @warn 不能在子类中创建
     */
    protected BaseActivity context = null;
    private static final String TAG = "asdf";
    protected LoadingDialog loadingDialog;
    public NetWorkStateReceiver netWorkStateReceiver;
    protected abstract Activity getActivity();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        context = (BaseActivity) getActivity();
    }

    //在onResume()方法注册
    @Override
    protected void onResume() {
        if (netWorkStateReceiver == null) {
            netWorkStateReceiver = new NetWorkStateReceiver();

        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkStateReceiver, filter);

        super.onResume();
    }

    //onPause()方法注销
    @Override
    protected void onPause() {
        unregisterReceiver(netWorkStateReceiver);

        super.onPause();
    }


    /**
     * 进度弹窗
     */
    protected ProgressDialog progressDialog = null;

    /**
     * 展示加载进度条,无标题
     */
    public void showLoading() {
        try {
            showLoading(null, null);
        } catch (Exception e) {
            Log.e(TAG, "showProgressDialog  showProgressDialog(null, context.getResources().getString(stringResId));");
        }
    }

    /**
     * 展示加载进度条,无标题
     *
     * @param stringResId
     */
    public void showLoading(int stringResId) {
        try {
            showLoading(null, context.getResources().getString(stringResId));
        } catch (Exception e) {
            Log.e(TAG, "showProgressDialog  showProgressDialog(null, context.getResources().getString(stringResId));");
        }
    }

    /**
     * 展示加载进度条,无标题
     *
     * @param message
     */
    public void showLoading(String message) {
        showLoading(null, message);
    }

    /**
     * 展示加载进度条
     *
     * @param title   标题
     * @param message 信息
     */
    public void showLoading(final String title, final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (progressDialog == null) {
                    progressDialog = new ProgressDialog(context, R.style.myDialog2);
                }
                if (progressDialog.isShowing() == true) {
                    progressDialog.dismiss();
                }
                if (!Judge.getBoolean_isNull(title)) {
                    progressDialog.setTitle(title);
                }
                if (!Judge.getBoolean_isNull(message)) {
                    progressDialog.setMessage(message);
                }
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            }
        });
    }


    /**
     * 隐藏加载进度
     */
    public void dismissLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //把判断写在runOnUiThread外面导致有时dismiss无效，可能不同线程判断progressDialog.isShowing()结果不一致
                if (progressDialog == null || progressDialog.isShowing() == false) {
                    Log.w(TAG, "dismissProgressDialog  progressDialog == null" +
                            " || progressDialog.isShowing() == false >> return;");
                    return;
                }
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
