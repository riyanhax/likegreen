package com.pywl.likegreen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.pywl.likegreen.R;
import com.pywl.likegreen.activity.mine.ShareLiftActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/*
* 种植日记
* */
public class PlantDiaryActivity extends AppCompatActivity implements View.OnClickListener {
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private TextView location,plantData;
    private ImageView imgPicker;
    private ArrayList<ImageItem> images = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_diary);
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();

        option.setIsNeedAddress(true);
//可选，是否需要地址信息，默认为不需要，即参数为false
//如果开发者需要获得当前点的地址信息，此处必须为true

        mLocationClient.setLocOption(option);
//mLocationClient为第二步初始化过的LocationClient对象
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
//更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
        //注册监听函数

        initView();
        initData();
    }



    private void initView() {
        location= (TextView)findViewById(R.id.tv_plant_location);
        plantData= (TextView)findViewById(R.id.tv_plant_data);
        imgPicker= (ImageView)findViewById(R.id.iv_plant_imgpicker);
        findViewById(R.id.tv_plant_wancheng).setOnClickListener(this);
        location.setOnClickListener(this);
        imgPicker.setOnClickListener(this);
    }
    private void initData() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 ");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        plantData.setText("默认日期"+simpleDateFormat.format(date));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_plant_location:
                mLocationClient.start();
                if (myListener.province!=null){
                    location.setText(myListener.province+"."+myListener.city+"."+myListener.district);
                }

                Log.v("nihaoma",myListener.addr+"00   @"+myListener.city+"@"+myListener.district+"@"+myListener.street);
                break;
            case  R.id.tv_plant_wancheng:
                Intent intentShareLiftActivity = new Intent(PlantDiaryActivity.this, ShareLiftActivity.class);
                startActivity(intentShareLiftActivity);
                break;
            case R.id.iv_plant_imgpicker:

                Intent intent = new Intent(this, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                //ImagePicker.getInstance().setSelectedImages(images);
                startActivityForResult(intent, 100);
                break;
        }
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        String addr;
        String province;
        String city;
        String district;
        String street;
        int locType;
        @Override
        public void onReceiveLocation(BDLocation location){
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            addr = location.getAddrStr();    //获取详细地址信息
            String country = location.getCountry();    //获取国家
            province= location.getProvince();    //获取省份
            city= location.getCity();    //获取城市
             district = location.getDistrict();    //获取区县
            street = location.getStreet();    //获取街道信息
            locType = location.getLocType();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                Log.v("nihaoma",images.toString());
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
