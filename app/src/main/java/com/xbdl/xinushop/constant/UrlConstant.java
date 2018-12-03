package com.xbdl.xinushop.constant;

public interface UrlConstant {
    //String baseUrl="http://192.168.1.5:8080/";
    String baseUrl="http://120.79.173.209:8080/xilvAPP/";
    String baseimgUrl = "http://120.79.173.209:8080";
    //String baseUrl = "http://192.168.0.120:8080/"; //谢
    //String baseUrl = "http://22418y6p77.iask.in/xilv/"; //李
    //String baseimgUrl = "http://22418y6p77.iask.in";
   // String baseUrl = "http://224fj14709.51mypc.cn:18278/xilv/"; //宵
    //String baseimgUrl = "http://224fj14709.51mypc.cn:18278"; //宵

    /**
     * 喜帖分享生活
     */
    String shareyourlife = "https://www.easy-mock.com/mock/5ba31506fb39f72ea10ec4d4/xilv/shareyourlife";
    /**
     * 推荐评论
     */
    String recommentlist = baseUrl + "comment/appCommentList";
    /**
     * 发布评论
     */
    String readdcomment = baseUrl + "comment/appAddComment";
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

    String appAddPlantDiary = baseUrl + "diary/appAddDiary";

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
     * 种植日记详情
     */
    String findplantDetail = baseUrl + "plantDiary/appFindPlanyDiary";
    /**
     * 日记热门
     */
    String noteHot = baseUrl + "diary/appGetPopularDiaries";
    /**
     * 日记详情
     */
    String appGetCurrentGroupDiaries = baseUrl + "diary/appGetCurrentGroupDiaries";
}
