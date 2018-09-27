package com.xbdl.xinushop.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.view.MultiImageView;


import java.util.ArrayList;

public class WriteLongPostActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<ImageItem> images = null;
    private MultiImageView imgs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_long_post);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_return).setOnClickListener(this);
        findViewById(R.id.tv_longpost_release).setOnClickListener(this);
        findViewById(R.id.ll_longpost_photo).setOnClickListener(this);
        imgs = (MultiImageView)findViewById(R.id.rv_add_photo);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_return:
                finish();
                break;
            case R.id.tv_longpost_release:
                finish();
                break;
            case R.id.ll_longpost_photo:
                Intent intent = new Intent(this, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                //ImagePicker.getInstance().setSelectedImages(images);
                startActivityForResult(intent, 100);
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
                if (images!=null){
                    ArrayList<String> strings = new ArrayList<>();
                    for(ImageItem imageItem:images){
                        strings.add(imageItem.path);
                    }
                    imgs.setList(strings);
                }
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
