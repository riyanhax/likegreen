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
    private String token=MyApplication.user.getLoginToken();
    private int userId=MyApplication.user.getUserId();
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
            json.put("token",MyApplication.user.getLoginToken());
            json.put("type", type);
            json.put("url", url);
            json.put("headline", headline);
            Log.v("nihaoma",json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<String>post(UrlConstant2.appPostVideo)// 请求方式和请求url
                .params("token", token)
                .params("type", type)
                .params("url", url)
                .params("headline", headline)
               /* .upJson(json)*/
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
     * ，获取当前用户关注列表
     */
    public static void appGetMyAttention(String token,int page,int userId,StringCallback stringCallback) {


        OkGo.<String>post(UrlConstant2.appGetMyAttention)// 请求方式和请求url
                .params("pn", page)
                .params("userId", userId)
                .params("token", token)
                .tag("myFansLsit")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * ，关注我的列表
     */
    public static void appGetWhoWatchingMe(String token,int page,int userId,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appGetWhoWatchingMe)// 请求方式和请求url
                .params("pn", page)
                .params("userId", userId)
                .params("token", token)
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
                .params("userId", userId)
                .params("beConcernUserId", beConcernUserId)
                .params("token", token)
                .tag("appAddConcern")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 取消关注
     */
    public static void appCancelYourAttention(String token,int userId,int beConcernUserId,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appCancelYourAttention)// 请求方式和请求url
                .params("userId", userId)
                .params("beConcernUserId", beConcernUserId)
                .params("token", token)
                .tag("appCancelYourAttention")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 视频直播评论判断是否已点赞
     */
    public static void appCheckClickToPraise(int clickToPraiseType,int clickToPraiseTypeId,int clickToPraiseUserId,String token,StringCallback stringCallback) {
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
    /**
     * 视频直播评论点赞
     */
    public static void appAddClickToPraise(int clickToPraiseType,int clickToPraiseTypeId,int clickToPraiseUserId,String token,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appAddClickToPraise)// 请求方式和请求url
                .params("clickToPraiseType", clickToPraiseType)
                .params("clickToPraiseTypeId", clickToPraiseTypeId)
                .params("clickToPraiseUserId", clickToPraiseUserId)
                .params("token", token)
                .tag("appAddClickToPraise")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 视频直播评论取消点赞
     */
    public static void appCancelClickToPraise(int clickToPraiseType,int clickToPraiseTypeId,int clickToPraiseUserId,String token,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appCancelClickToPraise)// 请求方式和请求url
                .params("clickToPraiseType", clickToPraiseType)
                .params("clickToPraiseTypeId", clickToPraiseTypeId)
                .params("clickToPraiseUserId", clickToPraiseUserId)
                .params("token", token)
                .tag("appCancelClickToPraise")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 视频直播评论列表
     */
    public static void appGetComments(int commentsType,int commentsTypeId,String token,int userId,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appGetComments)// 请求方式和请求url
                .params("commentsType", commentsType)
                .params("commentsTypeId", commentsTypeId)
                .params("token", token)
                .params("userId", userId)
                .tag("appGetComments")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 视频直播添加评论
     */
    public static void appAddComments(int commentsType,int commentsTypeId,int userId,int commentsBeCommentedUserId,
                                      String commentsContent,String token,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appAddComments)// 请求方式和请求url
                .params("commentsType", commentsType)
                .params("commentsTypeId", commentsTypeId)
                .params("userId", userId)
                .params("commentsBeCommentedUserId", commentsBeCommentedUserId)
                .params("commentsContent", commentsContent)
                .params("token", token)
                .tag("appAddComments")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 视频直播删除评论
     */
    public static void appDeleteComments(int commentsId,int userId,String token,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appDeleteComments)// 请求方式和请求url
                .params("commentsId", commentsId)
                .params("userId", userId)
                .params("token", token)
                .tag("appDeleteComments")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 获取是否点赞，点赞数，评论数
     */
    public static void appGetIcon(String token,int userId,int videoId,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appGetIcon)// 请求方式和请求url
                .params("token", token)
                .params("userId", userId)
                .params("videoId", videoId)
                .tag("appGetIcon")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 获取当前用户关注的人发布的视频
     */
    public static void appGetUserFocusedVideos(String token,int userId,int pn,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appGetUserFocusedVideos)// 请求方式和请求url
                .params("token", token)
                .params("userId", userId)
                .params("pn", pn)
                .tag("appGetUserFocusedVideos")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 增加转发数
     */
    public static void appAddNumberOfForwards(int videoId,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appAddNumberOfForwards)// 请求方式和请求url
                .params("videoId", videoId)
                .tag("appAddNumberOfForwards")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 判断用户是否被当前用户关注
     */
    public static void appJudgeWhetherToPayAttention(int userId,int beConcernUserId,String token,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appJudgeWhetherToPayAttention)// 请求方式和请求url
                .params("userId", userId)
                .params("beConcernUserId", beConcernUserId)
                .params("token", token)
                .tag("appJudgeWhetherToPayAttention")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 根据用户ID获取用户订单列表
     */
    public static void getOrdersByUserIdApiAll(String token,int pn,int userId,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.getOrdersByUserIdApi)// 请求方式和请求url
                .params("token", token)
                .params("pn", pn)
                .params("userId", userId)

                .tag("getOrdersByUserIdApi")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 根据用户ID获取用户订单列表
     */
    public static void getOrdersByUserIdApi(String token,int pn,int userId,String key,int val,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.getOrdersByUserIdApi)// 请求方式和请求url
                .params("token", token)
                .params("pn", pn)
                .params("userId", userId)
                .params(key, val)
                .tag("getOrdersByUserIdApi")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 根据根目录id获取下属的日记
     */
    public static void appGetCurrentGroupDiaries(String token,int diaryRootId,int userId,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant.appGetCurrentGroupDiaries)// 请求方式和请求url
                .params("token", token)
                .params("diaryRootId", diaryRootId)
                .params("userId", userId)
                .tag("appGetCurrentGroupDiaries")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 种植日记浏览量
     */
    public static void appAddNumberOfViews(String token,int diaryRootId,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appAddNumberOfViews)// 请求方式和请求url
                .params("token", token)
                .params("diaryRootId", diaryRootId)
                .tag("appAddNumberOfViews")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 日记新增评论层（当楼主）
     */
    public static void appAddedDiaryCommentFloor(String token,int diaryCommentLayerUserId,String diaryCommentLayerContent
            ,String diaryCommentLayerCreateTime,int diaryId,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appAddedDiaryCommentFloor)// 请求方式和请求url
                .params("token", token)
                .params("diaryCommentLayerUserId", diaryCommentLayerUserId)
                .params("diaryCommentLayerContent", diaryCommentLayerContent)
                .params("diaryCommentLayerCreateTime", diaryCommentLayerCreateTime)
                .params("diaryId", diaryId)
                .tag("appAddedDiaryCommentFloor")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 日记楼中评论
     */
    public static void appCommentDiaryCommentFloor(String token,int diaryId,int diaryLayerCommentUserId
            ,int diaryLayerCommentBeUserId,String diaryLayerCommentContent,int diaryCommentLayerId,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appCommentDiaryCommentFloor)// 请求方式和请求url
                .params("token", token)
                .params("diaryId", diaryId)
                .params("diaryLayerCommentUserId", diaryLayerCommentUserId)
                .params("diaryLayerCommentBeUserId", diaryLayerCommentBeUserId)
                .params("diaryLayerCommentContent", diaryLayerCommentContent)
                .params("diaryCommentLayerId", diaryCommentLayerId)
                .tag("appCommentDiaryCommentFloor")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 日记点赞/取消点赞
     */
    public static void appDiaryLikes(String token,int diaryClickToPraiseUserId,int diaryId,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appDiaryLikes)// 请求方式和请求url
                .params("token", token)
                .params("diaryClickToPraiseUserId", diaryClickToPraiseUserId)
                .params("diaryId", diaryId)

                .tag("appDiaryLikes")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     * 获取日记评论
     */
    public static void appViewAllReviews(String token,int diaryId,int userId,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appViewAllReviews)// 请求方式和请求url
                .params("token", token)
                .params("diaryId", diaryId)
                .params("userId", userId)

                .tag("appViewAllReviews")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     *获取我关注的人发布的日记
     */
    public static void appGetMyConcerned(String token,int userId,int pn,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appGetMyConcerned)// 请求方式和请求url
                .params("token", token)
                .params("userId", userId)
                .params("pn", pn)

                .tag("appGetMyConcerned")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     *修改根目录  修改植物名称
     */
    public static void appUpdateDiaryRoot(String token, int diaryRootId, String diaryRootTitle, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appUpdateDiaryRoot)// 请求方式和请求url
                .params("token", token)
                .params("diaryRootId", diaryRootId)
                .params("diaryRootTitle", diaryRootTitle)
                .tag("appUpdateDiaryRoot")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     *	删除日记(如果传分组id删一组，如果传日记id,删单个)
     */
    public static void appdeleteDiary1(String token, int diaryId, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appdeleteDiary)// 请求方式和请求url
                .params("token", token)
                .params("diaryId", diaryId)
                .tag("appdeleteDiary")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     *	删除日记2(如果传分组id删一组，如果传日记id,删单个)
     */
    public static void appdeleteDiary2(String token, int diaryRootId, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appdeleteDiary)// 请求方式和请求url
                .params("token", token)
                .params("diaryRootId", diaryRootId)

                .tag("appdeleteDiary")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     *	修改日记(如果传分组id删一组，如果传日记id,删单个)
     */
    public static void appUpdateDiary(String token, String dirayCreateTime, int diaryId, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.appUpdateDiary)// 请求方式和请求url
                .params("token", token)
                .params("dirayCreateTime", dirayCreateTime)
                .params("diaryId", diaryId)
                .tag("appUpdateDiary")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     *	申请直播
     *@param headline 商品名
     *@param content 商品详情
     *@param previewVideo 上传完视频返回的videoid
     *@param OPlayerTime 开播时间"yyyy-MM-dd HH:mm:ss"
     *@param contact 联系人
     *@param authenticationType 认证类型(1:个人 2:企业)
     *@param status 0为待审核 1为审核通过 2为审核不通过

     */
    public static void livestreamingAdd(String headline, String content, int previewVideo,String OPlayerTime,
                                        String contact,String contactNumber,int authenticationType,String images,
                                        String token,int status,StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.livestreamingAdd)// 请求方式和请求url
                .params("headline", headline)
                .params("content", content)
                .params("previewVideo", previewVideo)
                .params("OPlayerTime", OPlayerTime)
                .params("contact", contact)
                .params("contactNumber", contactNumber)
                .params("authenticationType", authenticationType)
                .params("images", images)
                .params("token", token)
                .params("status", status)
                .tag("livestreamingAdd")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     *	是否有直播间
     */
    public static void isHaslivingRoom(String token, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.isHaslivingRoom)// 请求方式和请求url
                .params("token", token)
                .tag("isHaslivingRoom")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
    /**
     *	删除直播间
     *@param id 直播的id videoid
     *
     */
    public static void deletelivingRoom(int  id, StringCallback stringCallback) {
        OkGo.<String>post(UrlConstant2.deletelivingRoom)// 请求方式和请求url
                .params("id", id)
                .tag("deletelivingRoom")                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(stringCallback);
    }
}
