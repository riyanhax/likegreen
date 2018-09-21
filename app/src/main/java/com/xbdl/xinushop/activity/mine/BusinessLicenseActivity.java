package com.xbdl.xinushop.activity.mine;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.xbdl.xinushop.R;


import java.util.ArrayList;

/*营业执照上传
* */
public class BusinessLicenseActivity extends AppCompatActivity implements View.OnClickListener {
    private  ImageView photo;
    private View camera;
    private  PopupWindow popupWindow;
    private  ImagePicker imagePicker;
    private ArrayList<ImageItem> images = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_license);
        initView();

    }

    private void initView() {
        photo=(ImageView) findViewById(R.id.iv_business_photo);
        photo.setOnClickListener(this);
        camera = findViewById(R.id.iv_business_camera);
        camera.setOnClickListener(this);
        findViewById(R.id.tv_business_pop).setOnClickListener(this);
        findViewById(R.id.tv_submission).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_business_photo:
            case R.id.iv_business_camera:
                imagePicker = ImagePicker.getInstance();
                imagePicker.setMultiMode(false);
                Intent intent = new Intent(this, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                //ImagePicker.getInstance().setSelectedImages(images);
                startActivityForResult(intent, 100);
                break;
            case R.id.tv_business_pop:
                showuPop();
                break;
            case R.id.tv_submission:
                mysubmission();
                break;
            case R.id.iv_close:
                popupWindow.dismiss();
                break;
        }
    }
    private void mysubmission(){
        if (images!=null){

        }else {
            return;
        }

    }
    private void showuPop() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_business, null);
        View iv_close = contentView.findViewById(R.id.iv_close);
        iv_close.setOnClickListener(this);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        //设置半透明
        WindowManager.LayoutParams lp =this.getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        this.getWindow().setAttributes(lp);
        //恢复正常
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = BusinessLicenseActivity.this.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                BusinessLicenseActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                BusinessLicenseActivity.this.getWindow().setAttributes(lp);
            }
        });
        popupWindow.showAtLocation(BusinessLicenseActivity.this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                Log.v("nihaoma",images.toString());
                imagePicker.getImageLoader().displayImage(this,images.get(0).path,photo,photo.getMaxWidth(),photo.getMaxHeight());
                camera.setVisibility(View.GONE);
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
