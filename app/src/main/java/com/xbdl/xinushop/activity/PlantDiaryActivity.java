package com.xbdl.xinushop.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.mine.ShareLiftActivity;
import com.xbdl.xinushop.adapter.ImagePickerAdapter;
import com.xbdl.xinushop.constant.ImagePickerConstant;
import com.xbdl.xinushop.utils.Base64Util;
import com.xbdl.xinushop.utils.Judge;
import com.xbdl.xinushop.view.SelectDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.xbdl.xinushop.MyApplication.maxImgCount;


/*
 * 种植日记
 * */
public class PlantDiaryActivity extends AppCompatActivity implements View.OnClickListener, ImagePickerAdapter.OnRecyclerViewItemClickListener {

    private TextView tvlocation, plantData;
    String addr;//详细地址
    private RecyclerView rvimages;
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_diary);


        initView();
        initData();
    }

    public LocationClient mLocationClient = null;

    private void initView() {
        tvlocation = (TextView) findViewById(R.id.tv_plant_location);
        plantData = (TextView) findViewById(R.id.tv_plant_data);

        findViewById(R.id.tv_plant_wancheng).setOnClickListener(this);
        tvlocation.setOnClickListener(this);


        //添加商品图片
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.images);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, 6);
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
        plantData.setText("默认日期" + simpleDateFormat.format(date));

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
            case R.id.tv_plant_wancheng:
//                Intent intentShareLiftActivity = new Intent(PlantDiaryActivity.this, ShareLiftActivity.class);
//                startActivity(intentShareLiftActivity);
                break;
            case R.id.iv_commit:
                String me= Base64Util.Bitmap2StrByBase64(BitmapFactory.decodeFile(adapter.getImages().get(0).path));
                Log.i("","");
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
                                ImagePicker.getInstance().setSelectLimit(6 - selImageList.size());
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
        String addr;

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

    private Activity getActivity() {
        return PlantDiaryActivity.this;
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!this.isFinishing()) {
            maxImgCount = 6;
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
