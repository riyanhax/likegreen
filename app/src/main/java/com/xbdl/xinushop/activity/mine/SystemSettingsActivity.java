package com.xbdl.xinushop.activity.mine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.LoginActivity;
import com.xbdl.xinushop.activity.RegisterActivity;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.base.BasePresenter;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.evnetBus.FinshMain;
import com.xbdl.xinushop.utils.SharedPreferencesUtil;
import com.xbdl.xinushop.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import cn.jpush.android.api.JPushInterface;


public class SystemSettingsActivity extends BaseActivity implements View.OnClickListener {
    private Context mContext;
    private  View mMyToPromote,mSystemPersonalData;
    private PopupWindow popupWindow;
    private  CheckBox wifi,mobileandWifi;

    @Override
    protected Activity getActivity() {
        return SystemSettingsActivity.this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_mysettings);
        mContext=this;
        initView();
        initData();
    }



    private void initView() {
        mSystemPersonalData=findViewById(R.id.system_personal_Data);
        mSystemPersonalData.setOnClickListener(this);
        //我要推广
        mMyToPromote = findViewById(R.id.my_to_promote);
        mMyToPromote.setOnClickListener(this);
        //网络状态
        wifi = (CheckBox)findViewById(R.id.cb_wifi_network);
        mobileandWifi = (CheckBox)findViewById(R.id.cb_mobile_network);

        findViewById(R.id.rl_change_phone).setOnClickListener(this);
        findViewById(R.id.rl_push_msg).setOnClickListener(this);
        findViewById(R.id.rl_user_protocol).setOnClickListener(this);
        findViewById(R.id.rl_exit_logon).setOnClickListener(this);
        findViewById(R.id.rl_about_us).setOnClickListener(this);//关于我们
        findViewById(R.id.rl_change_pwd).setOnClickListener(this);//修改密码
        findViewById(R.id.rl_clean).setOnClickListener(this);//清除缓存
        findViewById(R.id.rl_rommended_to_friends).setOnClickListener(this);//推荐给好友
    }
    private void initData() {
        //wifi下观看视频
        wifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    wifi.setChecked(true);
                    mobileandWifi.setChecked(false);
                    SharedPreferencesUtil.putBoolean(SystemSettingsActivity.this, MyConstants.WIFI_AND_MOBILE,true);
                }else {
                    wifi.setChecked(false);
                    mobileandWifi.setChecked(true);
                    SharedPreferencesUtil.putBoolean(SystemSettingsActivity.this, MyConstants.WIFI_AND_MOBILE,false);
                }
            }
        });
        mobileandWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    wifi.setChecked(false);
                    mobileandWifi.setChecked(true);
                }else {
                    wifi.setChecked(true);
                    mobileandWifi.setChecked(false);
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        UMWeb web = new UMWeb("https://www.baidu.com");
        web.setTitle("快来加入喜绿吧");//标题
         //web.setThumb();  //缩略图
        web.setDescription("快来加入喜绿吧");//描述
        switch (view.getId()){
            case R.id.rl_clean://清除缓存
                clearAppCache();
                break;
            case R.id.my_to_promote:
                showPopuWindow();
                break;
            case R.id.system_personal_Data:
                Intent intentPersonalData = new Intent(this, PersonalDataActivity.class);
                startActivity(intentPersonalData);
                break;
            case R.id.tv_to_promote_cancel:
                popupWindow.dismiss();
                break;
            case R.id.to_to_promte_consulting://立即咨询
                call("18038637318");
                popupWindow.dismiss();
                break;
            case R.id.rl_change_phone://更改手机号
                Intent intentChangePhoneNumActivity = new Intent(this, ChangePhoneNumActivity.class);
                startActivity(intentChangePhoneNumActivity);
                break;
            case R.id.rl_push_msg://消息推送
                Intent intentSetPushMsgActivity = new Intent(this, SetPushMsgActivity.class);
                startActivity(intentSetPushMsgActivity);
                break;
            case R.id.rl_user_protocol://版权说明 用户协议
                Intent intentUserProtocolActivity = new Intent(this, UserProtocolActivity.class);
                startActivity(intentUserProtocolActivity);
                break;
            case R.id.rl_about_us://关于我们
                Intent intentAboutUsActivity = new Intent(this, AboutUsActivity.class);
                startActivity(intentAboutUsActivity);
                break;
            case R.id.rl_exit_logon://退出登录
                //退出极光推送
                JPushInterface.deleteAlias(getActivity(), MyApplication.user.getUserId());
                Intent intentLoginActivity = new Intent(this, LoginActivity.class);
                startActivity(intentLoginActivity);
                SharedPreferencesUtil.putBoolean(this,MyConstants.ISLOGIN,false);
                EventBus.getDefault().post(FinshMain.mian);
                finish();
                break;
            case R.id.rl_change_pwd:
                Intent intentRegisterActivity1 = new Intent(this, RegisterActivity.class);
                intentRegisterActivity1.putExtra("LoginActivity",0);
                startActivity(intentRegisterActivity1);
                break;
            case R.id.rl_rommended_to_friends://推荐给好友
                showRommended();
                break;
            case R.id.ll_share_weixin_circleo:
                new ShareAction(this)
                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//朋友圈
                        .withMedia(web)
                        .setCallback(umShareListener)//回调监听器
                        .share();
                popShare.dismiss();
                break;
            case R.id.ll_share_weixin:

                new ShareAction(this)
                        .setPlatform(SHARE_MEDIA.WEIXIN)//微信
                        .withMedia(web)
                        .setCallback(umShareListener)//回调监听器
                        .share();

                popShare.dismiss();
                break;
            case R.id.ll_share_weibo:
                new ShareAction(this)
                        .setPlatform(SHARE_MEDIA.SINA)//微博
                        .withMedia(web)
                        .setCallback(umShareListener)//回调监听器
                        .share();
                popShare.dismiss();
            case R.id.ll_shareQQ:
                new ShareAction(this)
                        .setPlatform(SHARE_MEDIA.QQ)//微博
                        .withText("hello")//分享内容
                        .setCallback(umShareListener)//回调监听器
                        .share();
                popShare.dismiss();
                break;
        }
    }
    private UMShareListener umShareListener= new UMShareListener() {

        @Override
        public void onStart(SHARE_MEDIA share_media) {
            Log.v("nihaoma","1111111");
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Log.v("nihaoma","2222222 "+share_media.getName());
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            Log.v("nihaoma",throwable+"33333333 "+share_media.getName());
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            Log.v("nihaoma","4444444");
        }
    };
   private PopupWindow popShare;
    //推荐给好友
    private void showRommended() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_share, null);
        contentView.findViewById(R.id.ll_share_weixin_circleo).setOnClickListener(this);
        contentView.findViewById(R.id.ll_share_weixin).setOnClickListener(this);
        contentView.findViewById(R.id.ll_shareQQ).setOnClickListener(this);
        contentView.findViewById(R.id.ll_share_weibo).setOnClickListener(this);
        popShare = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popShare.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popShare.setOutsideTouchable(true);
        popShare.setTouchable(true);
        //设置半透明
        WindowManager.LayoutParams lp = this.getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        this.getWindow().setAttributes(lp);
        //恢复正常
        popShare.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = SystemSettingsActivity.this.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                SystemSettingsActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                SystemSettingsActivity.this.getWindow().setAttributes(lp);
            }
        });
        //popupWindow.showAsDropDown(mMyToPromote, -70, 5);
        popShare.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }

    //调到拨号界面
    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void showPopuWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_to_promote, null);
        TextView popToPromotCancel = contentView.findViewById(R.id.tv_to_promote_cancel);
        popToPromotCancel.setOnClickListener(this);
        TextView popToPromotConsulting = contentView.findViewById(R.id.to_to_promte_consulting);
        popToPromotConsulting.setOnClickListener(this);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        //设置半透明
        WindowManager.LayoutParams lp = this.getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        this.getWindow().setAttributes(lp);
        //恢复正常
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = SystemSettingsActivity.this.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                SystemSettingsActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                SystemSettingsActivity.this.getWindow().setAttributes(lp);
            }
        });
        //popupWindow.showAsDropDown(mMyToPromote, -70, 5);
        popupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }

    /**
     * 清除app缓存
     */
    public static boolean isMethodsCompat(int VersionCode) {
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        return currentVersion >= VersionCode;
    }
    public void myclearaAppCache() {
        //清除webview缓存
        File file = getCacheDir();

        //先删除WebViewCache目录下的文件
        if (file != null && file.exists() && file.isDirectory()) {
            for (File item : file.listFiles()) {
                item.delete();
            }
            file.delete();
        }
        //清除数据缓存
        clearCacheFolder(getFilesDir(), System.currentTimeMillis());
        clearCacheFolder(getCacheDir(), System.currentTimeMillis());
        //清除数据缓存
        clearCacheFolder(getFilesDir(), System.currentTimeMillis());
        clearCacheFolder(getCacheDir(), System.currentTimeMillis());
        //2.2版本才有将应用缓存转移到sd卡的功能
        if (isMethodsCompat(android.os.Build.VERSION_CODES.FROYO)) {
            clearCacheFolder(getExternalCacheDir(), System.currentTimeMillis());
        }
    }

    private int clearCacheFolder(File dir, long curTime) {
        int deletedFiles = 0;
        if (dir != null && dir.isDirectory()) {
            try {
                for (File child : dir.listFiles()) {
                    if (child.isDirectory()) {
                        deletedFiles += clearCacheFolder(child, curTime);
                    }
                    if (child.lastModified() < curTime) {
                        if (child.delete()) {
                            deletedFiles++;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deletedFiles;
    }

    /**
     * 清除app缓存
     *
     * @param
     */
    public void clearAppCache() {

        new Thread() {
            @Override
            public void run() {
                Message msg = new Message();
                try {
                    myclearaAppCache();
                    msg.what = CLEAN_SUC;
                } catch (Exception e) {
                    e.printStackTrace();
                    msg.what = CLEAN_FAIL;
                }
                handler.sendMessage(msg);
            }
        }.start();
    }

    private final int CLEAN_SUC = 1001;
    private final int CLEAN_FAIL = 1002;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case CLEAN_FAIL:
                    ToastUtil.shortToast(SystemSettingsActivity.this,"清除失败");
                    break;
                case CLEAN_SUC:
                    ToastUtil.shortToast(SystemSettingsActivity.this, "清除成功");
                    break;
            }
        }
    };
}
