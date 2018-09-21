package com.xbdl.xinushop.activity.mian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.ShortCameraActivity;
import com.xbdl.xinushop.activity.mine.LivePreViewActivity;

import java.util.ArrayList;


public class ApplyLiveActivity extends AppCompatActivity implements View.OnClickListener {
    private  EditText goodsname,goodsdetails;
    private ImageView mPhotoOne,mPhototwo,mPhotothree,mshortVideo;
    private ArrayList<ImageItem> images = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_live);
        initView();
    }
    private void initView() {
       findViewById(R.id.iv_return).setOnClickListener(this);
       findViewById(R.id.rl_data_picker).setOnClickListener(this);//选择开播时间
         goodsname = (EditText)findViewById(R.id.et_goodsname);
         goodsdetails = (EditText)findViewById(R.id.et_goodsdetails);
         mPhotoOne = (ImageView)findViewById(R.id.apply_live_photo_one);
         mPhototwo = (ImageView)findViewById(R.id.apply_live_photo_two);
         mPhotothree = (ImageView)findViewById(R.id.apply_live_photo_three);
         mshortVideo = (ImageView)findViewById(R.id.apply_live_shortvideo);//短视频拍摄
        findViewById(R.id.tv_submission).setOnClickListener(this);//提交
        mPhotoOne.setOnClickListener(this);
        mPhototwo.setOnClickListener(this);
        mPhotothree.setOnClickListener(this);
        mshortVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.iv_return:
                finish();
                break;
            case R.id.rl_data_picker:

                break;
            case R.id.apply_live_photo_one:
            case R.id.apply_live_photo_two:
            case R.id.apply_live_photo_three:
                Intent intent = new Intent(this, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                //ImagePicker.getInstance().setSelectedImages(images);
                startActivityForResult(intent, 100);
            break;
            case R.id.apply_live_shortvideo:
                Intent intentShortCameraActivity= new Intent(this, ShortCameraActivity.class);
                intentShortCameraActivity.putExtra("ApplyLiveActivity","ApplyLiveActivity");
                startActivityForResult(intentShortCameraActivity, 200);
                break;
            case R.id.tv_submission://提交
                Intent intentLivePreViewActivity = new Intent(this, LivePreViewActivity.class);
                startActivity(intentLivePreViewActivity);
                finish();
                break;
        }
    }
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
        }
        if (resultCode==ShortCameraActivity.APPLY_LIVE){
            if (data!=null&&requestCode==200){
                String shortCameraActivity = data.getStringExtra("videoPath");
                Log.v("nihaoma",shortCameraActivity);
            }
        }

    }
    private void setImages(ArrayList<ImageItem> list){
        if (list!=null){
            switch (list.size()){
                case 1:
                    MyApplication.imagePicker.getImageLoader().displayImage(this,list.get(0).path,mPhotoOne,mPhotoOne.getMaxWidth(),mPhotoOne.getMaxHeight());
                    mPhototwo.setVisibility(View.VISIBLE);
                    mPhototwo.setImageResource(R.drawable.addphotos);
                    mPhotothree.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    mPhototwo.setVisibility(View.VISIBLE);
                    mPhotothree.setVisibility(View.VISIBLE);
                    MyApplication.imagePicker.getImageLoader().displayImage(this,list.get(0).path,mPhotoOne,mPhotoOne.getMaxWidth(),mPhotoOne.getMaxHeight());
                    MyApplication.imagePicker.getImageLoader().displayImage(this,list.get(1).path,mPhototwo,mPhototwo.getMaxWidth(),mPhototwo.getMaxHeight());
                    mPhotothree.setImageResource(R.drawable.addphotos);
                    break;
                case 3:
                    mPhototwo.setVisibility(View.VISIBLE);
                    mPhotothree.setVisibility(View.VISIBLE);
                    MyApplication.imagePicker.getImageLoader().displayImage(this,list.get(0).path,mPhotoOne,mPhotoOne.getMaxWidth(),mPhotoOne.getMaxHeight());
                    MyApplication.imagePicker.getImageLoader().displayImage(this,list.get(1).path,mPhototwo,mPhototwo.getMaxWidth(),mPhototwo.getMaxHeight());
                    MyApplication.imagePicker.getImageLoader().displayImage(this,list.get(2).path,mPhotothree,mPhotothree.getMaxWidth(),mPhotothree.getMaxHeight());
                    break;
            }


        }
    }
}