package com.xbdl.xinushop.constant;

public interface UrlConstant2 {


    /**
     * 忘记密码
     */
    String forget=UrlConstant.baseUrl+"user/appForget";
    /**
     * 自动登录
     */
    String autoLogin=UrlConstant.baseUrl+"user/appAutoLogin";
    /**
     * 修改用户信息
     */
    String updataUser=UrlConstant.baseUrl+"user/appUpdataUser";
    /**
     * 添加收货地址
     */
    String adduserAddress=UrlConstant.baseUrl+"useraddress/addUserAddressApi";
    /**
     * 查询收货地址
     */
    String getAddressList=UrlConstant.baseUrl+"useraddress/getAddressByUserIdApi";
    /**
     * 设置默认收货地址
     */
    String setAddressDefault=UrlConstant.baseUrl+"useraddress/settingDefaultApi";
    /**
     * 删除收货地址
     */
    String delAddress=UrlConstant.baseUrl+"useraddress/delUserAddressApi";
    /**
     * 修改收货地址
     */
    String updateAddress=UrlConstant.baseUrl+"useraddress/updUserAddressApi";
    /**
     * 帐户余额充值
     */
    String balanceRecharge=UrlConstant.baseUrl+"user/balanceRechargeApi";
    /**
     * 帐户余额提现
     */
    String tixian=UrlConstant.baseUrl+"user/balanceWithdrawDepositApi";
    /**
     * 我发布的商品
     */
    String getCommodityByUserId=UrlConstant.baseUrl+"commodity/getCommodityByUserIdApi";
    /**
     * 交易明细
     */
    String getWalletDetailByInfo=UrlConstant.baseUrl+"walletdetail/getWalletDetailByInfoApi";
    /**
     * 查询用户信息
     */
    String getUserInfoById=UrlConstant.baseUrl+"user/getUserInfoByIdApi";
    /**
     * 查询用户视频
     */
    String myvideolist=UrlConstant.baseUrl+"myvideolist";
    /**
     * 查询天气
     */
    String getweather="https://way.jd.com/he/freeweather";
    /**
     * 查询网易的accid
     */
    String getaccid=UrlConstant.baseUrl+"getaccid";
    /**
     * 查询用户优惠卷
     */
    String getUserCouponsListApi=UrlConstant.baseUrl+"usercoupons/getUserCouponsListApi";
    /**
     * 领取优惠券
     */
    String collectionOfCouponsApi=UrlConstant.baseUrl+"usercoupons/collectionOfCouponsApi";
    /**
     * 申请网易子账号
     */
    String getNetUser="https://vcloud.163.com/app/vod/thirdpart/user/create";
    /**
     * 设置网易子账号
     */
    String setuseraccid=UrlConstant.baseUrl+"setuseraccid1";
    /**
     * 获取我出售商品的交易详情
     */
    String getSellerOrdersByUserIdApi=UrlConstant.baseUrl+"sellerorders/getSellerOrdersByUserIdApi";
    /**
     * 上传账号视频到后台
     */
    String appPostVideo=UrlConstant.baseUrl+"appPostVideo";
    /**
     * 获取最新视频
     */
    String selectNewest=UrlConstant.baseUrl+"selectNewest";
    /**
     * 获取推荐视频
     */
    String suggestedVideos=UrlConstant.baseUrl+"suggestedVideos";

    /**
     * 谁关注了当前用户列表
     */
    String appGetWhoWatchingMe=UrlConstant.baseUrl+"concern/appGetWhoWatchingMe";
    /**
     * 查询我的粉丝，我的关注列表
     */
    String appGetMyAttention=UrlConstant.baseUrl+"concern/appGetMyAttention";
    /**
     * 关注谁谁谁
     */
    String appAddConcern=UrlConstant.baseUrl+"concern/appAddConcern";
    /**
     * 取消关注
     */
    String appCancelYourAttention=UrlConstant.baseUrl+"concern/appCancelYourAttention";
    /**
     * 视频直播评论判断是否已点赞
     */
    String appCheckClickToPraise=UrlConstant.baseUrl+"clickToPraise/appCheckClickToPraise";
    /**
     * 视频直播评论点赞
     */
    String appAddClickToPraise=UrlConstant.baseUrl+"clickToPraise/appAddClickToPraise";
    /**
     * 视频直播评论取消点赞
     */
    String appCancelClickToPraise=UrlConstant.baseUrl+"clickToPraise/appCancelClickToPraise";
    /**
     * 视频直播评论列表
     */
    String appGetComments=UrlConstant.baseUrl+"comments/appGetComments";
    /**
     * 视频直播添加评论
     */
    String appAddComments=UrlConstant.baseUrl+"comments/appAddComments";
    /**
     * 视频直播删除评论
     */
    String appDeleteComments=UrlConstant.baseUrl+"comments/appDeleteComments";
    /**
     * 获取是否点赞，点赞数，评论数
     */
    String appGetIcon=UrlConstant.baseUrl+"concern/appGetIcon";
    /**
     * 获取当前用户关注的人发布的视频
     */
    String appGetUserFocusedVideos=UrlConstant.baseUrl+"concern/appGetUserFocusedVideos";
    /**
     * 增加转发数
     */
    String appAddNumberOfForwards=UrlConstant.baseUrl+"concern/addNumberOfForwards";
    /**
     * 判断用户是否被当前用户关注
     */
    String appJudgeWhetherToPayAttention=UrlConstant.baseUrl+"concern/appJudgeWhetherToPayAttention";
    /**
     * 根据用户ID获取用户订单列表
     */
    String getOrdersByUserIdApi=UrlConstant.baseUrl+"orders/getOrdersByUserIdApi";
    /**
     * 日记浏览量
     */
    String appAddNumberOfViews=UrlConstant.baseUrl+"diary/appAddNumberOfViews";

    /**
     * 日记新增评论层（当楼主）
     */
    String appAddedDiaryCommentFloor=UrlConstant.baseUrl+"diary/appAddedDiaryCommentFloor";
    /**
     * 日记楼中评论
     */
    String appCommentDiaryCommentFloor=UrlConstant.baseUrl+"diary/appCommentDiaryCommentFloor";
    /**
     * 日记点赞/取消点赞
     */
    String appDiaryLikes=UrlConstant.baseUrl+"diary/appDiaryLikes";
    /**
     * 获取日记评论
     */
    String appViewAllReviews=UrlConstant.baseUrl+"diary/appViewAllReviews";
    /**
     * 获取我关注的人的日记
     */
    String appGetMyConcerned=UrlConstant.baseUrl+"diary/appGetMyConcerned";
    /**
     * 修改根目录  修改植物名称
     */
    String appUpdateDiaryRoot=UrlConstant.baseUrl+"diary/appUpdateDiaryRoot";
    /**
     * 	删除日记(如果传分组id删一组，如果传日记id,删单个)
     */
    String appdeleteDiary=UrlConstant.baseUrl+"diary/appdeleteDiary";
    /**
     * 		修改日记
     */
    String appUpdateDiary=UrlConstant.baseUrl+"diary/appUpdateDiary";
    /**
     * 		申请直播
     */
    String livestreamingAdd=UrlConstant.baseUrl+"livestreaming/add";
    /**
     * 	判断该用户是否有直播间
     */
    String isHaslivingRoom=UrlConstant.baseUrl+"livestreaming/findbyuserid";
    /**
     * 	删除直播间
     */
    String deletelivingRoom=UrlConstant.baseUrl+"livestreaming/delete";
    /**
     * 	查询所有直播间
     */
    String livestreamingList=UrlConstant.baseUrl+"livestreaming/list";

}
