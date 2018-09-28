package com.xbdl.xinushop.bean;

/**
 * 话题 bean
 */
public class TopsBean {
    /**
     * img_path : http://192.168.0.121:8080/uploadFiles/e138be40f3754db2a7490c418c22a0c8.jpg
     * user_id : 1
     * t_name : 喜绿App还有多久完成？
     * t_desc : 这个不知道！
     * count : 0
     * weight : 0
     * topic_id : 1
     * time : 1538131753000
     */

    private String img_path;
    private int user_id;
    private String t_name;
    private String t_desc;
    private int count;
    private int weight;
    private int topic_id;
    private long time;

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getT_desc() {
        return t_desc;
    }

    public void setT_desc(String t_desc) {
        this.t_desc = t_desc;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TopsBean{" +
                "img_path='" + img_path + '\'' +
                ", user_id=" + user_id +
                ", t_name='" + t_name + '\'' +
                ", t_desc='" + t_desc + '\'' +
                ", count=" + count +
                ", weight=" + weight +
                ", topic_id=" + topic_id +
                ", time=" + time +
                '}';
    }
}
