package com.xbdl.xinushop.utils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.xbdl.xinushop.constant.UrlConstant;
import com.xbdl.xinushop.constant.UrlConstant2;

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
        OkGo.<String>get(UrlConstant.login)// 请求方式和请求url
                .params("namePhone", phone)
                .params("password", pwd)
                .tag("login")                       // 请求的 tag, 主要用于取消对应的请求
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

    public static void getCategorylist(StringCallback stringCallback) {
        OkGo.<String>get(UrlConstant.getCategorylist)                            // 请求方式和请求url
                .tag("getCategorylist")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }

    /**
     * 发送验证码
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

    /**
     * 添加种植日记
     */
    public static void appAddPlantDiary(String token, String name, String address, String img,
                                        String plantTime, String desc, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant.appAddPlantDiary)// 请求方式和请求url
                .params("token", token)
                .params("name", name)
                .params("address", address)
                .params("img", img)
                .params("plantTime", plantTime)
                .params("desc", desc)
                .tag("appAddPlantDiary")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }

    /**
     * 获取所有标签
     *
     * @param stringCallback
     */
    public static void findAllTag(String token, StringCallback stringCallback) {
        OkGo.<String>get(UrlConstant.findAllTag)                            // 请求方式和请求url
                .tag("findAllTag")
                .params("token", token)
                // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }

    /**
     * 发布帖子  	帖子类型 2、分享生活 3、参与话题 4、精选 5、攻略
     *
     * @param stringCallback
     */
    public static void setPost(String token,
                               String type,
                               String tagID,
                               String topicID,
                               String chooseID,
                               String img,
                               String noteName,
                               String noteContent, StringCallback stringCallback) {



        OkGo.<String>post(UrlConstant.setPost)
                .params("token", token)
                .params("type", type)
                .params("tagID", tagID)
                .params("topicID", topicID)
                .params("chooseID", chooseID)
                .params("img", img)
                .params("noteName", noteName)
                .params("noteContent", noteContent)
                // 请求的 tag, 主要用于取消对应的请求

                .execute(stringCallback);
    }
    /**
     * 获取所有标签
     *
     * @param stringCallback
     */
    public static void findAllSubject(String token, StringCallback stringCallback) {
        OkGo.<String>get(UrlConstant.findAllSubject)                            // 请求方式和请求url
                .tag("findAllSubject")
                .params("token", token)
                // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     *
     * @param stringCallback
     * 查询类型 1、普通查询 2、好友查询 3、查询当前用户的
     */
    public static void findPlantList(String token, String page,String findType,StringCallback stringCallback) {
        OkGo.<String>get(UrlConstant.findPlantList)                            // 请求方式和请求url
                .tag("findPlantList")
                .params("token", token)
                .params("page", page)
                .params("findType", findType)
                // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 获取 优惠券
     *
     * @param stringCallback
     */
    public static void findMydiscountCoupon (String pn,String userId,String status, StringCallback stringCallback) {
        OkGo.<String>get(UrlConstant.findAllSubject)                            // 请求方式和请求url
                .tag("findMydiscountCoupon")
                .params("pn", pn)
                .params("userId", userId)
                .params("status", status)
                // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
}
