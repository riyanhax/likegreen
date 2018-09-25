package com.xbdl.xinushop.utils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.xbdl.xinushop.constant.UrlConstant;

public class HttpUtils {
    /**
     * 分享生活列表
     *
     * @param stringCallback
     */
    public static void shareyourlife(StringCallback stringCallback) {
        OkGo.<String>get(UrlConstant.shareyourlife)                            // 请求方式和请求url
                .tag("shareyourlife")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }

    /**
     * 评论列表
     */
    public static void recommentlist(StringCallback stringCallback) {
        OkGo.<String>get(UrlConstant.recommentlist)                            // 请求方式和请求url
                .tag("recommentlist")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }

    /**
     * 评论列表
     */
    public static void notehotandattention(StringCallback stringCallback) {
        OkGo.<String>get(UrlConstant.notehotandattention)                            // 请求方式和请求url
                .tag("notehotandattention")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }

    /**
     * 评论列表
     */
    public static void login(String phone, String pwd, StringCallback stringCallback) {
        OkGo.<String>get(UrlConstant.notehotandattention)// 请求方式和请求url
                .params("namePhone", phone)
                .params("password", pwd)
                .tag("login")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }

    /**
     * 发送验证码
     */
    public static void sendCode(String phone, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant.sendCode)// 请求方式和请求url
                .params("userPhone", phone)
                .tag("sendcode")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }

    /**
     * 注册
     */
    public static void register(String phone, String pwd, String code, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant.regeist)// 请求方式和请求url
                .params("phone", phone)
                .params("password", pwd)
                .params("code", code)
                .tag("register")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    public static void getCategorylist(StringCallback stringCallback){
        OkGo.<String>get(UrlConstant.getCategorylist)                            // 请求方式和请求url
                .tag("getCategorylist")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
}
