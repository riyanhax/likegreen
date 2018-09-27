package com.xbdl.xinushop.utils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.xbdl.xinushop.constant.UrlConstant;
import com.xbdl.xinushop.constant.UrlConstant2;

import java.util.LinkedHashMap;
import java.util.Map;

public class HttpUtils2 {

    /**
     * 自动登录
     */
    public static void autoLogin(String token, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.autoLogin)// 请求方式和请求url
                .params("token", token)
                .tag("autoLogin")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 修改用户信息
     */
    public static void updataUser(String token,String key,String val, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.updataUser)// 请求方式和请求url
                .params("token",token)
                .params(key,val)
                .tag("updataUser")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }

}
