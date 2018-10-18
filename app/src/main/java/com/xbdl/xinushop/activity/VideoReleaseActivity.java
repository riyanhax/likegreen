package com.xbdl.xinushop.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.netease.cloud.nos.android.core.CallRet;
import com.netease.cloud.nos.android.exception.InvalidChunkSizeException;
import com.netease.cloud.nos.android.exception.InvalidParameterException;
import com.netease.vcloudnosupload.AcceleratorConfig;
import com.netease.vcloudnosupload.NOSUpload;
import com.netease.vcloudnosupload.NOSUploadHandler;


import com.xbdl.xinushop.MainActivity;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.mine.AdMsgInputActivity;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.CallTab;
import com.xbdl.xinushop.bean.NetBean;
import com.xbdl.xinushop.constant.UrlConstant2;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.ToastUtil;
import com.xbdl.xinushop.utils.VideoFileUtil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.load.resource.bitmap.VideoBitmapDecoder.FRAME_OPTION;

/*
* 拍摄完视频后下一步上传
* */
public class VideoReleaseActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "VideoReleaseActivity";
    private View mNextStep;//下一步
    private RadioGroup mVideoType;
    private ImageView   mSelsctVideo;
    private String mVideoPath;
    private AcceleratorConfig acceleratorConf = new AcceleratorConfig();//上传设置的参数
    private NOSUpload nosUpload = NOSUpload.getInstace(VideoReleaseActivity.this);
    private NOSUpload.UploadExecutor executor = null;


    private String mNosToken, mBucket, mObject;
    //private String album;//相册
    private File mFile;
    private EditText videoTitle,musicTitle;
    private View text_music;
    @Override
    protected Activity getActivity() {
        return this;
    }

    public static class HandleMsg {
        public static final int MSG_INIT_SUCCESS = 0;
        public static final int MSG_INIT_FAIL = 1;
        public static final int MSG_QUERYVIDEO_SUCCESS = 2;
        public static final int MSG_QUERYVIDEO_FAIL = 3;
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case HandleMsg.MSG_INIT_SUCCESS: {
                    Toast.makeText(VideoReleaseActivity.this, "init success", Toast.LENGTH_SHORT).show();

                    //txtNetUrl.setText("http://nos.netease.com/" + mBucket + "/" + mObject);
                    break;
                }
                case HandleMsg.MSG_INIT_FAIL: {
                    int code = msg.arg1;
                    String m = (String) msg.obj;
                    Toast.makeText(VideoReleaseActivity.this, "init fail, code: " + code + ", msg: " + m, Toast.LENGTH_SHORT).show();
                    Log.v("nihaoma","code"+code+"   msg   "+m);
                    break;
                }
                case HandleMsg.MSG_QUERYVIDEO_SUCCESS: {
                    //查询成功
                    List<NOSUploadHandler.VideoQueryCallback.QueryResItem> list = (List< NOSUploadHandler.VideoQueryCallback.QueryResItem> ) msg.obj;
                    //Toast.makeText(VideoReleaseActivity.this, "query video success: " + list.toString(), Toast.LENGTH_SHORT).show();

                    break;
                }
                case HandleMsg.MSG_QUERYVIDEO_FAIL: {
                    int code = msg.arg1;
                    String m = (String) msg.obj;
                    Toast.makeText(VideoReleaseActivity.this, "query video fail, code: " + code + ", msg: " + m, Toast.LENGTH_SHORT).show();
                    break;
                }

                default:
                    break;
            }
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_release);

        initView();
        initData();
        loadDefaultAcceleratorConf();
        if (nosUpload != null) {
            final NOSUpload.Config config = new NOSUpload.Config();
            HttpUtils2.getAccid(MyApplication.user.getLoginToken(),new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        Log.v("nihaoma",response.body());
                        String curTime = jsonObject.getString("curTime");
                        String checkSum = jsonObject.getString("checkSum");
                        String accid = jsonObject.getString("accid");
                        String appKey = jsonObject.getString("appKey");
                        String nonce = jsonObject.getString("nonce");
                        int type = jsonObject.getInt("type");
                        if (type==1){
                            //申请网易账号
                            getNetID(curTime, checkSum, accid, nonce, appKey,config);
                        }else {
                            //已经申请过了
                            String token = jsonObject.getString("token");
                            config.appKey = appKey;
                            config.accid = accid;
                            config.token = token;
                            nosUpload.setConfig(config);
                          /*  config.appKey = "35aaca97cc05a23cd153b9f05c740a52";
                            config.accid = "a01";
                            config.token = "95c7b8398c3692616ec6211947bfeba9b00c6f38"*/
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    dismissLoading();
                }

                @Override
                public void onStart(Request<String, ? extends Request> request) {
                    super.onStart(request);
                    showLoading();
                }

                @Override
                public void onError(Response<String> response) {
                    super.onError(response);
                    Log.v("nihaoma","222222222");
                    dismissLoading();
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                    dismissLoading();
                }
            });
     /*       config.appKey = "35aaca97cc05a23cd153b9f05c740a52";
            config.accid = "2513533419133";
            config.token = "8c5c0e0652e8ee2dd098938a3a82e0d3515d65d4";
            nosUpload.setConfig(config);*/
        }
    }

    private void setAccid(String accid, String token) {
        HttpUtils2.setuseraccid(String.valueOf(MyApplication.user.getUserId()), accid, token, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.v("nihaoma","setAccid   "+response.body());
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                Log.v("nihaoma","setAccid  onStart ");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Log.v("nihaoma","setAccid  onError "+response.body()+response);
            }
        });
    }

    //申请网易账号
    private void getNetID(String curTime, String checkSum, String accid, String nonce, final String appKey, final NOSUpload.Config config) {

        initOkgo(appKey,nonce,curTime,checkSum);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("accid",accid);
            jsonObject.put("type",1);
            okGo.<String>post(UrlConstant2.getNetUser)// 请求方式和请求url
                    .upJson(jsonObject)
                    .tag("getNetUser")                       // 请求的 tag, 主要用于取消对应的请求
                    .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                    .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            Log.v("nihaoma","网易返回"+response.body());
                            Gson gson = new Gson();
                            NetBean netBean = gson.fromJson(response.body(), NetBean.class);
                            NetBean.RetBean ret = netBean.getRet();
                            String accid1 = ret.getAccid();
                            String token = ret.getToken();
                            config.appKey = appKey;
                            config.accid = accid1;
                            config.token = token;
                            //返回accid给后台
                            setAccid(accid1,token);
                            nosUpload.setConfig(config);

                        }

                        @Override
                        public void onStart(Request<String, ? extends Request> request) {
                            super.onStart(request);
                            Log.v("nihaoma","网易返回onStart");
                        }

                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                            Log.v("nihaoma","网易返回onError");
                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();
                            Log.v("nihaoma","网易返回onFinish");
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private OkGo okGo;
    private void initOkgo(String AppKey,String Nonce,String CurTime,String CheckSum) {
        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
        HttpHeaders headers = new HttpHeaders();
        headers.put("AppKey", AppKey);    //header不支持中文，不允许有特殊字符
        headers.put("Content-Type", "application/json");
        headers.put("Nonce", Nonce);
        headers.put("CurTime", CurTime);
        headers.put("CheckSum", CheckSum);

        // 详细说明看GitHub文档：https://github.com/jeasonlzy/
        okGo = OkGo.getInstance().init(getApplication())                           //必须调用初始化
                .addCommonHeaders(headers);//全局公共头

    }

    private void initView() {
        mNextStep = findViewById(R.id.rl_nextstep_btn);//下一步
        mNextStep.setOnClickListener(this);
        mVideoType = (RadioGroup)findViewById(R.id.rg_video_type);
        mSelsctVideo =(ImageView) findViewById(R.id.iv_select_video);//封面
        mSelsctVideo.setOnClickListener(this);
        findViewById(R.id.iv_return).setOnClickListener(this);
        videoTitle= findViewById(R.id.et_video_title);
        musicTitle= findViewById(R.id.et_music_title);
        text_music= findViewById(R.id.text_music);

    }

    private void initData() {
        Intent intent = getIntent();
        mVideoPath = intent.getStringExtra("ShortCameraActivity");
       // album = intent.getStringExtra("ShortCameraActivityAlbum");
        if (mVideoPath!=null){
            //setVideoImg(album);
            setVideoImg(mVideoPath);
            Log.v("nihaoma","mVideoPath  "+mVideoPath);
        }
        String music = intent.getStringExtra("music");
        if (music!=null){

            musicTitle.setText(music);
        }else {
            text_music.setVisibility(View.GONE);
            musicTitle.setVisibility(View.GONE);
        }
    }

    private void setVideoImg(String path) {
        mFile = new File(path);
        if (mFile!=null) {
            RequestOptions requestOptions = RequestOptions.frameOf(2);//选择第几贞做封面
            requestOptions.set(FRAME_OPTION, MediaMetadataRetriever.OPTION_CLOSEST);
            requestOptions.transform(new BitmapTransformation() {
                @Override
                protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
                    return toTransform;
                }
                @Override
                public void updateDiskCacheKey(MessageDigest messageDigest) {
                    try {
                        messageDigest.update((VideoReleaseActivity.this.getPackageName() + "RotateTransform").getBytes("utf-8"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            Glide.with(VideoReleaseActivity.this).load(mFile).apply(requestOptions).into(mSelsctVideo);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_return:
                finish();
                break;
            case R.id.rl_nextstep_btn://下一步
                if (TextUtils.isEmpty(videoTitle.getText())){
                    ToastUtil.shortToast(getActivity(),"说点什么吧");
                    return;
                }
                try {
                        acceleratorConf.setConnectionTimeout(Integer.parseInt("30") * 1000);
                        acceleratorConf.setRefreshInterval(Integer.parseInt("1") * 1000);
                        acceleratorConf.setChunkSize(Integer.parseInt("4") * 1024);
                        acceleratorConf.setChunkRetryCount(Integer.parseInt("1"));
                        acceleratorConf.setQueryRetryCount(Integer.parseInt("1"));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (InvalidChunkSizeException e) {
                    e.printStackTrace();
                } catch (InvalidParameterException e) {
                    e.printStackTrace();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
                nosUpload.setAcceConfig(acceleratorConf);


                if (mVideoType.getCheckedRadioButtonId()==R.id.rb_putongvideo){
                    Log.v("nihaoma","普通视频");
                    Intent intentMain = new Intent(VideoReleaseActivity.this, MainActivity.class);
                    startActivity(intentMain);
                    EventBus.getDefault().post(CallTab.MINE);
                    uploadInit();

                    finish();
                }else {
                    Log.v("nihaoma","广告视频");
                    Intent intentAdMsgInputActivity = new Intent(VideoReleaseActivity.this, AdMsgInputActivity.class);
                    startActivity(intentAdMsgInputActivity);
                }
                break;
            case R.id.iv_select_video://点击图片选取封面
                Intent intent = new Intent();
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT){
                    intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                }else {
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                }
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                startActivityForResult(intent, 0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        String path = VideoFileUtil.getPath(VideoReleaseActivity.this, data.getData());
        String substring = path.substring(path.length() - 4);
        if (substring.equals(".jpg")||substring.equals(".png")||substring.equals(".gif")||substring.equals(".tif")){
                 Glide.with(this).load(path).into(mSelsctVideo);

        }else {
            ToastUtil.shortToast(this,"请选择图片文件");
        }
       // setVideoImg(path);
    }

    private void loadDefaultAcceleratorConf() {
        try {
            acceleratorConf.setChunkSize(1024 * 32);
            acceleratorConf.setChunkRetryCount(2);
            acceleratorConf.setConnectionTimeout(10 * 1000);
            acceleratorConf.setSoTimeout(30 * 1000);
            acceleratorConf.setLbsConnectionTimeout(10 * 1000);
            acceleratorConf.setLbsSoTimeout(10 * 1000);
            acceleratorConf.setRefreshInterval(2 * 3600);
            acceleratorConf.setMonitorInterval(120 * 1000);
            acceleratorConf.setMonitorThread(true);
        } catch (InvalidChunkSizeException e) {
            e.printStackTrace();
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
    }
    private void uploadInit() {
        if(mFile == null){
            Toast.makeText(VideoReleaseActivity.this, "please select file first!", Toast.LENGTH_SHORT).show();
        }
        String name = mFile.getName();

        nosUpload.fileUploadInit(name, null, -1, -1, null, null, -1, null, new NOSUploadHandler.UploadInitCallback() {
            @Override
            public void onSuccess(String nosToken, String bucket, String object) {
                mNosToken = nosToken;
                mBucket = bucket;
                mObject = object;
                Message msg = Message.obtain(mHandler, HandleMsg.MSG_INIT_SUCCESS);
                mHandler.sendMessage(msg);
                Log.v("nihaoma","1111111111111");
                httpsUpload();
            }

            @Override
            public void onFail(int code, String msg) {
                Message m = Message.obtain(mHandler, HandleMsg.MSG_INIT_FAIL);
                m.arg1 = code;
                m.obj = msg;
                mHandler.sendMessage(m);
            }
        });
    }

    private void httpUpload() {
        if(mFile == null){
            Toast.makeText(VideoReleaseActivity.this, "please select file first!", Toast.LENGTH_SHORT).show();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                {
                    String uploadContext = null;
                    /**
                     *  查看一个这个文件是否已经上传过，如果上传过就取出它的uploadcontext, 传给NosUpload.putFileByHttp进行断点续传
                     *  当uploadContext是null时, 就从头开始往上传
                     */
                    String tmp = nosUpload.getUploadContext(mFile);
                    if (tmp != null && !tmp.equals("")) {
                        uploadContext = tmp;
                    }
                    try {

                        executor = nosUpload.putFileByHttp(mFile,
                                uploadContext, mBucket, mObject, mNosToken, new NOSUploadHandler.UploadCallback() {
                                    @Override
                                    public void onUploadContextCreate(
                                            String oldUploadContext,
                                            String newUploadContext) {
                                        /**
                                         *  将新的uploadcontext保存起来
                                         */
                                        nosUpload.setUploadContext(mFile, newUploadContext);
                                    }

                                    @Override
                                    public void onProcess(long current, long total) {
                                      //  int pro = (int)((1.0f* current / total) * progressBar.getMax());
                                      //  progressBar.setProgress(pro);
                                    }

                                    @Override
                                    public void onSuccess(CallRet ret) {
                                        executor = null;
                                        /**
                                         *  清除该文件对应的uploadcontext
                                         */
                                        nosUpload.setUploadContext(mFile, "");
                                        Toast.makeText(VideoReleaseActivity.this, "upload success", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(CallRet ret) {
                                        executor = null;
                                        Toast.makeText(VideoReleaseActivity.this, "upload fail", Toast.LENGTH_SHORT).show();
                                       // progressBar.setProgress(0);
                                    }

                                    @Override
                                    public void onCanceled(CallRet ret) {
                                        executor = null;
                                        Toast.makeText(VideoReleaseActivity.this, "upload cancel", Toast.LENGTH_SHORT).show();
                                        //progressBar.setProgress(0);
                                    }
                                });
                        executor.join();
                    } catch (Exception ex) {
                    }

                }
            }
        }).start();
    }

    private void cancelUpload() {
        if (executor != null) {
            executor.cancel();
        }
    }
    private String url="http://jdvodrdjw0c4b.vod.126.net/jdvodrdjw0c4b/";
    private void queryVideo() {
        List<String> list = new ArrayList<>();
        list.add(mObject);
        nosUpload.videoQuery(list, new NOSUploadHandler.VideoQueryCallback() {
            @Override
            public void onSuccess(List<QueryResItem> list) {
                Log.v("nihaoma", "list: " + list.toString());
                sendVideo(list);
                Message msg = Message.obtain(mHandler, HandleMsg.MSG_QUERYVIDEO_SUCCESS);
                msg.obj = list;
                mHandler.sendMessage(msg);
            }

            @Override
            public void onFail(int code, String msg) {
                Log.v("nihaoma", "videoQuery fail: " + msg);
                Message m = Message.obtain(mHandler, HandleMsg.MSG_QUERYVIDEO_FAIL);
                m.arg1 = code;
                m.obj = msg;
                mHandler.sendMessage(m);
            }
        });
    }
        //传视频信息到后台
    private void sendVideo(List<NOSUploadHandler.VideoQueryCallback.QueryResItem> list) {
        HttpUtils2.appPostVideo2(MyApplication.user.getLoginToken(), 1, url + list.get(0).objectName, videoTitle.getText().toString(), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                Log.v("nihaoma","传送到后台onSuccess"+response.body());
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                Log.v("nihaoma","传送到后台onStart");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Log.v("nihaoma","传送到后台onError");
            }

            @Override
            public void onFinish() {
                super.onFinish();
                Log.v("nihaoma","传送到后台onFinish");
            }
        });
    }

    private void httpsUpload() {
        Log.v("nihaoma","222222222222222222222");
        if(mFile == null){
            Toast.makeText(VideoReleaseActivity.this, "please select file first!", Toast.LENGTH_SHORT).show();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                {

                    String uploadContext = null;
                    /**
                     *  查看一个这个文件是否已经上传过，如果上传过就取出它的uploadcontext, 传给NosUpload.putFileByHttp进行断点续传
                     *  当uploadContext是null时, 就从头开始往上传
                     */
                    String tmp = nosUpload.getUploadContext(mFile);
                    if (tmp != null && !tmp.equals("")) {
                        uploadContext = tmp;
                    }
                    try {
                        executor = nosUpload.putFileByHttps(  mFile,
                                uploadContext, mBucket, mObject, mNosToken, new NOSUploadHandler.UploadCallback() {
                                    @Override
                                    public void onUploadContextCreate(
                                            String oldUploadContext,
                                            String newUploadContext) {
                                        /**
                                         *  将新的uploadcontext保存起来
                                         */
                                        Log.v("nihaoma","3333333333333");
                                        nosUpload.setUploadContext(mFile, newUploadContext);
                                    }

                                    @Override
                                    public void onProcess(long current, long total) {
                                        Log.v("nihaoma","4444444444"+total);
//                                        Toast.makeText(MainActivity.this, "onProcess " + current + "/" + total, Toast.LENGTH_SHORT).show();
                                       // int pro = (int)((1.0f* current / total) * progressBar.getMax());
                                       // progressBar.setProgress(pro);
                                    }


                                    @Override
                                    public void onSuccess(CallRet ret) {
                                        Log.v("nihaoma","5655555555");
                                        executor = null;
                                        /**
                                         *  上传已经完成, 清除该文件对应的uploadcontext
                                         */
                                        nosUpload.setUploadContext(mFile, "");
                                        Toast.makeText(VideoReleaseActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                                        queryVideo();//返回结果
                                    }

                                    @Override
                                    public void onFailure(CallRet ret) {
                                        executor = null;
                                        Toast.makeText(VideoReleaseActivity.this, "upload fail", Toast.LENGTH_SHORT).show();
                                        //progressBar.setProgress(0);
                                    }

                                    @Override
                                    public void onCanceled(CallRet ret) {
                                        executor = null;
                                        Toast.makeText(VideoReleaseActivity.this, "upload cancel", Toast.LENGTH_SHORT).show();
                                       // progressBar.setProgress(0);
                                    }
                                });
                        executor.join();
                    } catch (Exception ex) {
                    }

                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    /*    OkGo.getInstance().cancelAll();
        okGo.cancelAll();*/
    }
}
