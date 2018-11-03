package com.xbdl.xinushop.fragment.mine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.PlantDiaryActivity;
import com.xbdl.xinushop.adapter.PlantingDiaryMyGrardenAdapter;
import com.xbdl.xinushop.adapter.baseadapter.BaseAdapter;
import com.xbdl.xinushop.base.BaseFragment;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.bean.PlantingDiaryBean;
import com.xbdl.xinushop.bean.WeatherBean;
import com.xbdl.xinushop.utils.HttpUtils;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.Judge;
import com.xbdl.xinushop.utils.SharedPreferencesUtil;
import com.xbdl.xinushop.utils.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theWind on 2018/8/1.
 * 花园
 */

public class
MyGardenFragment extends BaseFragment {
    String token = "";

    public static final int lOAD_DADA = 1;
    public static final int UP_DADA = lOAD_DADA + 1;
    View rlEmpty;
    MaterialRefreshLayout refreshLayout;
    PlantingDiaryMyGrardenAdapter plantingDiaryMyGrardenAdapter;
    private TextView tvTmp,tv_location,tv_feel;
    private ImageView weatherIcon;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_garden, container, false);
        initView(view);
        rlEmpty = view.findViewById(R.id.rl_emptylayout);
        refreshLayout = view.findViewById(R.id.refresh);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        plantingDiaryMyGrardenAdapter = new PlantingDiaryMyGrardenAdapter(getActivity());
        recyclerView.setAdapter(plantingDiaryMyGrardenAdapter);

        String userjson = SharedPreferencesUtil.getString(getActivity(), MyConstants.User, "");

        PersonBean personBean = new Gson().fromJson(userjson, PersonBean.class);
        token = personBean.getLoginToken();
        getDataNormal(lOAD_DADA);
        setListener();
        return view;
    }

    private void initView(View v) {
        weatherIcon = v.findViewById(R.id.iv_weather_icon);
        tvTmp = v.findViewById(R.id.tv_tmp);
        tv_location = v.findViewById(R.id.tv_location);
        tv_feel = v.findViewById(R.id.tv_feel);
        //创建植物
        v.findViewById(R.id.rl_my_garden_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlantDiaryActivity.class);
                startActivity(intent);
            }
        });
        //设置天气
        String weather = SharedPreferencesUtil.getString(getActivity(), MyConstants.wearther, "key");
        String spCity = SharedPreferencesUtil.getString(getActivity(), MyConstants.city, "key");
        if (weather != null && !weather.equals("key") && spCity != null && !spCity.equals("key")) {
            Gson gson = new Gson();
            WeatherBean weatherBean = gson.fromJson(weather, WeatherBean.class);
            if (weatherBean.getCode().equals("10000")) {
                WeatherBean.ResultBean result = weatherBean.getResult();
                List<WeatherBean.ResultBean.HeWeather5Bean> heWeather5 = result.getHeWeather5();
                for (WeatherBean.ResultBean.HeWeather5Bean bean : heWeather5) {
                    WeatherBean.ResultBean.HeWeather5Bean.NowBean now = bean.getNow();
                    String tmp = now.getTmp();
                    WeatherBean.ResultBean.HeWeather5Bean.NowBean.CondBean cond = now.getCond();
                    String txt = cond.getTxt();
                    WeatherBean.ResultBean.HeWeather5Bean.SuggestionBean suggestion = bean.getSuggestion();
                    //舒适度
                    WeatherBean.ResultBean.HeWeather5Bean.SuggestionBean.ComfBean comf = suggestion.getComf();
                    tvTmp.setText(tmp + "℃");
                    tv_location.setText(spCity + "   " + txt);
                    tv_feel.setText(comf.getBrf());
                    setWeatherIcon(cond);
                }

            }
        } else {
            setLocation();
        }

    }

    private void setWeatherIcon(WeatherBean.ResultBean.HeWeather5Bean.NowBean.CondBean cond) {
        String code = cond.getCode();

        switch (code){
            case "100":
                weatherIcon.setImageResource(R.drawable.qing);
                break;

            case "213":
            case "300":
            case "301":
            case "302":
            case "303":
            case "304":
            case "305":
            case "306":
            case "307":
            case "308":
            case "309":
            case "310":
            case "311":
            case "312":
            case "313":
            case "314":
            case "315":
            case "316":
            case "317":
            case "318":
                weatherIcon.setImageResource(R.drawable.drip);
                break;
            case "400":
            case "401":
            case "402":
            case "403":
            case "404":
            case "405":
            case "407":
            case "408":
            case "409":
            case "410":
            case "406":
            case "499":
                weatherIcon.setImageResource(R.drawable.xiaxue);
                break;
            default:
                weatherIcon.setImageResource(R.drawable.duoyun);
                break;
        }
    }

    //位置
    private void setLocation() {
        //位置
        LocationClient mLocationClient = new LocationClient(getActivity());
        //声明LocationClient类
        mLocationClient.registerLocationListener(new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {

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
                                    SharedPreferencesUtil.putString(getActivity(),MyConstants.wearther,response.body());
                                    SharedPreferencesUtil.putString(getActivity(),MyConstants.city,city);
                                    WeatherBean.ResultBean.HeWeather5Bean.SuggestionBean suggestion = bean.getSuggestion();
                                    //舒适度
                                    WeatherBean.ResultBean.HeWeather5Bean.SuggestionBean.ComfBean comf = suggestion.getComf();
                                    tvTmp.setText(tmp+"℃");
                                    tv_location.setText(city+"   "+txt);
                                    tv_feel.setText(comf.getBrf());
                                    setWeatherIcon(cond);
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
    }

    private void setListener() {
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                //下拉刷新...
                new Handler().postDelayed(new Runnable() {


                    @Override
                    public void run() {
                        page = 1;
                        getData(lOAD_DADA);
                        refreshLayout.setLoadMore(true);
                    }
                }, 500);

            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //上拉加载更多...
                        /**
                         * 完成加载数据后，调用此方法，要不然刷新的效果不会消失
                         */
                        page++;
                        getData(UP_DADA);

                    }
                }, 500);

            }
        });
        //     refreshLayout.autoRefresh();
        plantingDiaryMyGrardenAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    int page = 1;

    /**
     * 加载数据
     */
    private void getData(final int upOrLoad) {
        if (upOrLoad == lOAD_DADA) { // 下拉
            page = 1;
        } else if (upOrLoad == UP_DADA) { // 上拉
            page += 1;
        }
        HttpUtils.findPlantList(token, page + "", 3 + "", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    int code = jsonObject.getInt("code");
                    String object = jsonObject.getString("object");

                    if (code == 100) {
                        List<PlantingDiaryBean> plantingDiaryBeans = getPlantingDiaryBeans(object);
                        if (plantingDiaryBeans != null && plantingDiaryBeans.size() > 0) {
                            //   rlEmpty.setVisibility(View.GONE);
                            if (upOrLoad == lOAD_DADA) { // 下拉
                                plantingDiaryMyGrardenAdapter.refreshData(plantingDiaryBeans);
                            } else if (upOrLoad == UP_DADA) { // 上拉
                                plantingDiaryMyGrardenAdapter.loadMoreData(plantingDiaryBeans);
                            }
                        } else {
                            //  rlEmpty.setVisibility(View.VISIBLE);

                        }
                    }

                } catch (JSONException e) {

                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                refreshLayout.finishRefresh();
                refreshLayout.finishRefreshLoadMore();
            }
        });

    }

    /**
     * 加载数据
     */
    private void getDataNormal(final int upOrLoad) {
        if (upOrLoad == lOAD_DADA) { // 下拉
            page = 1;
        } else if (upOrLoad == UP_DADA) { // 上拉
            page += 1;
        }
        HttpUtils.findPlantList(token, page + "", 3 + "", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    int code = jsonObject.getInt("code");
                    String object = jsonObject.getString("object");

                    if (code == 100) {
                        List<PlantingDiaryBean> plantingDiaryBeans = getPlantingDiaryBeans(object);
                        if (plantingDiaryBeans != null && plantingDiaryBeans.size() > 0) {
                            rlEmpty.setVisibility(View.GONE);
                            if (upOrLoad == lOAD_DADA) { // 下拉
                                plantingDiaryMyGrardenAdapter.refreshData(plantingDiaryBeans);
                            } else if (upOrLoad == UP_DADA) { // 上拉
                                plantingDiaryMyGrardenAdapter.loadMoreData(plantingDiaryBeans);
                            }
                        } else {
                            rlEmpty.setVisibility(View.VISIBLE);

                        }
                    }

                } catch (JSONException e) {

                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                refreshLayout.finishRefresh();
                refreshLayout.finishRefreshLoadMore();
            }
        });

    }

    private List<PlantingDiaryBean> getPlantingDiaryBeans(String object) {
        if (Judge.getBoolean_isNull(object)) {
            return null;
        }
        try {
            JSONArray array = new JSONArray(object);
            List<PlantingDiaryBean> datas = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                PlantingDiaryBean plantingDiaryBean = new Gson().fromJson(array.getString(i), PlantingDiaryBean.class);
                datas.add(plantingDiaryBean);
            }
            return datas;
        } catch (JSONException e) {
            return null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        OkGo.getInstance().cancelTag("findPlantList");
    }
}
