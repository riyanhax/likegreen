package com.pywl.likegreen.activity.mian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.pywl.likegreen.MainActivity;
import com.pywl.likegreen.MyApplication;
import com.pywl.likegreen.R;
import com.pywl.likegreen.activity.ShortCameraActivity;

import java.util.ArrayList;
import java.util.Date;

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

                startActivityForResult(intentShortCameraActivity, 200);
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
                    MyApplication.imagePicker.getImageLoader().displayImage(this,list.get(0).path,mPhototwo,mPhototwo.getMaxWidth(),mPhototwo.getMaxHeight());
                    mPhotothree.setImageResource(R.drawable.addphotos);
                    break;
                case 3:
                    mPhototwo.setVisibility(View.VISIBLE);
                    mPhotothree.setVisibility(View.VISIBLE);
                    MyApplication.imagePicker.getImageLoader().displayImage(this,list.get(0).path,mPhotoOne,mPhotoOne.getMaxWidth(),mPhotoOne.getMaxHeight());
                    MyApplication.imagePicker.getImageLoader().displayImage(this,list.get(0).path,mPhototwo,mPhototwo.getMaxWidth(),mPhototwo.getMaxHeight());
                    MyApplication.imagePicker.getImageLoader().displayImage(this,list.get(0).path,mPhotothree,mPhotothree.getMaxWidth(),mPhotothree.getMaxHeight());
                    break;
            }


        }
    }
}
