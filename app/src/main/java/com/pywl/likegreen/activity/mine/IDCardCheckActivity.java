package com.pywl.likegreen.activity.mine;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.pywl.likegreen.MyApplication;
import com.pywl.likegreen.R;

import java.util.ArrayList;

public class IDCardCheckActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView previewfront,previewback;
    private ArrayList<ImageItem> front,imgback;
    private View idcard_front,idCardBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idcard_check);
        initView();

    }

    private void initView() {
         idcard_front = findViewById(R.id.iv_idcard_front);
         idcard_front.setOnClickListener(this);
         idCardBack = findViewById(R.id.iv_idcard_back);
        idCardBack.setOnClickListener(this);
        findViewById(R.id.tv_submission).setOnClickListener(this);
         previewfront= (ImageView)findViewById(R.id.iv_previewfront);//身份证正面
         previewfront.setOnClickListener(this);
         previewback = (ImageView)findViewById(R.id.iv_previewback);
        previewback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_idcard_front:
            case R.id.iv_previewfront:

                Intent intentPerview = new Intent(this, ImageGridActivity.class);
                intentPerview.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                startActivityForResult(intentPerview, 100);
                break;
            case R.id.iv_idcard_back:
            case R.id.iv_previewback:
                Intent intentBack = new Intent(this, ImageGridActivity.class);
                intentBack.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                startActivityForResult(intentBack, 200);
                break;

            case R.id.tv_submission:
                submissionImg();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                front = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                Log.v("nihaoma",front.toString());
                MyApplication.imagePicker.getImageLoader().displayImage(this,front.get(0).path,previewfront,previewfront.getMaxWidth(),previewfront.getMaxHeight());
                idcard_front.setVisibility(View.GONE);
            }else if (data != null && requestCode == 200) {
                imgback = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                MyApplication.imagePicker.getImageLoader().displayImage(this,front.get(0).path,previewback,previewback.getMaxWidth(),previewback.getMaxHeight());
                idCardBack.setVisibility(View.GONE);
                Log.v("nihaoma",imgback.toString());
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }

    }
    private void submissionImg(){
        if (front!=null&&imgback!=null){

        }else {
            Toast.makeText(this, "请拍照后提交", Toast.LENGTH_SHORT).show();
        }
    }
}
