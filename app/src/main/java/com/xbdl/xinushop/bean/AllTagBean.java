package com.xbdl.xinushop.bean;

/**
 * 所有标签
 */
public class AllTagBean {
    /**
     * tagId : 1
     * name : 1
     * time : 1533309425000
     * weight : 7
     * userId : 1
     */

    private int tagId;
    private String name;
    private long time;
    private int weight;
    private int userId;
    private boolean ischeck = false;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isIscheck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    @Override
    public String toString() {
        return "AllTagBean{" +
                "tagId=" + tagId +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", weight=" + weight +
                ", userId=" + userId +
                ", ischeck=" + ischeck +
                '}';
    }
}
