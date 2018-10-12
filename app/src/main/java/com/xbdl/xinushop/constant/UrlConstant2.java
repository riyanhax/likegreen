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

}
