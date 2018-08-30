package com.pywl.likegreen.activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.transcoding.TranscodingAPI;
import com.netease.transcoding.TranscodingNative;
import com.netease.transcoding.record.MediaRecord;
import com.netease.transcoding.record.MessageHandler;
import com.netease.vcloud.video.effect.VideoEffect;
import com.netease.vcloud.video.render.NeteaseView;
import com.netease.vcloudnosupload.util.FileUtil;
import com.pywl.likegreen.R;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static com.netease.transcoding.TranscodingAPI.TRAN_MIX_FILE_PARSE_ERROR;
import static com.netease.transcoding.TranscodingAPI.TRAN_OUT_FILE_CREATE_ERROR;
import static com.netease.transcoding.TranscodingAPI.TRAN_PARA_NULL;
import static com.netease.transcoding.TranscodingAPI.TRAN_PRE_IS_NOT_FINISH;
import static com.netease.transcoding.TranscodingAPI.TRAN_PROCESS_ERROR;
import static com.netease.transcoding.TranscodingAPI.TRAN_SOURCE_FILE_PARSE_ERROR;
import static com.netease.transcoding.TranscodingAPI.TRAN_SOURCE_NO_VIDEO_OR_AUDIO;


/*
* 发布短视频点进来的照相机
* */
public class ShortCameraActivity extends AppCompatActivity implements MessageHandler,View.OnClickListener {
    private String appkey="4edf106797fe7de29d4cffc6bf073691";


    private static final int SET_COUNT_TIME = 0;
    private static final int ShortVideoProcess_CHOOSE = 100;
    private  View mTakePhotoHead,mTakePhotofilter,mTakePhotoAlbum, mTakePhotoSecond,
            mTakePhotoStr,mTakePhotoCancel,mTakePhotoChoose, musicChoose, voiceBtn;//顶部一栏,滤镜,拍照按钮,相册,读秒，音乐,声音
   private  ImageView mTakePhotoBtn,switchCamera,flashBtn;//录像按钮，切换前后摄像头
    private boolean ishide=true;
    private int countTime=0;//录像时间
    private TextView mTvCountTime;
    private NeteaseView videoView;
    private String videoFilePath;
    //拼接视频
    private ArrayList<String> videoFiles= new ArrayList<>();
    private AsyncTask mShortVideoProcessTask;
    //伴音广播
    private audioMixVolumeMsgReceiver audioMixVolumeMsgReceiver;
    long clickTime = 0L;
    /**
     * SDK 相关参数
     **/
    private MediaRecord mMediaRecord = null;
    private volatile boolean mRecording = false;
    private DateFormat formatter_file_name = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.getDefault());


    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SET_COUNT_TIME:
                  int t= (int) msg.obj;
                    Log.v("nihaoma",""+t);
                    mTvCountTime.setText(String.valueOf(t));
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livestreaming);
        //应用运行时，保持屏幕高亮，不锁屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.screenBrightness = 0.7f;
        getWindow().setAttributes(params);
        setContentView(R.layout.activity_short_camera);

        //以下为SDK调用主要步骤，请用户参考使用
        //1、创建录制实例
        MediaRecord.MediaRecordPara mediaRecordPara = new MediaRecord.MediaRecordPara();
        mediaRecordPara.setAppKey(appkey);  //APPKEY
        mediaRecordPara.setContext(getApplicationContext()); //APP上下文
        mediaRecordPara.setMessageHandler(this); //消息回调
        mMediaRecord = new MediaRecord(mediaRecordPara);

        boolean frontCamera = true; // 是否前置摄像头
        boolean scale_16x9 = false; //是否强制16:9
        initView();
        initData();
        //2、 预览参数设置
        MediaRecord.VideoQuality videoQuality = MediaRecord.VideoQuality.SUPER_HIGH; //视频模板（SUPER_HIGH 1280*720、SUPER 960*540、HIGH 640*480、MEDIUM 480*360）
        mMediaRecord.startVideoPreview(videoView, frontCamera, videoQuality, scale_16x9);
        mMediaRecord.setBeautyLevel(2); //磨皮强度为5,共5档，0为关闭
        mMediaRecord.setFilterStrength(0.5f); //滤镜强度
        mMediaRecord.setFilterType(VideoEffect.FilterType.clean);
        //伴音
        audioMixVolumeMsgReceiver = new audioMixVolumeMsgReceiver();
        IntentFilter audioMixVolumeIntentFilter = new IntentFilter();
        audioMixVolumeIntentFilter.addAction("AudioMixVolume");
        audioMixVolumeIntentFilter.addAction("AudioMix");
        registerReceiver(audioMixVolumeMsgReceiver, audioMixVolumeIntentFilter);
    }

    private void initView() {
         mTakePhotoHead = findViewById(R.id.rl_takephoto_head);//顶部一栏
         mTakePhotofilter = findViewById(R.id.ll_takephoto_filter);//滤镜
         mTakePhotoAlbum = findViewById(R.id.ll_takephoto_album);//相册
         mTakePhotoSecond = findViewById(R.id.rl_takephoto_sc_bg);//读秒
         mTakePhotoBtn = (ImageView)findViewById(R.id.iv_takephoto_btn);//拍照
         mTakePhotoBtn.setOnClickListener(this);
         mTakePhotoStr = findViewById(R.id.tv_takephoto_str);//点击拍照字
         mTakePhotoCancel = findViewById(R.id.iv_takephoto_cancel);//取消
         mTakePhotoChoose = findViewById(R.id.iv_takephoto_choose);//确认//录制完成
         mTakePhotoChoose.setOnClickListener(this);
         mTvCountTime = (TextView)findViewById(R.id.tv_takephoto_counttime);
         videoView=(NeteaseView)findViewById(R.id.videoview);
         switchCamera = (ImageView)findViewById(R.id.iv_switch_camera);//切换前后摄像头
        switchCamera.setOnClickListener(this);
        flashBtn = (ImageView) findViewById(R.id.iv_flash_btn);//闪光灯
        flashBtn.setOnClickListener(this);
        musicChoose = findViewById(R.id.iv_music_svideo);//背景音乐选择
        musicChoose.setOnClickListener(this);
        voiceBtn = findViewById(R.id.iv_voice_svideo);//声音
        voiceBtn.setOnClickListener(this);
    }




    private void initData() {
        setTime();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_takephoto_btn:
                hideView(ishide);
                break;
            case R.id.iv_takephoto_choose://录制完成  确认
                if (videoFilePath!=null){
                 //合成视频
                   syntheticVideo(videoFiles);
           /*     Intent intentVideoReleaseActivity = new Intent(ShortCameraActivity.this, VideoReleaseActivity.class);

                startActivity(intentVideoReleaseActivity);
                finish();*/
                }
                break;
            case R.id.iv_switch_camera:   //切换前后摄像头
                switchCamera();
                break;
            case R.id.iv_flash_btn://闪光灯
                flashCamera();
                break;
            case R.id.iv_music_svideo://音乐
                openFileChoose(ShortVideoProcess_CHOOSE);
                break;
            case R.id.iv_voice_svideo://声音

                break;
        }
    }


    private void hideView(boolean b) {
        if (b){
            mTakePhotoHead.setVisibility(View.GONE);
            mTakePhotofilter.setVisibility(View.GONE);
            mTakePhotoAlbum.setVisibility(View.GONE);
            mTakePhotoStr.setVisibility(View.GONE);
            mTakePhotoSecond.setVisibility(View.VISIBLE);
            mTakePhotoBtn.setImageResource(R.drawable.recording);
            mTakePhotoCancel.setVisibility(View.GONE);
            mTakePhotoChoose.setVisibility(View.GONE);
            musicChoose.setVisibility(View.GONE);
            voiceBtn.setVisibility(View.GONE);
            videoFilePath =Environment.getExternalStorageDirectory() + "/transcode/" + formatter_file_name.format(new Date()) + ".mp4";
            videoFiles.add(videoFilePath);//视频文件传到提交界面
            mMediaRecord.startRecord(videoFilePath);
            ishide=false;
        }else {
            mTakePhotoHead.setVisibility(View.VISIBLE);
            mTakePhotofilter.setVisibility(View.VISIBLE);
            mTakePhotoAlbum.setVisibility(View.VISIBLE);
            mTakePhotoStr.setVisibility(View.VISIBLE);
            mTakePhotoSecond.setVisibility(View.GONE);
            mTakePhotoBtn.setImageResource(R.drawable.photograph);
            if (countTime==0){
                mTakePhotoCancel.setVisibility(View.GONE);
                mTakePhotoChoose.setVisibility(View.GONE);
            }else {
                mTakePhotoCancel.setVisibility(View.VISIBLE);
                mTakePhotoChoose.setVisibility(View.VISIBLE);
                musicChoose.setVisibility(View.VISIBLE);
                voiceBtn.setVisibility(View.VISIBLE);
                switchCamera.setVisibility(View.GONE);
                flashBtn.setVisibility(View.GONE);
            }
            mMediaRecord.stopRecord();
            ishide=true;
        }
    }
    private Thread threadTime;
    private boolean threadStart;
    private void setTime() {
        threadTime= new Thread(new Runnable() {
                @Override
                public void run() {
                    while (countTime<60) {
                        if (!ishide){
                        try {
                            countTime++;
                            Thread.sleep(1000);
                            Message message = Message.obtain();
                            message.what = SET_COUNT_TIME;
                            message.obj = countTime;
                            mHandler.sendMessage(message);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }}
            });
        threadTime.start();
        }


    //用于接收Service发送的消息，伴音音量
    public class audioMixVolumeMsgReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            //音量
            float audioMixVolumeMsg = intent.getFloatExtra("AudioMixVolumeMSG", -1);
            if(audioMixVolumeMsg != -1 && mMediaRecord != null){
                mMediaRecord.setMusicVolume(audioMixVolumeMsg);
            }

            //伴音文件
            int audioMixMsg = intent.getIntExtra("AudioMixMSG", 0);
            String fileName = intent.getStringExtra("AudioMixFilePathMSG");

            //伴音开关的控制
            if(audioMixMsg == 1)
            {
                if(mMediaRecord != null) {
                    try{
                        AssetFileDescriptor descriptor = context.getAssets().openFd("mixAudio/"+ fileName);
                        FileDescriptor fd = descriptor.getFileDescriptor();
                        mMediaRecord.startPlayMusic(fd,descriptor.getStartOffset(),descriptor.getLength(),false);
                        mMediaRecord.setMusicVolume(0.2f);
//                        mMediaRecord.musicSeekTo(1000); //伴音seek
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }

                }
            }
            else if (audioMixMsg == 2)
            {
                if(mMediaRecord != null){
                    mMediaRecord.resumePlayMusic();
                }
            }
            else if(audioMixMsg == 3)
            {
                if(mMediaRecord != null){
                    mMediaRecord.pausePlayMusic();
                }
            }
            else if(audioMixMsg == 4)
            {
                if(mMediaRecord != null){
                    mMediaRecord.stopPlayMusic();
                }
            }
        }
    }
    //切换前后摄像头
    private void switchCamera() {
        if (mMediaRecord != null) {
            mMediaRecord.switchCamera();
        }
    }
    private boolean mFlashOn = false;
    private void flashCamera(){
        if(mMediaRecord != null){
            mFlashOn = !mFlashOn;
            mMediaRecord.setCameraFlashPara(mFlashOn);
        }
    }
    //处理SDK抛上来的异常和事件，用户需要在这里监听各种消息，进行相应的处理。
    @Override
    public void handleMessage(int msg, Object object) {
        switch (msg) {
            case MSG_INIT_RECORD_VERIFY_ERROR:
                showToast("鉴权失败，请检查APPkey");
                finish();
                break;
            case MSG_START_PREVIEW_FINISHED:
                Log.d("nihaoma","开启预览成功");
                break;
            case MSG_START_CAMERA_ERROR:
                showToast("开启相机失败，请检查相机权限");
                finish();
                break;
            case MSG_START_AUDIO_ERROR:
                showToast("开启录音失败，请检查麦克风权限");
                finish();
                break;
            case MSG_START_RECORD_ERROR:
                showToast("开启录制失败");
                break;
            case MSG_START_RECORD_FINISHED:
                showToast("录制已开启");
                mRecording = true;
                break;
            case MSG_STOP_RECORD_FINISHED:
                if(!(Boolean) object){
                    showToast("录制停止失败，删除录制文件");
                }else {
                    showToast("录制已停止");
                }
                mRecording = false;
                break;
            case MSG_SWITCH_CAMERA_FINISHED:
                showToast("相机切换成功");
                break;
            case MSG_CAMERA_NOT_SUPPORT_FLASH:
                showToast("不支持闪光灯");
                break;
            case MSG_START_CAPTURE_FINISHED:
                final Bitmap bitmap = (Bitmap) object;
                new Thread() {
                    public void run(){
                        if(bitmap != null){
                            FileOutputStream outStream = null;
                            String screenShotFilePath = Environment.getExternalStorageDirectory() + "/transcode/" +
                                    formatter_file_name.format(new Date()) + "_" +
                                    bitmap.getWidth() + "x" + bitmap.getHeight() +
                                    ".png";
                            try {
                                outStream = new FileOutputStream(String.format(screenShotFilePath));
                                bitmap.compress(Bitmap.CompressFormat.PNG,100,outStream);
                                showToast("截图已保存到：" + screenShotFilePath);
                            }catch (Exception e){
                                e.printStackTrace();
                            }finally {
                                if(outStream != null){
                                    try {
                                        outStream.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }.start();

                break;
            default:
                break;
        }
    }

    private Toast mToast;
    private void showToast(final String text) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast == null) {
                    mToast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                }
                mToast.setText(text);
                mToast.show();
            }
        });
    }
    @Override
    protected void onDestroy() {

        if(mMediaRecord != null){
            if(mRecording){
                mMediaRecord.stopRecord();
            }
            mMediaRecord.stopVideoPreview();
            mMediaRecord.destroyVideoPreview();
            mMediaRecord.unInit();
        }
        unregisterReceiver(audioMixVolumeMsgReceiver);
        if(mShortVideoProcessTask != null && mShortVideoProcessTask.getStatus() != AsyncTask.Status.FINISHED){
            mShortVideoProcessTask.cancel(true);
        }
        if(mShortVideoProcessTask != null &&!mShortVideoProcessTask.isCancelled()){
            mShortVideoProcessTask.cancel(true);
        }
        mShortVideoProcessTask = null;
   /*     mShortVideoProcess_out_file = null;
        mShortVideoProcess_multi_source_1 = null;
        mShortVideoProcess_multi_source_2 = null;
        mShortVideoProcess_multi_source_3 = null;
        mMulti_input_file_fade_time = null;
        mMulti_input_file_target_width = null;
        mMulti_input_file_target_height = null;
        mCrop_x_pos = null;
        mCrop_y_pos = null;
        mCrop_width = null;
        mCrop_height = null;
        mScale_ratio = null;
        mWatermark_x_pos = null;
        mWatermark_y_pos = null;
        mWatermark_offset = null;
        mWatermark_out_time = null;
        mWatermark_rect = null;
        mChartlet_frquency = null;
        mChartlet_x_pos = null;
        mChartlet_y_pos = null;
        mChartlet_offset = null;
        mChartlet_duration = null;
        mChartlet_rect = null;
        mFile_offset = null;
        mFile_duration = null;
        mAudioMerge_file = null;
        mVideo_adjust_volume = null;
        mAudioMerge_audio_volume = null;
        mAudioMerge_fade_time = null;*/
        TranscodingAPI.getInstance().unInit();

        super.onDestroy();
    }
    private int videoWidth=720;//拼接宽
    private int videoHeight=1280;//拼接高
    private int fadeTime=200;//多文件拼接淡入淡出时间
    private int adjustVolume=1;//视频音量调节系数0-2
    private String outPutVideoPath;//合并文件输出路径
    private String mAudioMerge_file;//混音文件路径
    private Float mAudioMerge_audio_volume=0.3f;//伴音音量
    private int mAudioMerge_fade_time=2000;//伴音开始时间
    //合成视频
    private void syntheticVideo(ArrayList<String> list) {
        TranscodingAPI.TranSource tranSource = new TranscodingAPI.TranSource();
        String[] stockArr1 = new String[list.size()];
        stockArr1 = list.toArray(stockArr1);
        tranSource.setFilePaths(stockArr1);  //转码文件数组
        tranSource.setVideoFadeDuration(fadeTime);
        tranSource.setAudioVolume(adjustVolume);
        tranSource.setMergeWidth(videoWidth);
        tranSource.setMergeHeight(videoHeight);
        TranscodingAPI.TranscodePara transcodePara = new TranscodingAPI.TranscodePara();
        //输出路径
        TranscodingAPI.TranOut tranOut = new TranscodingAPI.TranOut();
        outPutVideoPath=Environment.getExternalStorageDirectory() + "/相机/"+formatter_file_name.format(new Date()) + ".mp4";
        tranOut.setFilePath(outPutVideoPath);
        //伴音
        if (mAudioMerge_file!=null) {
            TranscodingAPI.TranMixAudio tranMixAudio = new TranscodingAPI.TranMixAudio();
            tranMixAudio.setFilePath(mAudioMerge_file);
            tranMixAudio.setMixVolume(mAudioMerge_audio_volume);
            tranMixAudio.setFadeDuration(mAudioMerge_fade_time);
            transcodePara.setMixAudio(tranMixAudio);
        }
        transcodePara.setSource(tranSource);  //必须

        transcodePara.setOut(tranOut); //必须

/*        //以下参数为非必须参数，用户可根据需要决定设置哪些参数
        transcodePara.setWaterMarks(new TranscodingAPI.TranWaterMark[]{tranWaterMark,tranStringWaterMark}); //水印，需要时添加，否则不用设置
        transcodePara.setDynamicWater(tranDynamicWater); //动态水印，需要时添加，否则不用设置
        transcodePara.setCrop(tranCrop);//视频宽高裁剪，需要时添加，否则不用设置
        transcodePara.setChangeSpeed(tranSpeedRate);//音视频加减速播放，需要时添加，否则不用设置
        transcodePara.setScale(tranScale);//视频等比例缩放，需要时添加，否则不用设置
        transcodePara.setTimeCut(tranTimeCut);//媒体文件时长剪辑，需要时添加，否则不用设置
        transcodePara.setMixAudio(tranMixAudio);//混音，需要时添加，否则不用设置
        transcodePara.setFilter(tranFilter);//转码滤镜，需要时添加，否则不用设置*/
        mShortVideoProcessTask = new AsyncTask<TranscodingAPI.TranscodePara,Integer,Integer>(){

            ProgressDialog dialog;
            @Override
            protected Integer doInBackground(TranscodingAPI.TranscodePara... params) {
                TranscodingAPI.TranscodePara transcodePara = params[0];
                transcodePara.getOut().setCallBack(new TranscodingNative.NativeCallBack(){
                    @Override
                    public void progress(int progress, int total) {
                        dialog.setMax(total);
                        publishProgress(progress);
                    }
                });

                return TranscodingAPI.getInstance().VODProcess(transcodePara);
            }

            @Override
            protected void onPreExecute() {
                dialog = new ProgressDialog(ShortCameraActivity.this);
                dialog.setMessage("开始短视频处理");
                dialog.setCancelable(true);
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        TranscodingAPI.getInstance().stopVODProcess();
                    }
                });
                dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                dialog.show();
            }

            @Override
            protected void onCancelled() {
                if(dialog != null && dialog.isShowing()){
                    dialog.dismiss();
                    dialog = null;
                }
            }

            @Override
            protected void onProgressUpdate(Integer[] values) {
                dialog.setMessage("短视频处理中，请稍后...");
                dialog.setProgress(values[0]);
            }

            @Override
            protected void onPostExecute(Integer ret) {
                dialog.dismiss();
                dialog = null;
                switch (ret){
                    case TRAN_PARA_NULL:
                        showToast("短视频处理失败，输入文件为空");
                        break;
                    case TRAN_OUT_FILE_CREATE_ERROR:
                        showToast("短视频处理失败，无法创建目标文件，请检查目标文件地址或SD卡权限");
                        break;
                    case TRAN_PRE_IS_NOT_FINISH:
                        showToast("短视频处理失败，上一次未处理完毕");
                        break;
                    case TRAN_SOURCE_FILE_PARSE_ERROR:
                        showToast("短视频处理失败，原始文件解析失败");
                        break;
                    case TRAN_SOURCE_NO_VIDEO_OR_AUDIO:
                        showToast("短视频处理失败，原始文件没有视频或音频");
                        break;
                    case TRAN_MIX_FILE_PARSE_ERROR:
                        showToast("短视频处理失败，混音文件解析失败");
                        break;
                    case TRAN_PROCESS_ERROR:
                        showToast("短视频处理失败，媒体文件不支持，或参数设置错误");
                        break;
                    default:
                        showToast("视频处理已完成");
                        break;
                }
                startUploadActivity();
            }
        }.execute(transcodePara);
    }

    private void startUploadActivity() {
        if (outPutVideoPath!=null){
        Intent intentVideoReleaseActivity = new Intent(ShortCameraActivity.this, VideoReleaseActivity.class);
        intentVideoReleaseActivity.putExtra("ShortCameraActivity",outPutVideoPath);
        startActivity(intentVideoReleaseActivity);
        finish();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK || data.getData() == null) {
            return;
        }
        String path = FileUtil.getPath(this, data.getData());
        switch (requestCode){

            case ShortVideoProcess_CHOOSE:      //混音文件
                String substring = path.substring(path.length() - 4);
                if (substring.equals(".mp3")||substring.equals(".m4a")){
                    mAudioMerge_file=path;
                    int start=path.lastIndexOf("/");
                    int end=path.lastIndexOf(".");
                    if(start!=-1 && end!=-1){
                        String substring1 = path.substring(start+1, end);
                        showToast("选择了音乐："+substring1);
                    }

                }else {
                    showToast("请选择音乐文件");
                }
                break;
            default:
                break;
        }
    }
    //文件选择
   private void openFileChoose(int requestCode){

        Intent intent = new Intent();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT){
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        }else {
            intent.setAction(Intent.ACTION_GET_CONTENT);
        }
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent, requestCode);
    }
}


