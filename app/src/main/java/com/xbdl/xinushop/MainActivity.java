package com.xbdl.xinushop;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.socialize.UMShareAPI;
import com.xbdl.xinushop.activity.EquipmentActivity;
import com.xbdl.xinushop.activity.PlantDiaryActivity;
import com.xbdl.xinushop.activity.ShortCameraActivity;
import com.xbdl.xinushop.activity.WriteLongPostActivity;
import com.xbdl.xinushop.activity.mian.ApplyLiveActivity;
import com.xbdl.xinushop.activity.mine.AddShareLifeActivity;
import com.xbdl.xinushop.activity.mine.AddSubjectActivity;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.evnetBus.CallTab;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.WeatherBean;
import com.xbdl.xinushop.fragment.home.HomeFindFragment;
import com.xbdl.xinushop.fragment.home.HomeMyFragment;
import com.xbdl.xinushop.fragment.home.HomeNoteFragment;
import com.xbdl.xinushop.fragment.home.HomePageFragment;
import com.xbdl.xinushop.listener.BackHandledInterface;
import com.xbdl.xinushop.utils.WeekTimeUtil;
import com.xbdl.xinushop.utils.FastBlurUtility;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.Judge;
import com.xbdl.xinushop.utils.NavigationBarHeight;
import com.xbdl.xinushop.utils.SharedPreferencesUtil;
import com.xbdl.xinushop.utils.ToastUtil;
import com.xbdl.xinushop.view.BackHandledFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;


public class MainActivity extends BaseActivity implements View.OnClickListener,BackHandledInterface {
    final RxPermissions rxPermissions = new RxPermissions(this);
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";//测试url
    public static Activity instance = null;
    Fragment main_home, main_note, main_find, main_mine, main_add;
    private RadioGroup main_radio;
    private RadioButton main_rbt_home, main_rbt_add, main_rbt_mine, main_rbt_find, main_rbt_note;
    private FragmentTransaction transaction;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.xbdl.xinushop.R.layout.activity_main);
        instance = this;
        EventBus.getDefault().register(this);
        initView();
        initFragment();
        initPermissions();
        //initData();
        JMessageClient.login(getString(com.xbdl.xinushop.R.string.appidjiguang), "123456", new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                Log.i("asdf", "" + i);
            }

        });


    }

    private void initData() {
        String weather = SharedPreferencesUtil.getString(getActivity(), MyConstants.wearther, "key");
        String spCity = SharedPreferencesUtil.getString(getActivity(), MyConstants.city, "key");
        if (weather!=null&&!weather.equals("key")&&spCity!=null&&!spCity.equals("key")){
            Gson gson = new Gson();
            WeatherBean weatherBean = gson.fromJson(weather, WeatherBean.class);
            if (weatherBean.getCode().equals("10000")){
                WeatherBean.ResultBean result = weatherBean.getResult();
                List<WeatherBean.ResultBean.HeWeather5Bean> heWeather5 = result.getHeWeather5();
                for(WeatherBean.ResultBean.HeWeather5Bean bean:heWeather5){
                    WeatherBean.ResultBean.HeWeather5Bean.NowBean now = bean.getNow();
                    String tmp = now.getTmp();
                    WeatherBean.ResultBean.HeWeather5Bean.NowBean.CondBean cond = now.getCond();
                    String txt = cond.getTxt();
                    tvLoaction.setText(spCity+": "+txt+" "+tmp+"℃");
                }


            }
        }
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected void onResume() {

       /* HashMap<String, Boolean> stringBooleanHashMap = netWorkStateReceiver.wifiState();
        for (Map.Entry<String,Boolean> entry:stringBooleanHashMap.entrySet()){
            Log.v("nihaoma",entry.getKey()+entry.getValue());
        }*/
        super.onResume();

    }


    private void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (main_home == null) {
            main_home = new HomePageFragment();
            transaction.add(com.xbdl.xinushop.R.id.fragment_container, main_home);
            main_rbt_home.setTextColor(getResources().getColor(com.xbdl.xinushop.R.color.green));
        }
        transaction.commitAllowingStateLoss();
    }

    protected void initView() {
        main_radio = (RadioGroup) findViewById(com.xbdl.xinushop.R.id.main_radio);
        main_rbt_home = (RadioButton) findViewById(com.xbdl.xinushop.R.id.main_rbt_home);
        main_rbt_add = (RadioButton) findViewById(com.xbdl.xinushop.R.id.main_rbt_add);
        main_rbt_find = (RadioButton) findViewById(com.xbdl.xinushop.R.id.main_rbt_find);
        main_rbt_mine = (RadioButton) findViewById(com.xbdl.xinushop.R.id.main_rbt_mine);
        main_rbt_note = (RadioButton) findViewById(com.xbdl.xinushop.R.id.main_rbt_note);
        main_rbt_add.setOnClickListener(this);
        main_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                setTab(checkedId);
            }
        });
        findViewById(com.xbdl.xinushop.R.id.iv_mian_add).setOnClickListener(this);
    }

    private void setTab(@IdRes int checkedId) {
        transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (checkedId) {
            case com.xbdl.xinushop.R.id.main_rbt_home:
                if (main_home == null) {
                    main_home = new HomePageFragment();
                    transaction.add(com.xbdl.xinushop.R.id.fragment_container, main_home);
                } else {
                    transaction.show(main_home);
                }
                main_radio.setBackgroundColor(Color.TRANSPARENT);
                main_rbt_home.setTextColor(getResources().getColor(com.xbdl.xinushop.R.color.green));
                main_rbt_home.setChecked(true);
                main_rbt_add.setTextColor(getResources().getColor(com.xbdl.xinushop.R.color.maintextcolor));
                main_rbt_find.setTextColor(getResources().getColor(com.xbdl.xinushop.R.color.maintextcolor));
                main_rbt_mine.setTextColor(getResources().getColor(com.xbdl.xinushop.R.color.maintextcolor));
                main_rbt_note.setTextColor(getResources().getColor(com.xbdl.xinushop.R.color.maintextcolor));
                EventBus.getDefault().post(CallTab.MAIN);
                break;
            case com.xbdl.xinushop.R.id.main_rbt_note:
                if (main_note == null) {
                    main_note = new HomeNoteFragment();
                    transaction.add(com.xbdl.xinushop.R.id.fragment_container, main_note);
                } else {
                    transaction.show(main_note);
                }
                main_radio.setBackgroundColor(Color.WHITE);
                main_rbt_home.setTextColor(getResources().getColor(com.xbdl.xinushop.R.color.maintextcolor));
                main_rbt_add.setTextColor(getResources().getColor(com.xbdl.xinushop.R.color.maintextcolor));
                main_rbt_find.setTextColor(getResources().getColor(com.xbdl.xinushop.R.color.maintextcolor));
                main_rbt_mine.setTextColor(getResources().getColor(com.xbdl.xinushop.R.color.maintextcolor));
                main_rbt_note.setTextColor(getResources().getColor(com.xbdl.xinushop.R.color.green));
                main_rbt_note.setChecked(true);
                EventBus.getDefault().post(CallTab.NOTE);
                break;
            // case R.id.main_rbt_add:
                /*Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivityForResult(intent,1);*/
                /*if (main_add == null) {

                    transaction.add(R.id.fragment_container, main_add);
                } else {
                    transaction.show(main_add);
                }*/
            // showPopupWindow();
                /*main_rbt_home.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_add.setTextColor(getResources().getColor(R.color.green));
                main_rbt_add.setChecked(true);
                main_rbt_find.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_mine.setTextColor(getResources().getColor(R.color.maintextcolor));
                main_rbt_note.setTextColor(getResources().getColor(R.color.maintextcolor));*/
            // break;
            case com.xbdl.xinushop.R.id.main_rbt_find:
                if (main_find == null) {
                    main_find = new HomeFindFragment();
                    transaction.add(com.xbdl.xinushop.R.id.fragment_container, main_find);
                } else {
                    transaction.show(main_find);
                }
                main_radio.setBackgroundColor(Color.WHITE);
                main_rbt_home.setTextColor(getResources().getColor(com.xbdl.xinushop.R.color.maintextcolor));
                main_rbt_add.setTextColor(getResources().getColor(com.xbdl.xinushop.R.color.maintextcolor));
                main_rbt_find.setTextColor(getResources().getColor(com.xbdl.xinushop.R.color.green));
                main_rbt_find.setChecked(true);
                main_rbt_mine.setTextColor(getResources().getColor(com.xbdl.xinushop.R.color.maintextcolor));
                main_rbt_note.setTextColor(getResources().getColor(com.xbdl.xinushop.R.color.maintextcolor));
                EventBus.getDefault().post(CallTab.FIND);
                break;
            case com.xbdl.xinushop.R.id.main_rbt_mine:
                if (main_mine == null) {
                    main_mine = new HomeMyFragment();
                    transaction.add(com.xbdl.xinushop.R.id.fragment_container, main_mine);
                } else {
                    transaction.show(main_mine);
                }
                main_radio.setBackgroundColor(Color.WHITE);
                main_rbt_home.setTextColor(getResources().getColor(com.xbdl.xinushop.R.color.maintextcolor));
                main_rbt_add.setTextColor(getResources().getColor(com.xbdl.xinushop.R.color.maintextcolor));
                main_rbt_find.setTextColor(getResources().getColor(com.xbdl.xinushop.R.color.maintextcolor));
                main_rbt_mine.setTextColor(getResources().getColor(com.xbdl.xinushop.R.color.green));
                main_rbt_mine.setChecked(true);
                main_rbt_note.setTextColor(getResources().getColor(com.xbdl.xinushop.R.color.maintextcolor));
                EventBus.getDefault().post(CallTab.MINE);
                break;
        }
        transaction.commitAllowingStateLoss();
    }


    public void hideAllFragment(FragmentTransaction transaction) {
        if (main_home != null) {
            transaction.hide(main_home);
        }
        if (main_find != null) {
            transaction.hide(main_find);
        }
        if (main_mine != null) {
            transaction.hide(main_mine);
        }
        if (main_add != null) {
            // transaction.hide(main_add);
        }
        if (main_note != null) {
            transaction.hide(main_note);
        }
        main_rbt_mine.setChecked(false);
        main_rbt_add.setChecked(false);
        main_rbt_find.setChecked(false);
        main_rbt_home.setChecked(false);
        main_rbt_note.setChecked(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //QQ分享
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        //add页面结束后返回首页
        if (resultCode == 2) {
            if (requestCode == 1) {
                main_rbt_home.setChecked(true);
            }

        }
    }

    //接收eventbus事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setTab(CallTab callTab) {
    /*    switch (callTab){
            case MAIN:
                main_rbt_home.setChecked(true);
                break;
            case NOTE:
                main_rbt_note.setChecked(true);
                break;
            case MINE:
                main_rbt_mine.setChecked(true);
                break;
            case FIND:
                main_rbt_find.setChecked(true);
                break;
        }*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initPermissions() {
        rxPermissions.request(
                Manifest.permission.INTERNET,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_PHONE_STATE)
                .subscribe();
    }
    /*---百度地图------*/
    public LocationClient mLocationClient = null;
    public class MyLocationListener extends BDAbstractLocationListener {


        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            final String city = location.getCity();

            if (!Judge.getBoolean_isNull(city)) {

                //获取天气
                HttpUtils2.getweather(city, MyConstants.WEARTHERKEY, new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.v("nihaoma","000000");
                        Gson gson = new Gson();
                        WeatherBean weatherBean = gson.fromJson(response.body(), WeatherBean.class);
                        if (weatherBean.getCode().equals("10000")){
                            WeatherBean.ResultBean result = weatherBean.getResult();
                            List<WeatherBean.ResultBean.HeWeather5Bean> heWeather5 = result.getHeWeather5();
                            for(WeatherBean.ResultBean.HeWeather5Bean bean:heWeather5){
                                WeatherBean.ResultBean.HeWeather5Bean.NowBean now = bean.getNow();
                                String tmp = now.getTmp();
                                WeatherBean.ResultBean.HeWeather5Bean.NowBean.CondBean cond = now.getCond();
                                String txt = cond.getTxt();
                                tvLoaction.setText(city+": "+txt+" "+tmp+"℃");
                                SharedPreferencesUtil.putString(getActivity(),MyConstants.wearther,response.body());
                                SharedPreferencesUtil.putString(getActivity(),MyConstants.city,city);
                            }


                            }else {
                                ToastUtil.shortToast(getActivity(),"天气获取失败");
                            }

                        dismissLoading();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                       dismissLoading();
                        Log.v("nihaoma","333333");
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        dismissLoading();
                        Log.v("nihaoma","222222");
                    }

                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        showLoading();
                        Log.v("nihaoma","111111111");
                    }
                });
            }

        }
    }


    private MyLocationListener myListener = new MyLocationListener();

    /*---百度地图------*/
    private TextView tvLoaction;
    private void showPopupWindow() {
        View contentView = LayoutInflater.from(this).inflate(com.xbdl.xinushop.R.layout.pop_add, null);
        //设置日期
        setDate(contentView);
        tvLoaction= (TextView) contentView.findViewById(R.id.tv_add_location);
        //设置天气
        String weather = SharedPreferencesUtil.getString(getActivity(), MyConstants.wearther, "key");
        String spCity = SharedPreferencesUtil.getString(getActivity(), MyConstants.city, "key");
        if (weather!=null&&!weather.equals("key")&&spCity!=null&&!spCity.equals("key")){
            Gson gson = new Gson();
            WeatherBean weatherBean = gson.fromJson(weather, WeatherBean.class);
            if (weatherBean.getCode().equals("10000")){
                WeatherBean.ResultBean result = weatherBean.getResult();
                List<WeatherBean.ResultBean.HeWeather5Bean> heWeather5 = result.getHeWeather5();
                for(WeatherBean.ResultBean.HeWeather5Bean bean:heWeather5){
                    WeatherBean.ResultBean.HeWeather5Bean.NowBean now = bean.getNow();
                    String tmp = now.getTmp();
                    WeatherBean.ResultBean.HeWeather5Bean.NowBean.CondBean cond = now.getCond();
                    String txt = cond.getTxt();
                    tvLoaction.setText(spCity+": "+txt+" "+tmp+"℃");
                }
            }
        }else {
            setLocation();
        }

        contentView.findViewById(com.xbdl.xinushop.R.id.rl_add_close).setOnClickListener(this);//关闭
        contentView.findViewById(com.xbdl.xinushop.R.id.ll_add_shortvideo).setOnClickListener(this);//短视频
        contentView.findViewById(com.xbdl.xinushop.R.id.ll_add_plantdiary).setOnClickListener(this);//种植
        contentView.findViewById(com.xbdl.xinushop.R.id.ll_add_equipment).setOnClickListener(this);//应用设备
        contentView.findViewById(com.xbdl.xinushop.R.id.ll_add_longpost).setOnClickListener(this);//长贴子
        contentView.findViewById(com.xbdl.xinushop.R.id.ll_add_sharelife).setOnClickListener(this);//分享生活
        contentView.findViewById(com.xbdl.xinushop.R.id.ll_add_live).setOnClickListener(this);//直播
        contentView.findViewById(com.xbdl.xinushop.R.id.ll_add_jiontheme).setOnClickListener(this);//参与话题

        ImageView img = (ImageView) contentView.findViewById(R.id.iv_pop_bg);//背景
        //虚化背景
        Bitmap blurBackgroundDrawer = FastBlurUtility.getBlurBackgroundDrawer(this);
        img.setImageBitmap(blurBackgroundDrawer);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //设置为失去焦点 方便监听返回键的监听
        popupWindow.setFocusable(false);
        // 如果想要popupWindow 遮挡住状态栏可以加上这句代码
        popupWindow.setClippingEnabled(false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(false);

        //popupWindow.showAtLocation(main_rbt_add, Gravity.NO_GRAVITY, 0, 0);
        int navigationBarHeight = NavigationBarHeight.getNavigationBarHeight(this);
        popupWindow.showAtLocation(main_rbt_add, Gravity.NO_GRAVITY, 0, -navigationBarHeight);
        EventBus.getDefault().post(CallTab.ADD);
    }
    //位置
    private void setLocation() {
        //位置
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        LocationClientOption option = new LocationClientOption();

        option.setIsNeedAddress(true);
//可选，是否需要地址信息，默认为不需要，即参数为false
//如果开发者需要获得当前点的地址信息，此处必须为true

        mLocationClient.setLocOption(option);
//mLocationClient为第二步初始化过的LocationClient对象
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
//更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说
        mLocationClient.start();
    }

    //设置时间
    private void setDate(View contentView) {
        String  year= WeekTimeUtil.StringData(1);
        String  month= WeekTimeUtil.StringData(2);
        String  day= WeekTimeUtil.StringData(3);
        String  week= WeekTimeUtil.StringData(4);
        TextView tvday = (TextView) contentView.findViewById(R.id.tv_add_day);
        TextView tvweek = (TextView) contentView.findViewById(R.id.tv_add_week);
        TextView tvyear = (TextView) contentView.findViewById(R.id.tv_add_year);
        tvday.setText(day);
        tvweek.setText("星期"+week);
        tvyear.setText(month+"/"+year);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case com.xbdl.xinushop.R.id.iv_mian_add:
                showPopupWindow();
                break;
            case com.xbdl.xinushop.R.id.rl_add_close://关闭
                popupWindow.dismiss();

                break;
            case com.xbdl.xinushop.R.id.ll_add_shortvideo://短视频
                Intent intentShortCameraActivity = new Intent(MainActivity.this, ShortCameraActivity.class);

                startActivity(intentShortCameraActivity);
                popupWindow.dismiss();
                break;
            case com.xbdl.xinushop.R.id.ll_add_plantdiary://种植日记
                Intent intentPlantDiaryActivity = new Intent(MainActivity.this, PlantDiaryActivity.class);
                startActivity(intentPlantDiaryActivity);
                popupWindow.dismiss();
                break;
            case com.xbdl.xinushop.R.id.ll_add_equipment://应用设备
                Intent intentEquipmentActivity = new Intent(MainActivity.this, EquipmentActivity.class);
                startActivity(intentEquipmentActivity);
                popupWindow.dismiss();
                break;
            case com.xbdl.xinushop.R.id.ll_add_longpost://长贴子
                Intent intentLongPostActivity = new Intent(MainActivity.this, WriteLongPostActivity.class);
                startActivity(intentLongPostActivity);
                popupWindow.dismiss();
                break;
            case com.xbdl.xinushop.R.id.ll_add_sharelife://分享生活
                Intent intentShareLiftActivity = new Intent(MainActivity.this, AddShareLifeActivity.class);
                startActivity(intentShareLiftActivity);
                popupWindow.dismiss();
                break;
            case com.xbdl.xinushop.R.id.ll_add_live://直播
                Intent intentApplyLiveActivity = new Intent(MainActivity.this, ApplyLiveActivity.class);
                startActivity(intentApplyLiveActivity);
                popupWindow.dismiss();
                break;
            //参与话题
            case R.id.ll_add_jiontheme:
                Intent intentAddSubjectActivity = new Intent(MainActivity.this, AddSubjectActivity.class);
                startActivity(intentAddSubjectActivity);
                popupWindow.dismiss();
                break;
        }
    }
    //webview fragment
    private BackHandledFragment mBackHandedFragment;
    private boolean hadIntercept;
    @Override
    public void setSelectedFragment(BackHandledFragment selectedFragment) {
        this.mBackHandedFragment = selectedFragment;
    }
    @Override
    public void onBackPressed() {
     /*   if (popupWindow != null) {
            popupWindow.dismiss();
        }*/
        if (mBackHandedFragment == null || !mBackHandedFragment.onBackPressed()) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                Log.i("asdf", "1");

                super.onBackPressed();
            } else {
                Log.i("asdf", "2");
                getSupportFragmentManager().popBackStack(); //fragment 出栈
            }
        }
        SharedPreferencesUtil.remove(getActivity(),MyConstants.city);
        SharedPreferencesUtil.remove(getActivity(),MyConstants.wearther);
//        long secondTime = System.currentTimeMillis();
//        if (secondTime - firstTime > 2000) {
//            ToastUtil.showShortToast("再按一次退出程序");
//            firstTime = secondTime;
//        } else {
//            if (NiceVideoPlayerManager.instance().onBackPressd()) return;
//            finish();
//        }
        // super.onBackPressed();
    }

}
