package com.xbdl.xinushop.bean;

import java.io.Serializable;

public class PersonBean implements Serializable{

    /**
     * userId : 9
     * userName : xl_65365287
     * userPhone : 15626202636
     * password : 12345678
     * xlId : 65365287
     * headPortrait : null
     * sex : null
     * signature : 个性签名
     * registerTime : 2018-09-21 18:03:36
     * backgroundImg : null
     * wechat : null
     * loginToken : 477170c87af34c97a2f493028bb875f4
     * accountBalance : 0
     * clientId : null
     * realName : null
     *
     */

    private int userId;
    private String userName;
    private String userPhone;
    private String password;
    private String xlId;
    private String headPortrait;
    private String sex;
    private String signature;
    private String registerTime;
    private Object backgroundImg;
    private String wechat;
    private String loginToken;
    private int accountBalance;
    private Object clientId;
    private String realName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getXlId() {
        return xlId;
    }

    public void setXlId(String xlId) {
        this.xlId = xlId;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public Object getBackgroundImg() {
        return backgroundImg;
    }

    public void setBackgroundImg(Object backgroundImg) {
        this.backgroundImg = backgroundImg;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Object getClientId() {
        return clientId;
    }

    public void setClientId(Object clientId) {
        this.clientId = clientId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "PersonBean{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", password='" + password + '\'' +
                ", xlId='" + xlId + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
                ", sex='" + sex + '\'' +
                ", signature='" + signature + '\'' +
                ", registerTime='" + registerTime + '\'' +
                ", backgroundImg=" + backgroundImg +
                ", wechat='" + wechat + '\'' +
                ", loginToken='" + loginToken + '\'' +
                ", accountBalance=" + accountBalance +
                ", clientId=" + clientId +
                ", realName='" + realName + '\'' +
                '}';
    }
}
