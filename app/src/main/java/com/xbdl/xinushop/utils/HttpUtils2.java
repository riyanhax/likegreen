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
    /**
     * 增加收货地址
     */
    public static void adduserAddress(int userId,String consignee,String contactWay,String province,
                                      String city,String district,String contactAddress,int isDefaultAddress,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.adduserAddress)// 请求方式和请求url
                .params("userId",userId)
                .params("consignee",consignee)
                .params("contactWay",contactWay)
                .params("province",province)
                .params("city",city)
                .params("district",district)
                .params("contactAddress",contactAddress)
                .params("isDefaultAddress",isDefaultAddress)
                .tag("adduserAddress")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 查询用户的收货地址
     */
    public static void getAddressList(int userId, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.getAddressList)// 请求方式和请求url
                .params("userId",userId)
                .tag("getAddressList")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 设置默认收货地址
     */
    public static void setAddressDefault(int UserAddressID, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.setAddressDefault)// 请求方式和请求url
                .params("UserAddressID",UserAddressID)
                .tag("setAddressDefault")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 删除收货地址
     */
    public static void delAddress(int UserAddressID, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.delAddress)// 请求方式和请求url
                .params("UserAddressID",UserAddressID)
                .tag("delAddress")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 修改收货地址
     */
    public static void updateAddress(int UserAddressID,int userId,String consignee,String contactWay,String province,
                                      String city,String district,String contactAddress,int isDefaultAddress,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.updateAddress)// 请求方式和请求url
                .params("UserAddressID",UserAddressID)
                .params("userId",userId)
                .params("consignee",consignee)
                .params("contactWay",contactWay)
                .params("province",province)
                .params("city",city)
                .params("district",district)
                .params("contactAddress",contactAddress)
                .params("isDefaultAddress",isDefaultAddress)
                .tag("updateAddress")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 帐户余额充值
     */
    public static void balanceRecharge(int userId,int rechargeWay,float rechargePrice, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.balanceRecharge)// 请求方式和请求url
                .params("userId",userId)
                .params("rechargeWay",rechargeWay)
                .params("rechargePrice",rechargePrice)
                .tag("balanceRecharge")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 帐户余额提现
     */
    public static void tixian(int userId,int withdrawalWay,float withdrawalPrice,String key,String val, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.tixian)// 请求方式和请求url
                .params("userId",userId)
                .params("withdrawalWay",withdrawalWay)
                .params("withdrawalPrice",withdrawalPrice)
                .params(key,val)
                .tag("tixian")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 我发布的商品
     */
    public static void getCommodityByUserId(int pn,int userId, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.getCommodityByUserId)// 请求方式和请求url
                .params("pn",pn)
                .params("userId",userId)
                .tag("getCommodityByUserId")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 交易明细
     */
    public static void getWalletDetailByInfo(int pn,int userId, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.getWalletDetailByInfo)// 请求方式和请求url
                .params("pn",pn)
                .params("userId",userId)
                .tag("getWalletDetailByInfo")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 查询用户信息
     */
    public static void getUserInfoById(String token,int userId, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.getUserInfoById)// 请求方式和请求url
                .params("token", token)
                .params("user_id",userId)
                .tag("getUserInfoById")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 查询用户视频
     */
    public static void myvideolist(String token,String appkey, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.myvideolist)// 请求方式和请求url
                .params("token", token)
                .params("appkey",appkey)
                .tag("myvideolist")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 查询天气信息
     */
    public static void getweather(String city,String appkey, StringCallback stringCallback) {
        OkGo.<String>get(UrlConstant2.getweather)// 请求方式和请求url
                .params("city", city)
                .params("appkey",appkey)
                .tag("getweather")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
}
