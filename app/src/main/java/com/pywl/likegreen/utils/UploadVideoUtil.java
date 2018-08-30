package com.pywl.likegreen.utils;

import android.content.Context;

import android.util.Log;
import android.widget.Toast;

import com.netease.cloud.nos.android.core.CallRet;
import com.netease.cloud.nos.android.exception.InvalidChunkSizeException;
import com.netease.cloud.nos.android.exception.InvalidParameterException;
import com.netease.vcloudnosupload.AcceleratorConfig;
import com.netease.vcloudnosupload.NOSUpload;
import com.netease.vcloudnosupload.NOSUploadHandler;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UploadVideoUtil {
    private  Context context;
    private static UploadVideoUtil s = null;
    private AcceleratorConfig acceleratorConf = new AcceleratorConfig();
    private NOSUpload nosUpload = NOSUpload.getInstace(context);
    private NOSUpload.UploadExecutor executor = null;


    private String mNosToken, mBucket, mObject;

    private File mFile;
    private UploadVideoUtil(Context context) {
        this.context=context;
        loadDefaultAcceleratorConf();
    }

    //同步代码快的demo加锁，安全高效
    public static UploadVideoUtil getInStanceBlock(Context context){
        if(s==null)
            synchronized (UploadVideoUtil.class) {
                if(s==null)
                    s = new UploadVideoUtil(context);
            }

        return s;

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
            Toast.makeText(context, "please select file first!", Toast.LENGTH_SHORT).show();
        }
        String name = mFile.getName();

        nosUpload.fileUploadInit(name, null, -1, -1, null, null, -1, null, new NOSUploadHandler.UploadInitCallback() {
            @Override
            public void onSuccess(String nosToken, String bucket, String object) {
                mNosToken = nosToken;
                mBucket = bucket;
                mObject = object;
               // Message msg = Message.obtain(mHandler, VideoReleaseActivity.HandleMsg.MSG_INIT_SUCCESS);
               // mHandler.sendMessage(msg);
                Log.v("nihaoma","1111111111111");
                httpsUpload();
            }

            @Override
            public void onFail(int code, String msg) {
               // Message m = Message.obtain(mHandler, VideoReleaseActivity.HandleMsg.MSG_INIT_FAIL);
               // m.arg1 = code;
              //  m.obj = msg;
               // mHandler.sendMessage(m);
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
    //返回上传的路径
    private List<NOSUploadHandler.VideoQueryCallback.QueryResItem> queryVideo() {
        List<String> list = new ArrayList<>();
        final List<NOSUploadHandler.VideoQueryCallback.QueryResItem> callbackList = new ArrayList<>();
        list.add(mObject);
        nosUpload.videoQuery(list, new NOSUploadHandler.VideoQueryCallback() {
            @Override
            public void onSuccess(List<QueryResItem> list) {
                callbackList.addAll(list);
                /*Log.e(TAG, "list: " + list.toString());
                Message msg = Message.obtain(mHandler, VideoReleaseActivity.HandleMsg.MSG_QUERYVIDEO_SUCCESS);
                msg.obj = list;
                mHandler.sendMessage(msg);*/
            }

            @Override
            public void onFail(int code, String msg) {
                Toast.makeText(context, "上传不成功，请重新上传", Toast.LENGTH_SHORT).show();
             /*   Log.e(TAG, "videoQuery fail: " + msg);
                Message m = Message.obtain(mHandler, VideoReleaseActivity.HandleMsg.MSG_QUERYVIDEO_FAIL);
                m.arg1 = code;
                m.obj = msg;
                mHandler.sendMessage(m);*/
            }
        });
        return callbackList;
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
