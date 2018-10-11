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
    String adduserAddress=UrlConstant.baseUrl+"useraddress/addUserAddress";
    /**
     * 查询收货地址
     */
    String getAddressList=UrlConstant.baseUrl+"useraddress/getAddressByUserId";
    /**
     * 设置默认收货地址
     */
    String setAddressDefault=UrlConstant.baseUrl+"useraddress/settingDefault";
    /**
     * 删除收货地址
     */
    String delAddress=UrlConstant.baseUrl+"useraddress/delUserAddress";
    /**
     * 修改收货地址
     */
    String updateAddress=UrlConstant.baseUrl+"useraddress/updUserAddress";
    /**
     * 帐户余额充值
     */
    String balanceRecharge=UrlConstant.baseUrl+"user/balanceRecharge";
    /**
     * 帐户余额提现
     */
    String tixian=UrlConstant.baseUrl+"user/balanceWithdrawDeposit";
    /**
     * 我发布的商品
     */
    String getCommodityByUserId=UrlConstant.baseUrl+"commodity/getCommodityByUserId";
    /**
     * 交易明细
     */
    String getWalletDetailByInfo=UrlConstant.baseUrl+"walletdetail/getWalletDetailByInfo";
    /**
     * 查询用户信息
     */
    String getUserInfoById=UrlConstant.baseUrl+"user/getUserInfoByIdApi";
    /**
     * 查询用户视频
     */
    String myvideolist=UrlConstant.baseUrl+"xilv/myvideolist";
    /**
     * 查询天气
     */
    String getweather="https://way.jd.com/he/freeweather";

}
