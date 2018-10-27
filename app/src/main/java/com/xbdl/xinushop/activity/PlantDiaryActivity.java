package com.xbdl.xinushop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.google.gson.Gson;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.ImagePickerAdapter;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.bean.WeatherBean;
import com.xbdl.xinushop.constant.ImagePickerConstant;
import com.xbdl.xinushop.utils.HttpUtils;
import com.xbdl.xinushop.utils.ImageUtils;
import com.xbdl.xinushop.utils.Judge;
import com.xbdl.xinushop.utils.SharedPreferencesUtil;
import com.xbdl.xinushop.view.SelectDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.xbdl.xinushop.MyApplication.maxImgCount;


/*
 * 种植日记
 * */
public class PlantDiaryActivity extends BaseActivity implements View.OnClickListener, ImagePickerAdapter.OnRecyclerViewItemClickListener {

    private TextView tvlocation, plantData;
    String addr;//详细地址
    private RecyclerView rvimages;
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片

    EditText etPlantName;//植物名字
    EditText etDynamicstate;//动态

    TextView tvPlantName;
    TextView tvdynamic;//动态

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_diary);

        initView();
        initData();
    }

    public LocationClient mLocationClient = null;

    private void initView() {
        tvlocation = findViewById(R.id.tv_plant_location);
        plantData = findViewById(R.id.tv_plant_data);
        etPlantName = findViewById(R.id.et_plantname);
        etDynamicstate = findViewById(R.id.et_dynamicstate);
        tvPlantName = findViewById(R.id.tv_plantname);
        tvdynamic = findViewById(R.id.tv_dynamic);
        //findViewById(R.id.tv_plant_wancheng).setOnClickListener(this);
        tvlocation.setOnClickListener(this);
        findViewById(R.id.iv_return).setOnClickListener(this);

        //添加商品图片
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.images);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, 1);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        //添加商品图片

        initListener();
    }

    private void initListener() {
        etPlantName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int length = editable.toString().length();
                tvPlantName.setText("植物名字（" + length + "/32）");
            }
        });
        etDynamicstate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int length = editable.toString().length();
                tvdynamic.setText("Ta的动态（"+length+"/280）");
            }
        });
    }

    private void initData() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        plantData.setText(simpleDateFormat.format(date));

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

        //提交
        findViewById(R.id.iv_commit).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_plant_location:
                break;
            case R.id.iv_return:
                finish();
                break;
//            case R.id.tv_plant_wancheng:
////                Intent intentShareLiftActivity = new Intent(PlantDiaryActivity.this, ShareLiftActivity.class);
////                startActivity(intentShareLiftActivity);
//                break;
            case R.id.iv_commit:
                if (adapter != null && adapter.getImages().size() == 0) {
                    Toast.makeText(getActivity(), "请选择图片", Toast.LENGTH_LONG).show();
                    return;
                } else if (Judge.getBoolean_isNull(etPlantName.getText().toString())) {
                    Toast.makeText(getActivity(), "请填写植物名字", Toast.LENGTH_LONG).show();
                    return;
                } else if (Judge.getBoolean_isNull(etDynamicstate.getText().toString())) {
                    Toast.makeText(getActivity(), "请填写你的动态", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    showLoading();
                    StringBuilder sb = new StringBuilder();
                    String[] img=new String[adapter.getImages().size()+1];
                    //String[] keys=adapter.getImages().toArray(new String[adapter.getImages().size()]);
                    for (int i = 0; i < adapter.getImages().size(); i++) {
                        String path = adapter.getImages().get(i).path;
                        String pathbase64 = ImageUtils.bitmapToString(path);
                        if (adapter.getImages().get(i).mimeType.contains("image/jpeg") || adapter.getImages().get(i).mimeType.contains("image/jpg")) {
                           img[i]=pathbase64;

                        }

                    }

                    String base64images = Arrays.toString(img);
                    //String base64images2 = Arrays.toString(keys);
                    int size = adapter.getImages().size();
                    String name = etPlantName.getText().toString();
                    String dynamicstate = etDynamicstate.getText().toString();
                    String token = MyApplication.user.getLoginToken();
                    String weather = getWeather();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
                    //获取当前时间
                    Date date = new Date(System.currentTimeMillis());
                    String time = simpleDateFormat.format(date);
                    Log.i("nihaoma", "  addr " +weather+"size  "+size+"token "+token+" name "+name+" dynamicstate  "+dynamicstate+" time " +time+" base64images "
                            +base64images+"  addr " +weather+"size  "+size);
                  HttpUtils.appAddPlantDiary1(token,name,base64images,dynamicstate,weather,time,String.valueOf(MyApplication.user.getUserId()), new StringCallback() {
                        @Override
                        public void onStart(Request<String, ? extends Request> request) {
                            super.onStart(request);
                            showLoading();
                            Log.i("nihaoma", "onStart");
                        }

                        @Override
                        public void onSuccess(Response<String> response) {
                            Log.i("nihaoma", "onSuccess"+response.body());
                            String body = response.body();
                            try {
                                JSONObject jsonObject=new JSONObject(body);

                                int code=jsonObject.getInt("code");
                                if (code==100)
                                {
                                    Toast.makeText(getActivity(),"提交成功",Toast.LENGTH_LONG).show();
                                    finish();
                                }else {
                                    Toast.makeText(getActivity(),"提交失败",Toast.LENGTH_LONG).show();
                                }

                            } catch (JSONException e) {
                                Toast.makeText(getActivity(),"提交失败",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                            Log.i("nihaoma", "onError"+response.body());
                            Toast.makeText(getActivity(),"提交失败",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();
                            dismissLoading();
                        }
                    });
                }

                break;

        }
    }
    private String getWeather() {
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
                    return spCity+"-"+txt+"-"+tmp;
                }

            }
        }
        return "";
    }
    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case ImagePickerConstant.IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机
                                /**
                                 * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
                                 *
                                 * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
                                 *
                                 * 如果实在有所需要，请直接下载源码引用。
                                 */
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setMultiMode(false);
                                Intent intent = new Intent(getActivity(), ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, ImagePickerConstant.REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                //ImagePicker.getInstance().setMultiMode(true);
                                ImagePicker.getInstance().setSelectLimit(3);
                                Intent intent1 = new Intent(getActivity(), ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                                startActivityForResult(intent1, ImagePickerConstant.REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }

                    }
                }, names);


                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(getActivity(), ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
//                intentPreview.putExtra(ImagePicker.RightImageIsShow, false);
                startActivityForResult(intentPreview, ImagePickerConstant.REQUEST_CODE_PREVIEW);
                break;
        }
    }

    public class MyLocationListener extends BDAbstractLocationListener {


        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            String city = location.getCity();
            String province = location.getProvince();
            addr=city;
            if (!Judge.getBoolean_isNull(city)&&!Judge.getBoolean_isNull(province)) {
               // tvlocation.setText(province+"."+city);
                tvlocation.setText(city);
            }

        }
    }


    private MyLocationListener myListener = new MyLocationListener();

    protected Activity getActivity() {
        return PlantDiaryActivity.this;
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!this.isFinishing()) {
            maxImgCount = 1;
            dialog.show();
        }
        return dialog;
    }

    ArrayList<ImageItem> images = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == ImagePickerConstant.REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == ImagePickerConstant.REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
    }


}
