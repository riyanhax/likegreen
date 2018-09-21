package com.xbdl.xinushop.bean;

/**
 * 首页推荐 列表
 */
public class ReCommentListBean  {
    /**
     * userid : 1231222
     * usericon : http://img3.redocn.com/tupian/20150730/meinvxingganzuozi_4749930.jpg
     * username : 马云
     * createtime : 2018-12-01
     * commentmessage : 阿斯蒂芬
     * likenumber : 11
     * islike : 0
     */

    private String userid;
    private String usericon;
    private String username;
    private String createtime;
    private String commentmessage;
    private int likenumber;
    private int islike;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsericon() {
        return usericon;
    }

    public void setUsericon(String usericon) {
        this.usericon = usericon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCommentmessage() {
        return commentmessage;
    }

    public void setCommentmessage(String commentmessage) {
        this.commentmessage = commentmessage;
    }

    public int getLikenumber() {
        return likenumber;
    }

    public void setLikenumber(int likenumber) {
        this.likenumber = likenumber;
    }

    public int getIslike() {
        return islike;
    }

    public void setIslike(int islike) {
        this.islike = islike;
    }
}
