package com.xbdl.xinushop.bean;

public class TheNewVideoBean {

    /**

     * update_time : 1539884068000
     * music : null
     * commodity_id : null  商品id
     * create_time : 1539884068000
     * user_id : 25
     * clickNum : null
     * recommend : null
     * type : 1   1普通视频  2广告视频
     * headline : 添加
     * url : http://jdvodrdjw0c4b.vod.126.net/jdvodrdjw0c4b/e5d91ceb-853b-453a-b12b-26c901acdd18.mp4
     * video_id : 8
     * status : 0
     */

    private long update_time;
    private Object music;
    private Object commodity_id;
    private long create_time;
    private int user_id;
    private Object clickNum;
    private Object recommend;
    private int type;
    private String headline;
    private String url;
    private int video_id;
    private int status;

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    public Object getMusic() {
        return music;
    }

    public void setMusic(Object music) {
        this.music = music;
    }

    public Object getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(Object commodity_id) {
        this.commodity_id = commodity_id;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Object getClickNum() {
        return clickNum;
    }

    public void setClickNum(Object clickNum) {
        this.clickNum = clickNum;
    }

    public Object getRecommend() {
        return recommend;
    }

    public void setRecommend(Object recommend) {
        this.recommend = recommend;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVideo_id() {
        return video_id;
    }

    public void setVideo_id(int video_id) {
        this.video_id = video_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TheNewVideoBean{" +
                "update_time=" + update_time +
                ", music=" + music +
                ", commodity_id=" + commodity_id +
                ", create_time=" + create_time +
                ", user_id=" + user_id +
                ", clickNum=" + clickNum +
                ", recommend=" + recommend +
                ", type=" + type +
                ", headline='" + headline + '\'' +
                ", url='" + url + '\'' +
                ", video_id=" + video_id +
                ", status=" + status +
                '}';
    }
}
