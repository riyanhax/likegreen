package com.xbdl.xinushop.utils;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.netease.cloud.nos.android.core.CallRet;
import com.netease.cloud.nos.android.exception.InvalidChunkSizeException;
import com.netease.cloud.nos.android.exception.InvalidParameterException;
import com.netease.vcloudnosupload.AcceleratorConfig;
import com.netease.vcloudnosupload.NOSUpload;
import com.netease.vcloudnosupload.NOSUploadHandler;
import com.netease.vcloudnosupload.NOSUploadImpl;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.activity.VideoReleaseActivity;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.NetBean;
import com.xbdl.xinushop.constant.UrlConstant2;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VideoUploadUtil  {
    private static final String TAG = "VideoReleaseActivity";
    private File mFile;
    private AcceleratorConfig acceleratorConf;

    private NOSUpload.UploadExecutor executor = null;
    private String mNosToken, mBucket, mObject;
    private static VideoUploadUtil phoneUtil;
     public static VideoUploadUtil getInstance(Application context, File mFile, int type){
         if (phoneUtil == null) {
             synchronized (VideoUploadUtil.class) {
                 if (phoneUtil == null) {
                     phoneUtil = new VideoUploadUtil(context,mFile,type);
                 }
             }
         }
         return phoneUtil;
     }
     private Application context;
   private NOSUpload nosUpload ;
   private String  videoTitle ;
   private int  type ;//视频类型 1 普通视频 2 广告视频
     private VideoUploadUtil(Application context, File mFile,int type){
         this.context=context;
         this.mFile=mFile;
         this.type=type;
         nosUpload=NOSUpload.getInstace(context);
         acceleratorConf= new AcceleratorConfig();//上传设置的参数
         loadDefaultAcceleratorConf();
         if (nosUpload != null) {
             getAccid();

         }
     }

    private void getAccid() {
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
                        uploadInit();
                          /*  config.appKey = "35aaca97cc05a23cd153b9f05c740a52";
                            config.accid = "a01";
                            config.token = "95c7b8398c3692616ec6211947bfeba9b00c6f38"*/
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                Log.v("nihaoma","//申请网易账号onStart 222222222");
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Log.v("nihaoma","222222222");

            }

            @Override
            public void onFinish() {
                super.onFinish();

            }
        });
    }
    private void loadDefaultAcceleratorConf() {
        try {
            acceleratorConf.setConnectionTimeout(Integer.parseInt("30") * 1000);
            acceleratorConf.setRefreshInterval(Integer.parseInt("1") * 1000);
            acceleratorConf.setChunkSize(Integer.parseInt("4") * 1024);
            acceleratorConf.setChunkRetryCount(Integer.parseInt("1"));
            acceleratorConf.setQueryRetryCount(Integer.parseInt("1"));

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
        nosUpload.setAcceConfig(acceleratorConf);
    }
    private void uploadInit() {
        if(mFile == null){
            Toast.makeText(context, "please select file first!", Toast.LENGTH_SHORT).show();
        }
        String name = mFile.getName();

        nosUpload.fileUploadInit(name, null, -1, -1, null, null, -1, null, new NOSUploadHandler.UploadInitCallback() {
            @Override
            public void onSuccess(String nosToken, String bucket, String object) {
                mNosToken = nosToken;
                mBucket = bucket;
                mObject = object;
                Log.v("nihaoma","uploadInit  onSuccess1111111111111");
                httpsUpload();
            }

            @Override
            public void onFail(int code, String msg) {
                Log.v("nihaoma","uploadInit  onFail1111111111111");
            }
        });
    }
    private void httpUpload() {
        if(mFile == null){
            Toast.makeText(context, "please select file first!", Toast.LENGTH_SHORT).show();
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
                                        Toast.makeText(context, "upload success", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(CallRet ret) {
                                        executor = null;
                                        Toast.makeText(context, "upload fail", Toast.LENGTH_SHORT).show();
                                        // progressBar.setProgress(0);
                                    }

                                    @Override
                                    public void onCanceled(CallRet ret) {
                                        executor = null;
                                        Toast.makeText(context, "upload cancel", Toast.LENGTH_SHORT).show();
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
    private String netVideoPath;
    private void queryVideo() {
        List<String> list = new ArrayList<>();
        list.add(mObject);
        nosUpload.videoQuery(list, new NOSUploadHandler.VideoQueryCallback() {
            @Override
            public void onSuccess(List<QueryResItem> list) {
                Log.e(TAG, "list: " + list.toString());
                String  netVideoPath=list.get(0).objectName;
                //setNetVideoPath(netVideoPath);

                sendVideo(list);
            }

            @Override
            public void onFail(int code, String msg) {
                Log.e(TAG, "videoQuery fail: " + msg);

            }
        });
    }
    //传视频信息到后台
   private void sendVideo(List<NOSUploadHandler.VideoQueryCallback.QueryResItem> list) {
        HttpUtils2.appPostVideo2(MyApplication.user.getLoginToken(), type, list.get(0).objectName, videoTitle, new StringCallback() {

            @Override
            public void onSuccess(Response<String> response) {
                Log.v("nihaoma","传送到后台onSuccess"+response.body());
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    if (jsonObject.getInt("code")==100){
                        int videoId = jsonObject.getInt("object");
                        uploadSuccess.UploadSuccess(videoId);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
        Log.v("nihaoma","httpsUpload 222222222222222222222");
        if(mFile == null){
            Toast.makeText(context, "please select file first!", Toast.LENGTH_SHORT).show();
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
                                        Log.v("nihaoma","上传已经完成onSuccess");
                                        executor = null;
                                        /**
                                         *  上传已经完成, 清除该文件对应的uploadcontext
                                         */
                                        nosUpload.setUploadContext(mFile, "");
                                      //  Toast.makeText(context, "upload success", Toast.LENGTH_SHORT).show();
                                        queryVideo();//返回结果
                                    }

                                    @Override
                                    public void onFailure(CallRet ret) {
                                        executor = null;
                                        Log.v("nihaoma","上传未完成onFailure"+ret.getCallbackRetMsg());
                                        //progressBar.setProgress(0);
                                    }

                                    @Override
                                    public void onCanceled(CallRet ret) {
                                        executor = null;
                                       // Toast.makeText(context, "upload cancel", Toast.LENGTH_SHORT).show();
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
                            uploadInit();
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
        okGo = OkGo.getInstance().init(context)                           //必须调用初始化
                .addCommonHeaders(headers);//全局公共头

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
    private UploadSuccess uploadSuccess;
    public void setUploadListener(UploadSuccess uploadSuccess){
        this.uploadSuccess=uploadSuccess;
    }
    public interface UploadSuccess{
        void UploadSuccess(int videoId);
    }
}
