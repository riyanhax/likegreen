package com.xbdl.xinushop.fragment.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.Gson;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.WebViewActivity;
import com.xbdl.xinushop.activity.mine.AuditAndLiveActivity;
import com.xbdl.xinushop.activity.mine.DiscountCouponActivity;

import com.xbdl.xinushop.activity.mine.MyFocuseActivity;
import com.xbdl.xinushop.activity.mine.MyMessageAndDirectActivity;
import com.xbdl.xinushop.activity.mine.MyProductsReleaseActivity;
import com.xbdl.xinushop.activity.mine.PersonalDataActivity;

import com.xbdl.xinushop.activity.mine.SystemSettingsActivity;
import com.xbdl.xinushop.activity.mine.wallet.MyWalletActivity;
import com.xbdl.xinushop.base.BaseFragment;

import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.fragment.mine.MyGardenFragment;
import com.xbdl.xinushop.fragment.mine.MyLiveFragment;
import com.xbdl.xinushop.fragment.mine.MyVideoFragment;
import com.xbdl.xinushop.fragment.mine.MyWeddingCardFragment;

import com.xbdl.xinushop.utils.FastBlurUtility;
import com.xbdl.xinushop.utils.HttpUtils2;

import com.xbdl.xinushop.utils.SharedPreferencesUtil;

import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by theWind on 2018/8/1.
 */

public class HomeMyFragment extends BaseFragment implements View.OnClickListener {

    private SlidingTabLayout mStMy;
    private ViewPager mViewpagerMy;
    private View mMyMore, mSystemSetting, mMsgDriect;
    private String[] items = new String[]{"视频", "直播", "花园", "喜帖"};
    private PopupWindow popupWindow;
    private TextView mMyfocuse,mMyfans,huozanshu;
    private CircleImageView headIcon;//头像
    private ImageView iv_top_icon;
    private TextView username,tv_my_word;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_my, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View v) {
        mStMy = (SlidingTabLayout) v.findViewById(R.id.st_my);
        mViewpagerMy = (ViewPager) v.findViewById(R.id.viewpager_home_my);
        mMyMore = v.findViewById(R.id.my_more);//弹popuwindow
        mSystemSetting = v.findViewById(R.id.system_setting);//系统设置
        mMsgDriect = v.findViewById(R.id.msg_driect);//消息私信
        mMyfocuse = (TextView)v.findViewById(R.id.my_home_focuse);//关注
        mMyfans = (TextView)v.findViewById(R.id.my_home_fans);//粉丝
        huozanshu = v.findViewById(R.id.tv_huozanshu);
        huozanshu.setOnClickListener(this);//获赞数
        headIcon =(CircleImageView) v.findViewById(R.id.iv_my_head);//头像
        mMyMore.setOnClickListener(this);
        mSystemSetting.setOnClickListener(this);
        mMsgDriect.setOnClickListener(this);
        mMyfocuse.setOnClickListener(this);
        mMyfans.setOnClickListener(this);
        headIcon.setOnClickListener(this);
        iv_top_icon= (ImageView)v.findViewById(R.id.iv_top_icon);//模糊背景
         username = v.findViewById(R.id.tv_my_username);
         tv_my_word = v.findViewById(R.id.tv_my_word);
    }

    private void initData() {
        getUserInfo();
        ArrayList<Fragment> fragments = new ArrayList<>();
        MyVideoFragment myVideoFragment = new MyVideoFragment();
        MyLiveFragment myLiveFragment = new MyLiveFragment();
        MyGardenFragment myGardenFragment = new MyGardenFragment();
        MyWeddingCardFragment myWeddingCardFragment = new MyWeddingCardFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("userId",MyApplication.user.getUserId());
        bundle.putInt("type",2);
        myVideoFragment.setArguments(bundle);
        myLiveFragment.setArguments(bundle);
        myGardenFragment.setArguments(bundle);
        myWeddingCardFragment.setArguments(bundle);

        fragments.add(myVideoFragment);
        fragments.add(myLiveFragment);
        fragments.add(myGardenFragment);
        fragments.add(myWeddingCardFragment);
        mStMy.setViewPager(mViewpagerMy, items, (FragmentActivity) getActivity(), fragments);


    }
    private void getUserInfo() {
        HttpUtils2.getUserInfoById(MyApplication.user.getLoginToken(),MyApplication.user.getUserId(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.v("nihaoma",response.body());
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    if (jsonObject.getInt("code")==1){
                        String object = jsonObject.getString("user");
                        String followAndLike = jsonObject.getString("followAndLike");
                        JSONObject jsonObject1 = new JSONObject(followAndLike);
                        int followCount = jsonObject1.getInt("followCount");
                        int fansCount = jsonObject1.getInt("fansCount");
                        int likeCount = jsonObject1.getInt("likeCount");
                        huozanshu.setText(likeCount+" 获赞");
                        mMyfocuse.setText(followCount+" 关注");
                        mMyfans.setText(fansCount+" 粉丝");
                        Gson gson = new Gson();
                        PersonBean personBean = gson.fromJson(object, PersonBean.class);
                        personBean.setLikeCount(likeCount);
                        Log.v("nihaoma",personBean.toString());
                        MyApplication application = (MyApplication) getActivity().getApplication();
                        application.setUer(personBean);
                        SharedPreferencesUtil.putString(getActivity(), MyConstants.User,object);
                        //设置用户信息
                        username.setText(personBean.getUserName());
                        tv_my_word.setText(TextUtils.isEmpty(personBean.getSignature())?getResources().getString(R.string.textsign):personBean.getSignature());
                        if (MyApplication.user.getHeadPortrait()!=null){
                            Glide.with(getActivity()).load(personBean.getHeadPortrait()).into(headIcon);
                            //模糊效果
                            String headPortrait = personBean.getHeadPortrait();
                            //Bitmap bmp= BitmapFactory.decodeResource(getResources(), R.drawable.xilvfriends);
                            returnBitMap(headPortrait);

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                }
                dismissLoading();
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                showLoading();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dismissLoading();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                dismissLoading();
            }
        });
    }
    //模糊效果处理
    private  Bitmap  bitMBitmap;
    public void returnBitMap(final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL imageurl = null;

                try {
                    imageurl = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    HttpURLConnection conn = (HttpURLConnection)imageurl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitMBitmap = BitmapFactory.decodeStream(is);
                    is.close();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Bitmap bitmap = FastBlurUtility.blurBitmap(bitMBitmap);
                            iv_top_icon.setImageBitmap(bitmap);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }



    @Override
    public void onClick(View view) {
        UMWeb web = new UMWeb("https://www.baidu.com");
        web.setTitle("快来加入喜绿吧");//标题
        switch (view.getId()) {
            case R.id.my_more://点击。。。
                showPopwindow();
                break;
            case R.id.system_setting:
                Intent intentSystemSetting = new Intent(getActivity(), SystemSettingsActivity.class);
                startActivity(intentSystemSetting);
                break;
            case R.id.msg_driect://点击铃铛
                Intent intentMyMessageAndDirectActivity = new Intent(getActivity(), MyMessageAndDirectActivity.class);
                intentMyMessageAndDirectActivity.putExtra("HomeMyFragment",0);
                startActivity(intentMyMessageAndDirectActivity);
                break;
            case R.id.pop_privateletter://私信
                Intent intentMyMessageAndDirectActivity2 = new Intent(getActivity(), MyMessageAndDirectActivity.class);
                intentMyMessageAndDirectActivity2.putExtra("HomeMyFragment",1);
                startActivity(intentMyMessageAndDirectActivity2);
                popupWindow.dismiss();
                break;
            case R.id.pop_audit://视频审核
                Intent intentAuditAndLiveActivity = new Intent(getActivity(), AuditAndLiveActivity.class);
                startActivity(intentAuditAndLiveActivity);
                popupWindow.dismiss();
                break;
            case R.id.my_home_focuse://我的关注
                Intent intentMyFocuseActivity = new Intent(getActivity(), MyFocuseActivity.class);
                intentMyFocuseActivity.putExtra("type",1);
                intentMyFocuseActivity.putExtra("userid",MyApplication.user.getUserId());
                startActivity(intentMyFocuseActivity);
                break;
            case R.id.my_home_fans://我的粉丝
                Intent intentMyFocuseActivity2 = new Intent(getActivity(), MyFocuseActivity.class);
                intentMyFocuseActivity2.putExtra("type",2);
                intentMyFocuseActivity2.putExtra("userid",MyApplication.user.getUserId());
                startActivity(intentMyFocuseActivity2);
                break;
            case R.id.tv_huozanshu://点赞数
                showDianZanShuPop();
                break;
            case R.id.pop_mygoods: //我发布的商品
                Intent intentMyProductsReleaseActivity = new Intent(getActivity(), MyProductsReleaseActivity.class);
                startActivity(intentMyProductsReleaseActivity);
                popupWindow.dismiss();
                break;
            case R.id.iv_my_head://头像
                Intent intentPersonalDataActivity = new Intent(getActivity(), PersonalDataActivity.class);
                startActivityForResult(intentPersonalDataActivity,100);
                break;
            case R.id.pop_mywallet://钱包
                Intent intentMyWalletActivity = new Intent(getActivity(), MyWalletActivity.class);
                startActivity(intentMyWalletActivity);
                popupWindow.dismiss();
                break;
            case R.id.iv_huozan_close://获赞关闭
                huoZanPop.dismiss();
                break;
            case R.id.tv_huozan_share://获赞分享
                showRommended();
                huoZanPop.dismiss();
                break;
            case R.id.ll_share_weixin_circleo:
                new ShareAction(getActivity())
                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//朋友圈
                        .withMedia(web)
                        .setCallback(umShareListener)//回调监听器
                        .share();
                popShare.dismiss();
                break;
            case R.id.ll_share_weixin:

                new ShareAction(getActivity())
                        .setPlatform(SHARE_MEDIA.WEIXIN)//微信
                        .withMedia(web)
                        .setCallback(umShareListener)//回调监听器
                        .share();

                popShare.dismiss();
                break;
            case R.id.ll_share_weibo:
                new ShareAction(getActivity())
                        .setPlatform(SHARE_MEDIA.SINA)//微博
                        .withMedia(web)
                        .setCallback(umShareListener)//回调监听器
                        .share();
                popShare.dismiss();
            case R.id.ll_shareQQ:
                new ShareAction(getActivity())
                        .setPlatform(SHARE_MEDIA.QQ)//微博
                        .withText("hello")//分享内容
                        .setCallback(umShareListener)//回调监听器
                        .share();
                popShare.dismiss();
                break;
            case R.id.pop_coupon://优惠券
                Intent intentDiscountCouponActivity = new Intent(getActivity(), DiscountCouponActivity.class);
                startActivity(intentDiscountCouponActivity);
                popupWindow.dismiss();
                break;
            case R.id.pop_shoppingcar://购物车
                Intent intentWebView = new Intent(getActivity(), WebViewActivity.class);
                intentWebView.putExtra("webview",0);
                startActivity(intentWebView);
                popupWindow.dismiss();
                break;
            case R.id.pop_order://订单
               /* Intent intentWebView2 = new Intent(getActivity(), WebViewActivity.class);
                intentWebView2.putExtra("webview",1);
                startActivity(intentWebView2);
                popupWindow.dismiss();*/
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

    private PopupWindow huoZanPop;


    private void showDianZanShuPop() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_huo_zan, null);
        contentView.findViewById(R.id.iv_huozan_close).setOnClickListener(this);
        contentView.findViewById(R.id.tv_huozan_share).setOnClickListener(this);
        TextView dianzan_username= contentView.findViewById(R.id.tv_dianzan_username);
        TextView dianzanshu = contentView.findViewById(R.id.tv_dianzanshu);
        dianzan_username.setText(MyApplication.user.getUserName());
        int likeCount = MyApplication.user.getLikeCount();
        String str="共获得<font color='#FF0000'>"+likeCount+"</font>个赞" ;
        dianzanshu.setText(Html.fromHtml(str));
        huoZanPop = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        huoZanPop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        huoZanPop.setOutsideTouchable(true);
        huoZanPop.setTouchable(true);
        //设置半透明
        WindowManager.LayoutParams lp = getActivity().getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
        //恢复正常
        huoZanPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getActivity().getWindow().setAttributes(lp);
            }
        });
        huoZanPop.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);

    }
    private PopupWindow popShare;
    //推荐给好友
    private void showRommended() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_share, null);
        contentView.findViewById(R.id.ll_share_weixin_circleo).setOnClickListener(this);
        contentView.findViewById(R.id.ll_share_weixin).setOnClickListener(this);
        contentView.findViewById(R.id.ll_shareQQ).setOnClickListener(this);
        contentView.findViewById(R.id.ll_share_weibo).setOnClickListener(this);
        popShare = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popShare.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popShare.setOutsideTouchable(true);
        popShare.setTouchable(true);
        //设置半透明
        WindowManager.LayoutParams lp =getActivity().getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
        //恢复正常
        popShare.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getActivity().getWindow().setAttributes(lp);
            }
        });
        //popupWindow.showAsDropDown(mMyToPromote, -70, 5);
        popShare.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }
    private void showPopwindow() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_menu, null);
        //钱包
        LinearLayout pop_mywallet = contentView.findViewById(R.id.pop_mywallet);
        pop_mywallet.setOnClickListener(this);
        //私信
        LinearLayout pop_privateletter = contentView.findViewById(R.id.pop_privateletter);
        pop_privateletter.setOnClickListener(this);
        //审核
        LinearLayout pop_audit = contentView.findViewById(R.id.pop_audit);
        pop_audit.setOnClickListener(this);
        //我发布的商品
        LinearLayout pop_goods = contentView.findViewById(R.id.pop_mygoods);
        pop_goods.setOnClickListener(this);

        //我的钱包
        contentView.findViewById(R.id.pop_mywallet).setOnClickListener(this);
        //购物车
        contentView.findViewById(R.id.pop_shoppingcar).setOnClickListener(this);
        //订单
        contentView.findViewById(R.id.pop_order).setOnClickListener(this);
        //优惠券
        contentView.findViewById(R.id.pop_coupon).setOnClickListener(this);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        //设置半透明
        WindowManager.LayoutParams lp = getActivity().getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
        //恢复正常
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getActivity().getWindow().setAttributes(lp);
            }
        });
        popupWindow.showAsDropDown(mMyMore, -80, 5);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==3){
            if (data != null&&requestCode==100){
                PersonBean personBean = (PersonBean) data.getSerializableExtra("data");
                //设置用户信息
                username.setText(personBean.getUserName());
                tv_my_word.setText(personBean.getSignature());
                if (MyApplication.user.getHeadPortrait()!=null){
                    Glide.with(getActivity()).load(personBean.getHeadPortrait()).into(headIcon);
                    //模糊效果
                    String headPortrait = personBean.getHeadPortrait();
                    //Bitmap bmp= BitmapFactory.decodeResource(getResources(), R.drawable.xilvfriends);
                    returnBitMap(headPortrait);

                }
            }
        }
    }
}

