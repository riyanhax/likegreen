package com.pywl.likegreen.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分享生活bean
 */
public class ShareLifeListBean implements Serializable {
    private static final long serialVersionUID = -7060214460046441L;



        private String usericon;
        private String username;
        private String createtime;
        private String topic;
        private String title;
        private String message;
        private int commentnumber;
        private int sharenumber;
        private int likenumber;
        private int viewcount;
        private int islike;
        private List<Images> images;
        public void setUsericon(String usericon) {
            this.usericon = usericon;
        }
        public String getUsericon() {
            return usericon;
        }

        public void setUsername(String username) {
            this.username = username;
        }
        public String getUsername() {
            return username;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }
        public String getCreatetime() {
            return createtime;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }
        public String getTopic() {
            return topic;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }

        public void setMessage(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }

        public void setCommentnumber(int commentnumber) {
            this.commentnumber = commentnumber;
        }
        public int getCommentnumber() {
            return commentnumber;
        }

        public void setSharenumber(int sharenumber) {
            this.sharenumber = sharenumber;
        }
        public int getSharenumber() {
            return sharenumber;
        }

        public void setLikenumber(int likenumber) {
            this.likenumber = likenumber;
        }
        public int getLikenumber() {
            return likenumber;
        }

        public void setViewcount(int viewcount) {
            this.viewcount = viewcount;
        }
        public int getViewcount() {
            return viewcount;
        }

        public void setIslike(int islike) {
            this.islike = islike;
        }
        public int getIslike() {
            return islike;
        }

        public void setImages( List<Images> images) {
            this.images = images;
        }
        public  List<Images> getImages() {
            return images;
        }

    @Override
    public String toString() {
        return "ShareLifeListBean{" +
                "usericon='" + usericon + '\'' +
                ", username='" + username + '\'' +
                ", createtime='" + createtime + '\'' +
                ", topic='" + topic + '\'' +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", commentnumber=" + commentnumber +
                ", sharenumber=" + sharenumber +
                ", likenumber=" + likenumber +
                ", viewcount=" + viewcount +
                ", islike=" + islike +
                ", images=" + images +
                '}';
    }
}