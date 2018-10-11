package com.xbdl.xinushop.utils;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.WeatherBean;

import java.util.List;

public class LocationUtil {
     String city;
    //位置
    private String setLocation(final BaseActivity activity) {

        //位置
        LocationClient mLocationClient = new LocationClient(activity);
        //声明LocationClient类
        mLocationClient.registerLocationListener(new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
                city = location.getCity();
                if (!Judge.getBoolean_isNull(city)) {

                    SharedPreferencesUtil.putString(activity,MyConstants.city,city);

                }
            }


        });
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
        return city;
    }


    private void getWeather(final BaseActivity activity,String city) {
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

                        SharedPreferencesUtil.putString(activity, MyConstants.wearther,response.body());

                    }
                }else {
                    ToastUtil.shortToast(activity,"天气获取失败");
                }

                activity.dismissLoading();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                activity. dismissLoading();
                Log.v("nihaoma","333333");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                activity.dismissLoading();
                Log.v("nihaoma","222222");
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                activity.showLoading();
                Log.v("nihaoma","111111111");
            }
        });
    }
}
