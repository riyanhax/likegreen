package com.xbdl.xinushop.constant;

public interface UrlConstant {
    //String baseUrl="http://192.168.1.5:8080/";
// String baseUrl="http://120.79.173.209:8080/";
    String baseUrl = "http://192.168.0.121:8080/"; //谢

    /**
     * 喜帖分享生活
     */
    String shareyourlife = "https://www.easy-mock.com/mock/5ba31506fb39f72ea10ec4d4/xilv/shareyourlife";
    /**
     * 推荐评论
     */
    String recommentlist = "https://www.easy-mock.com/mock/5ba31506fb39f72ea10ec4d4/xilv/recommentlist";
    /**
     * 推荐评论
     */
    String notehotandattention = "https://www.easy-mock.com/mock/5ba31506fb39f72ea10ec4d4/xilv/notehotandattention";
    /**
     * 登录
     */
    String login = baseUrl + "user/appLogin";
    /**
     * 发送验证码
     */
    //22222
    String sendCode = baseUrl + "user/appSend";
    /**
     * 注册
     */
    String regeist = baseUrl + "user/appRegist";
    /**
     * 商品分类
     */
    String getCategorylist = "commodityclassify/getClassifyByInfo";
    /**
     * 添加种植日记
     */
    String appAddPlantDiary = baseUrl + "plantDiary/appAddPlantDiary";
    /**
     * 查询种植日记详情
     */
    String findplantDetail = baseUrl + "plantDiary/appFindPlanyDiary";

    //kkj000000000000000000
    /**
     * 获取所有tag
     */
    String findAllTag = baseUrl + "note/appFindAllTag";
    /**
     * 发布帖子
     */
    String setPost = baseUrl + "note/appAddNote";
    /**
     * 获取所有话题
     */
    String findAllSubject = baseUrl + "topic/appFindAppTopic";
    /**
     * 查询种植日记列表
     */
    String findPlantList = baseUrl + "plantDiary/appPlantDiaryList";
    /**
     * 查找优惠券
     */
    String findMydiscountCoupon = baseUrl + "usercoupons/getUserCouponsListApi";
}
