package com.xbdl.xinushop.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.netease.cloud.nos.android.core.CallRet;
import com.netease.cloud.nos.android.exception.InvalidChunkSizeException;
import com.netease.cloud.nos.android.exception.InvalidParameterException;
import com.netease.vcloudnosupload.AcceleratorConfig;
import com.netease.vcloudnosupload.NOSUpload;
import com.netease.vcloudnosupload.NOSUploadHandler;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.activity.VideoReleaseActivity;
import com.xbdl.xinushop.base.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VideoUploadUtil {
    private static final String TAG = "VideoReleaseActivity";
    private File mFile;
    private AcceleratorConfig acceleratorConf;

    private NOSUpload.UploadExecutor executor = null;
    private String mNosToken, mBucket, mObject;
    private static VideoUploadUtil phoneUtil;
     public static VideoUploadUtil getInstance(BaseActivity context, File mFile, Handler mHandler){
         if (phoneUtil == null) {
             synchronized (VideoUploadUtil.class) {
                 if (phoneUtil == null) {
                     phoneUtil = new VideoUploadUtil(context,mFile,mHandler);
                 }
             }
         }
         return phoneUtil;
     }
     private BaseActivity context;
     private Handler mHandler;
   private NOSUpload nosUpload ;
     private VideoUploadUtil(BaseActivity context, File mFile, Handler mHandler){
         this.context=context;
         this.mFile=mFile;
         this.mHandler=mHandler;
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
                    if (jsonObject.getInt("code")==200){
                        config.appKey = jsonObject.getString("appkey");
                        config.accid = jsonObject.getString("accid");
                        config.token = jsonObject.getString("token");
                        nosUpload.setConfig(config);
                    }else {
                        ToastUtil.shortToast(context,"数据解析错误请返回再试");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                context.dismissLoading();
            }

            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
                context. showLoading();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Log.v("nihaoma","222222222");
                context.dismissLoading();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                context.dismissLoading();
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
                Message msg = Message.obtain(mHandler, VideoReleaseActivity.HandleMsg.MSG_INIT_SUCCESS);
                mHandler.sendMessage(msg);
                Log.v("nihaoma","1111111111111");
                httpsUpload();
            }

            @Override
            public void onFail(int code, String msg) {
                Message m = Message.obtain(mHandler, VideoReleaseActivity.HandleMsg.MSG_INIT_FAIL);
                m.arg1 = code;
                m.obj = msg;
                mHandler.sendMessage(m);
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

    private void queryVideo() {
        List<String> list = new ArrayList<>();
        list.add(mObject);
        nosUpload.videoQuery(list, new NOSUploadHandler.VideoQueryCallback() {
            @Override
            public void onSuccess(List<QueryResItem> list) {
                Log.e(TAG, "list: " + list.toString());
                Message msg = Message.obtain(mHandler, VideoReleaseActivity.HandleMsg.MSG_QUERYVIDEO_SUCCESS);
                msg.obj = list;
                mHandler.sendMessage(msg);

            }

            @Override
            public void onFail(int code, String msg) {
                Log.e(TAG, "videoQuery fail: " + msg);
                Message m = Message.obtain(mHandler, VideoReleaseActivity.HandleMsg.MSG_QUERYVIDEO_FAIL);
                m.arg1 = code;
                m.obj = msg;
                mHandler.sendMessage(m);
            }
        });
    }

    private void httpsUpload() {
        Log.v("nihaoma","222222222222222222222");
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
                                        Log.v("nihaoma","5655555555");
                                        executor = null;
                                        /**
                                         *  上传已经完成, 清除该文件对应的uploadcontext
                                         */
                                        nosUpload.setUploadContext(mFile, "");
                                        Toast.makeText(context, "upload success", Toast.LENGTH_SHORT).show();
                                        queryVideo();//返回结果
                                    }

                                    @Override
                                    public void onFailure(CallRet ret) {
                                        executor = null;
                                        Toast.makeText(context, "upload fail", Toast.LENGTH_SHORT).show();
                                        //progressBar.setProgress(0);
                                    }

                                    @Override
                                    public void onCanceled(CallRet ret) {
                                        executor = null;
                                        Toast.makeText(context, "upload cancel", Toast.LENGTH_SHORT).show();
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
}
