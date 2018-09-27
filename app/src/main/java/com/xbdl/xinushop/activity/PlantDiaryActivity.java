package com.xbdl.xinushop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.ImagePickerAdapter;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.constant.ImagePickerConstant;
import com.xbdl.xinushop.utils.HttpUtils;
import com.xbdl.xinushop.utils.ImageUtils;
import com.xbdl.xinushop.utils.Judge;
import com.xbdl.xinushop.utils.SharedPreferencesUtil;
import com.xbdl.xinushop.view.SelectDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        //findViewById(R.id.tv_plant_wancheng).setOnClickListener(this);
        tvlocation.setOnClickListener(this);


        //添加商品图片
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.images);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, 1);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        //添加商品图片
    }

    private void initData() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 ");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        plantData.setText( simpleDateFormat.format(date));

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
                    String base64image = null;

                        base64image = "data:image/jpg;base64,"+ImageUtils.bitmapToString(images.get(0).path);

                    String name = etPlantName.getText().toString();
                    String dynamicstate = etDynamicstate.getText().toString();
                    String time = plantData.getText().toString();
                  String userjson=  SharedPreferencesUtil.getString(getActivity(), MyConstants.User,"");
                  String token="";
                    PersonBean personBean=          new Gson().fromJson(userjson, PersonBean.class);
                    token=personBean.getLoginToken();
                    HttpUtils.appAddPlantDiary(token, name, addr, base64image, time, dynamicstate, new StringCallback() {
                        @Override
                        public void onStart(Request<String, ? extends Request> request) {
                            super.onStart(request);
                            showLoading();
                            Log.i("asdf","onStart");
                        }

                        @Override
                        public void onSuccess(Response<String> response) {
                            String body=response.body();
                            Log.i("asdf","success"+body);
                        }

                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                            Log.i("asdf","err");
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
                                ImagePicker.getInstance().setSelectLimit(1 - selImageList.size());
                                Intent intent = new Intent(getActivity(), ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, ImagePickerConstant.REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
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
            addr = location.getAddrStr();    //获取详细地址信息
            if (!Judge.getBoolean_isNull(addr)) {
                tvlocation.setText(addr);
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
