package com.xbdl.xinushop.activity.mian;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.netease.vcloudnosupload.NOSUpload;
import com.netease.vcloudnosupload.NOSUploadHandler;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.ShortCameraActivity;
import com.xbdl.xinushop.activity.VideoReleaseActivity;
import com.xbdl.xinushop.activity.mine.AdMsgInputActivity;
import com.xbdl.xinushop.activity.mine.BusinessLicenseActivity;
import com.xbdl.xinushop.activity.mine.IDCardCheckActivity;
import com.xbdl.xinushop.activity.mine.LivePreViewActivity;
import com.xbdl.xinushop.adapter.note.PlantListImgAdapter;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.ImageUtils;
import com.xbdl.xinushop.utils.SharedPreferencesUtil;
import com.xbdl.xinushop.utils.ToastUtil;
import com.xbdl.xinushop.utils.VideoFileUtil;
import com.xbdl.xinushop.utils.VideoUploadUtil;
import com.xbdl.xinushop.view.MultiImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.bumptech.glide.load.resource.bitmap.VideoDecoder.FRAME_OPTION;


public class ApplyLiveActivity extends BaseActivity implements View.OnClickListener {
    private  EditText goodsname,goodsdetails;
    private ImageView mPhotoOne,mshortVideo;
    private RecyclerView recyclerView;
    private ArrayList<ImageItem> images = null;
    private TextView tv_living_time,et_contact,et_phone_number;
    private CheckBox cb_agree;
    private ImageView rb_person_authentication,rb_business_authentication;
    private boolean idcheck,businessCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_live);
        initView();
        initData();
        initCustomTimePicker();
    }



    private void initView() {
       findViewById(R.id.iv_return).setOnClickListener(this);
       findViewById(R.id.rl_data_picker).setOnClickListener(this);//选择开播时间
         goodsname = (EditText)findViewById(R.id.et_goodsname);
         goodsdetails = (EditText)findViewById(R.id.et_goodsdetails);
         mPhotoOne = (ImageView)findViewById(R.id.apply_live_photo_one);
        recyclerView = findViewById(R.id.recyclerView);
         mshortVideo = (ImageView)findViewById(R.id.apply_live_shortvideo);//短视频拍摄
        findViewById(R.id.tv_submission).setOnClickListener(this);//提交
        mPhotoOne.setOnClickListener(this);
        mshortVideo.setOnClickListener(this);
        findViewById(R.id.rl_addliveshop).setOnClickListener(this);
         tv_living_time = findViewById(R.id.tv_living_time);
        et_contact = findViewById(R.id.et_contact);
        et_phone_number = findViewById(R.id.et_phone_number);
        rb_person_authentication = findViewById(R.id.rb_person_authentication);
        rb_business_authentication = findViewById(R.id.rb_business_authentication);
        cb_agree = findViewById(R.id.cb_agree);
    }
    private void initData() {
         idcheck = SharedPreferencesUtil.getBoolean(getActivity(), MyConstants.IDCheck, false);
         businessCheck = SharedPreferencesUtil.getBoolean(getActivity(), MyConstants.businessCheck, false);
         rb_person_authentication.setImageResource(!idcheck?R.drawable.weixuanzhong:R.drawable.xuanzhong);
         rb_business_authentication.setImageResource(!businessCheck?R.drawable.weixuanzhong:R.drawable.xuanzhong);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_return:
                finish();
                break;
            case R.id.rl_data_picker:
                pvCustomTime.show();
                break;
            case R.id.apply_live_photo_one:
                openPhoneCammer();
            break;
            case R.id.apply_live_shortvideo:
                Intent intentShortCameraActivity= new Intent(this, ShortCameraActivity.class);
                intentShortCameraActivity.putExtra("ApplyLiveActivity","ApplyLiveActivity");
                startActivityForResult(intentShortCameraActivity, 200);
                break;
            case R.id.tv_submission://提交
                if (TextUtils.isEmpty(goodsname.getText())){
                    ToastUtil.shortToast(getActivity(),"请输入商品名");
                    return;
                }
                if (TextUtils.isEmpty(goodsdetails.getText())){
                    ToastUtil.shortToast(getActivity(),"请输入商品详情");
                    return;
                }

                if (TextUtils.isEmpty(et_phone_number.getText())){
                    ToastUtil.shortToast(getActivity(),"请输入联系电话");
                    return;
                }
                if (TextUtils.isEmpty(et_contact.getText())){
                    ToastUtil.shortToast(getActivity(),"请输入联系人");
                    return;
                }
                if (images==null||images.size()==0){
                    ToastUtil.shortToast(getActivity(),"请选择直播图片");
                    return;
                }
                if (videoPath==null){
                    ToastUtil.shortToast(getActivity(),"请拍摄预告视频");
                    return;
                }

                if (tv_living_time.getText().equals("设定开播时间")){
                    ToastUtil.shortToast(getActivity(),"请设定开播时间");
                    return;
                }
                if (!cb_agree.isChecked()){
                    ToastUtil.shortToast(getActivity(),"请点击同意承诺以上信息真实可靠");
                    return;
                }
                //先上传视频到网易成功再上传到后台


                final String pathbase64 = ImageUtils.bitmapToString(images.get(0).path);
                //传到后台

                VideoUploadUtil uploadUtil = VideoUploadUtil.getInstance(getApplication(), mFile, 2);
                uploadUtil.setUploadListener(new VideoUploadUtil.UploadSuccess() {
                    @Override
                    public void UploadSuccess(int videoId) {

                        HttpUtils2.livestreamingAdd(goodsname.getText().toString(), goodsdetails.getText().toString(), videoId, time,
                                et_contact.getText().toString(), et_phone_number.getText().toString(), 1, pathbase64,
                                MyApplication.user.getLoginToken(),0, new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        Log.v("nihaoma","申请直播返回  "+response.body());
                                        //返回previewVideo  40   images  String
                                        ToastUtil.shortToast(getActivity(),"申请直播间成功");
                                    }
                                });
                    }
                });


                Intent intentLivePreViewActivity = new Intent(this, LivePreViewActivity.class);
                startActivity(intentLivePreViewActivity);
                finish();
                break;
            case R.id.rl_addliveshop://商品链接
                Intent AdMsgInputintent = new Intent(this, AdMsgInputActivity.class);
                startActivity(AdMsgInputintent);

                break;
        }
    }
    //打开相机
    private void openPhoneCammer() {
        ImagePicker.getInstance().setMultiMode(false);
        Intent intent = new Intent(this, ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
        //ImagePicker.getInstance().setSelectedImages(images);
        startActivityForResult(intent, 100);
    }
    String videoPath;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                Log.v("nihaoma",images.toString());
                setImages(images);
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }else if (resultCode==ShortCameraActivity.APPLY_LIVE){
            if (data!=null&&requestCode==200){
                videoPath = data.getStringExtra("videoPath");
                mFile = new File(videoPath);
               /* String path = VideoFileUtil.getPath(getActivity(), shortCameraActivity);
                String substring = path.substring(path.length() - 4);
                if (substring.equals(".jpg")||substring.equals(".png")||substring.equals(".gif")||substring.equals(".tif")){
                    Glide.with(this).load(path).into(mSelsctVideo);

                }else {
                    ToastUtil.shortToast(this,"请选择图片文件");
                }*/
                Log.v("nihaoma",videoPath);
                setVideoImg(videoPath);
            }
        }

    }


    private File mFile;
    private void setVideoImg(String path) {
        mFile = new File(path);
        if (mFile!=null) {
            RequestOptions requestOptions = RequestOptions.frameOf(5);//选择第几贞做封面
            requestOptions.set(FRAME_OPTION, MediaMetadataRetriever.OPTION_CLOSEST);
            requestOptions.transform(new BitmapTransformation() {
                @Override
                protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
                    return toTransform;
                }
                @Override
                public void updateDiskCacheKey(MessageDigest messageDigest) {
                    try {
                        messageDigest.update((ApplyLiveActivity.this.getPackageName() + "RotateTransform").getBytes("utf-8"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            Glide.with(ApplyLiveActivity.this).load(mFile).apply(requestOptions).into(mshortVideo);
        }
    }
    private void setImages(ArrayList<ImageItem> list){
        if (list!=null){
            PlantListImgAdapter plantListImgAdapter = new PlantListImgAdapter(getActivity());
            recyclerView.setVisibility(View.VISIBLE);
            plantListImgAdapter.setDataList(list);
            recyclerView.setLayoutManager(new GridLayoutManager(this, list.size()));
            recyclerView.setAdapter(plantListImgAdapter);

            plantListImgAdapter.setClick(new PlantListImgAdapter.MyClick() {
                @Override
                public void click() {
                    openPhoneCammer();
                }
            });
            if (list.size()==6){
                mPhotoOne.setVisibility(View.GONE);
            }

        }
    }

    @Override
    protected Activity getActivity() {
        return this;
    }



    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
    TimePickerView pvCustomTime;
    String time;
    private void initCustomTimePicker() {

        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH), startDate.get(Calendar.DAY_OF_MONTH));
        Calendar endDate = Calendar.getInstance();

        endDate.set(endDate.get(Calendar.YEAR)+1, 12, 31);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                String tempStr = getTime(date).substring(0, getTime(date).length()-3);
                time= getTime(date);
                tv_living_time.setText("设定开播时间为："+tempStr);
            }
        })
                /*.setType(TimePickerView.Type.ALL)//default is all
                .setCancelText("Cancel")
                .setSubmitText("Sure")
                .setContentTextSize(18)
                .setTitleSize(20)
                .setTitleText("Title")
                .setTitleColor(Color.BLACK)
               /*.setDividerColor(Color.WHITE)//设置分割线的颜色
                .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setTitleBgColor(Color.DKGRAY)//标题背景颜色 Night mode
                .setBgColor(Color.BLACK)//滚轮背景颜色 Night mode
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)*/
                /*.animGravity(Gravity.RIGHT)// default is center*/
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setContentTextSize(18)
                .setType(new boolean[]{ true, true, true,true, true, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .build();

    }


}
