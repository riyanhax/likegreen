package com.xbdl.xinushop.utils;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.constant.UrlConstant;
import com.xbdl.xinushop.constant.UrlConstant2;

import org.json.JSONException;
import org.json.JSONObject;

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
    public static void adduserAddress(String token,int userId,String consignee,String contactWay,String province,
                                      String city,String district,String contactAddress,int isDefaultAddress,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.adduserAddress)// 请求方式和请求url
                .params("token",token)
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
    public static void getAddressList(String token,int userId, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.getAddressList)// 请求方式和请求url
                .params("token",token)
                .params("userId",userId)
                .tag("getAddressList")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 设置默认收货地址
     */
    public static void setAddressDefault(String token,int UserAddressID, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.setAddressDefault)// 请求方式和请求url
                .params("token",token)
                .params("UserAddressID",UserAddressID)
                .tag("setAddressDefault")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 删除收货地址
     */
    public static void delAddress(String token,int UserAddressID, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.delAddress)// 请求方式和请求url
                .params("token",token)
                .params("UserAddressID",UserAddressID)
                .tag("delAddress")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 修改收货地址
     */
    public static void updateAddress(String token,int UserAddressID,int userId,String consignee,String contactWay,String province,
                                      String city,String district,String contactAddress,int isDefaultAddress,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.updateAddress)// 请求方式和请求url
                .params("token",token)
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
    public static void balanceRecharge(String token,int userId,int rechargeWay,float rechargePrice, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.balanceRecharge)// 请求方式和请求url
                .params("token",token)
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
    public static void tixian(String token,int userId,int withdrawalWay,float withdrawalPrice,String key,String val, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.tixian)// 请求方式和请求url
                .params("token",token)
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
    public static void getCommodityByUserId(String token,int pn,int userId, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.getCommodityByUserId)// 请求方式和请求url
                .params("token",token)
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
    public static void getWalletDetailByInfo(String token,int pn,int userId, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.getWalletDetailByInfo)// 请求方式和请求url
                .params("token",token)
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
    public static void myvideolist(String token,String status, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.myvideolist)// 请求方式和请求url
                .params("token", token)
                .params("status",status)
                .tag("myvideolist")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 查询用户视频
     */
    public static void myvideoAlllist(String token, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.myvideolist)// 请求方式和请求url
                .params("token", token)
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
    /**
     * 查询网易accid
     */
    public static void getAccid(String token,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.getaccid)// 请求方式和请求url
                .params("token", token)
                .tag("getaccid")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 设置网易accid到后台
     */
    public static void setuseraccid(String userid,String accid,String token,StringCallback stringCallback) {
        JSONObject json = new JSONObject();
        JSONObject user = new JSONObject();
        String sid = String.valueOf(userid);
        try {

            json.put("accid", accid);
            json.put("token", token);
            json.put("userId", sid);
            //Log.v("nihaoma",json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<String>post(UrlConstant2.setuseraccid)// 请求方式和请求url
               /*.params("userId", userid)
                .params("accid", accid)
                .params("token", token)*/
                .upJson(json)
                .tag("setuseraccid")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /*  .params("userid", userid)
                .params("accid", accid)
                .params("token", token)*/
    /**
     * 领取优惠券
     */
    public static void collectionOfCouponsApi(String token,int userId,int couponsId,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.collectionOfCouponsApi)// 请求方式和请求url
                .params("token", token)
                .params("userId", userId)
                .params("couponsId", couponsId)
                .tag("collectionOfCouponsApi")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 获取我出售商品的交易详情
     */
    public static void getSellerOrdersByUserIdApi(String token,int pn,int userId,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.getSellerOrdersByUserIdApi)// 请求方式和请求url
                .params("token", token)
                .params("pn", pn)
                .params("userId", userId)
                .tag("getSellerOrdersByUserIdApi")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 上传账号视频到后台 有音乐  有商品
     */
    public static void appPostVideo(String token,int type,String url,String headline,String music,String commodityId,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appPostVideo)// 请求方式和请求url
                .params("token", token)
                .params("type", type)
                .params("url", url)
                .params("headline", headline)
                .params("music", music)
                .params("commodityId", commodityId)
                .tag("appPostVideo")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 上传账号视频到后台 音乐商品有1
     */
    public static void appPostVideo1(String token,int type,String url,String headline,String key,String val,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appPostVideo)// 请求方式和请求url
                .params("token", token)
                .params("type", type)
                .params("url", url)
                .params("headline", headline)
                .params(key, val)
                .tag("appPostVideo")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 上传账号视频到后台 普通视频，没有音乐
     */
    public static void appPostVideo2(String token,int type,String url,String headline,StringCallback stringCallback) {
        JSONObject json = new JSONObject();
        try {

            json.put("type", type);
            json.put("url", url);
            json.put("headline", headline);
            Log.v("nihaoma",json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<String>post(UrlConstant2.appPostVideo+"/"+ MyApplication.user.getLoginToken())// 请求方式和请求url
                /*.params("type", type)
                .params("url", url)
                .params("headline", headline)*/
                .upJson(json)
                .tag("appPostVideo")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 获取推荐视频
     */
    public static void suggestedVideos(StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.suggestedVideos)// 请求方式和请求url
                .tag("suggestedVideos")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 获取最新视频
     */
    public static void selectNewest(StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.selectNewest)// 请求方式和请求url
                .tag("selectNewest")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 查询我的粉丝，我的关注列表
     */
    public static void myFansLsit(String token,int page,int followType,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.myFansLsit)// 请求方式和请求url
                .params("token", token)
                .params("page", page)
                .params("followType", followType)
                .tag("myFansLsit")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 关注谁谁谁
     */
    public static void appAddConcern(String token,int userId,int beConcernUserId,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appAddConcern)// 请求方式和请求url
                .params("token", token)
                .params("userId", userId)
                .params("beConcernUserId", beConcernUserId)
                .tag("appAddConcern")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 视频直播评论判断是否已点赞
     */
    public static void appCheckClickToPraise(String token,int clickToPraiseUserId,int clickToPraiseType,int clickToPraiseTypeId,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appCheckClickToPraise)// 请求方式和请求url
                .params("clickToPraiseType", clickToPraiseType)
                .params("clickToPraiseTypeId", clickToPraiseTypeId)
                .params("clickToPraiseUserId", clickToPraiseUserId)
                .params("token", token)
                .tag("appAddConcern")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
}
