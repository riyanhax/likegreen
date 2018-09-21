package com.xbdl.xinushop.rx;

import android.content.Intent;


import com.xbdl.xinushop.utils.StringUtils;

import rx.Observer;

/**
 * 基本观察者
 * Created by YSL on 2017/4/17.
 */

public abstract class BaseObserver<T> implements Observer<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
 /*       if (BuildConfig.DEBUG) {
            e.printStackTrace();
        }
        if (e instanceof ServerResult.ServerErrorException) {
            int status = ((ServerResult.ServerErrorException) e).result.getStatus();
            String err = ((ServerResult.ServerErrorException) e).result.getMessage();
            if (StringUtils.isEmpty(err)){
                err = "系统错误";
            }
            if (status == 3 || err.contains("请登录后")){
                //执行注销
                MineScene.quit();
                //发送广播更新数据通知
                BroadcastUtils.sendLocalBroadcast(new Intent(Constants.REFRESH_NOTICAITON));
            }

            if (!BuildConfig.DEBUG && status != 1 && status != 3){ //如果是线上版本且错误码不为1直接忽略err
                err = "网络不稳定,请稍后再试";
            }
            OnFail(status,err);
        }else{
            String err = e.toString();
            if (err.contains("HTTP 504")){
                err = "无网络连接";
            }else if (err.contains("HTTP 502")){
                err = "系统错误";
            }else if (err.contains("HTTP 404")){
                err = "服务无响应";
            }else {
                if (!BuildConfig.DEBUG) err = "网络不稳定,请稍后再试";
            }
            OnFail(5001,err);
        }*/
    }

    @Override
    public void onNext(T t) {
        OnSuccess(t);
    }

    public abstract void OnFail(int code,String err);
    public abstract void OnSuccess(T t);
}
