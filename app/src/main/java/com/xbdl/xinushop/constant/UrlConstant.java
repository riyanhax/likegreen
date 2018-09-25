package com.xbdl.xinushop.constant;

public interface UrlConstant {
    String baseUrl="http://192.168.1.4:8080/";
    /**
     * 喜帖分享生活
     */
    String shareyourlife="https://www.easy-mock.com/mock/5ba31506fb39f72ea10ec4d4/xilv/shareyourlife";
    /**
     * 推荐评论
     */
    String recommentlist="https://www.easy-mock.com/mock/5ba31506fb39f72ea10ec4d4/xilv/recommentlist";
    /**
     * 推荐评论
     */
    String notehotandattention="https://www.easy-mock.com/mock/5ba31506fb39f72ea10ec4d4/xilv/notehotandattention";
    /**
     * 登录
     */
    String login=baseUrl+"user/appLogin";
    /**
     * 发送验证码
     *
     */
    String sendCode=baseUrl+"user/appSend";
    /**
     * 注册
     */
    String regeist=baseUrl+"user/appRegist";
}
