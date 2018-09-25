package com.xbdl.xinushop.utils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.xbdl.xinushop.constant.UrlConstant;

public class HttpUtils2 {

    /**
     * 忘记密码
     */
    public static void forget(String phone, String pwd, String code, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant.regeist)// 请求方式和请求url
                .params("phone", phone)
                .params("password", pwd)
                .params("code", code)
                .tag("forget")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }

}
