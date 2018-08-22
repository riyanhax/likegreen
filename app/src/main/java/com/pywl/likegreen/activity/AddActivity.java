package com.pywl.likegreen.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.aliyun.common.utils.StorageUtils;
import com.aliyun.demo.recorder.AliyunVideoRecorder;
import com.aliyun.struct.common.CropKey;
import com.aliyun.struct.common.VideoQuality;
import com.aliyun.struct.recorder.CameraType;
import com.aliyun.struct.recorder.FlashType;
import com.aliyun.struct.snap.AliyunSnapVideoParam;
import com.pywl.likegreen.R;
import com.pywl.likegreen.aliyun.Utils;

import java.io.File;

/*
* 加号
* */
public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    String[] eff_dirs;
    private View mClose,mShortVideo;//关闭，短视频
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        initData();
        copyAssets();
    }

    private void initView() {
         mClose = findViewById(R.id.rl_add_close);//关闭
         mClose.setOnClickListener(this);
         mShortVideo = findViewById(R.id.ll_add_shortvideo);//短视频
         mShortVideo.setOnClickListener(this);
    }

    private void initData() {


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_add_close://关闭
                setCallbackMain();
                finish();
                break;
            case R.id.ll_add_shortvideo://短视频
                startShortVideo();

        }
    }
    private static final int PROGRESS_360P = 25;
    private static final int PROGRESS_480P = 50;
    private static final int PROGRESS_540P = 75;
    private static final int PROGRESS_720P = 100;
    private static final int REQUEST_CROP = 2002;

    private void startShortVideo() {
        String path = StorageUtils.getCacheDirectory(this).getAbsolutePath() + File.separator+ Utils.QU_NAME + File.separator;
        File filter = new File(new File(path), "filter");

        String[] list = filter.list();
        if(list == null || list.length == 0){
            return ;
        }
        eff_dirs = new String[list.length + 1];
        eff_dirs[0] = null;
        for(int i = 0; i < list.length; i++){
            eff_dirs[i + 1] = filter.getPath() + "/" + list[i];
        }
        AliyunSnapVideoParam recordParam = new AliyunSnapVideoParam.Builder()
                //设置录制分辨率，目前支持360p，480p，540p，720p
                .setResulutionMode(PROGRESS_360P)
                //设置视频比例，目前支持1:1,3:4,9:16
                .setRatioMode(AliyunSnapVideoParam.RATIO_MODE_9_16)
                .setRecordMode(AliyunSnapVideoParam.RECORD_MODE_AUTO) //设置录制模式，目前支持按录，点录和混合模式
                .setFilterList(eff_dirs) //设置滤镜地址列表,具体滤镜接口接收的是一个滤镜数组
                .setBeautyLevel(80) //设置美颜度
                .setBeautyStatus(true) //设置美颜开关
                .setCameraType(CameraType.FRONT) //设置前后置摄像头
                .setFlashType(FlashType.ON) // 设置闪光灯模式
                .setNeedClip(true) //设置是否需要支持片段录制
                .setMaxDuration(30000) //设置最大录制时长 单位毫秒
                .setMinDuration(2000) //设置最小录制时长 单位毫秒
                .setVideQuality(VideoQuality.HD) //设置视频质量
                .setGop(5) //设置关键帧间隔
                .setVideoBitrate(2000) //设置视频码率，如果不设置则使用视频质量videoQulity参数计算出码率
                .setSortMode(AliyunSnapVideoParam.SORT_MODE_VIDEO)//设置导入相册过滤选择视频
                .build();
        AliyunVideoRecorder.startRecordForResult(this,REQUEST_CROP,recordParam);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode ==  REQUEST_CROP){
            if(resultCode == Activity.RESULT_OK && data!= null){
                int type = data.getIntExtra(AliyunVideoRecorder.RESULT_TYPE,0);
                if(type ==  AliyunVideoRecorder.RESULT_TYPE_CROP){
                    String path = data.getStringExtra(CropKey.RESULT_KEY_CROP_PATH);
                    Toast.makeText(this,"文件路径为 "+ path + " 时长为 " +
                            data.getLongExtra(CropKey.RESULT_KEY_DURATION,0),Toast.LENGTH_SHORT).show();

                }else if(type ==  AliyunVideoRecorder.RESULT_TYPE_RECORD){
                    Toast.makeText(this,"文件路径为 "+
                            data.getStringExtra(AliyunVideoRecorder.OUTPUT_PATH),Toast.LENGTH_SHORT).show();
                }
            }else if(resultCode == Activity.RESULT_CANCELED){
                Toast.makeText(this,"用户取消录制",Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void copyAssets() {
        new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] params) {
                Utils.copyAll(AddActivity.this);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                mShortVideo.setEnabled(true);
                initAssetPath();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
    private void initAssetPath() {
        String path = StorageUtils.getCacheDirectory(this).getAbsolutePath() + File.separator + Utils.QU_NAME + File.separator;
        File filter = new File(new File(path), "filter");

        String[] list = filter.list();
        if (list == null || list.length == 0) {
            return;
        }
        eff_dirs = new String[list.length + 1];
        eff_dirs[0] = null;
        for (int i = 0; i < list.length; i++) {
            eff_dirs[i + 1] = filter.getPath() + "/" + list[i];
        }
    }
    @Override
    public void onBackPressed() {
        setCallbackMain();
        super.onBackPressed();

        finish();
    }
    private  void setCallbackMain(){
        Intent intent = new Intent();
        setResult(2, intent);
    }
}
