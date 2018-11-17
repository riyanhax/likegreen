package com.xbdl.xinushop.base;


import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.xbdl.xinushop.R;
import com.xbdl.xinushop.utils.Judge;

import static com.lzy.okgo.utils.HttpUtils.runOnUiThread;

/**
 * Created by theWind on 2018/8/1.
 */
public class BaseFragment extends Fragment {
    private static final String TAG = "asdf";



    /**
     * 进度弹窗
     */
    protected ProgressDialog progressDialog = null;

    /**
     * 展示加载进度条,无标题
     */
    public void showLoading() {
        try {
            showLoading("", null);
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
            showLoading(null, getActivity().getResources().getString(stringResId));
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
                    progressDialog = new ProgressDialog(getActivity(), R.style.myDialog2);
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
}
