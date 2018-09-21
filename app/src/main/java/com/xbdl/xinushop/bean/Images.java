package com.xbdl.xinushop.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Images implements Parcelable {

    private String image;
    private String imagecreatetime = "";
    private String imagetitle = "";

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getImagecreatetime() {
        return imagecreatetime;
    }

    public void setImagecreatetime(String imagecreatetime) {
        this.imagecreatetime = imagecreatetime;
    }

    public String getImagetitle() {
        return imagetitle;
    }

    public void setImagetitle(String imagetitle) {
        this.imagetitle = imagetitle;
    }

    @Override
    public String toString() {
        return "Images{" +
                "image='" + image + '\'' +
                ", imagecreatetime='" + imagecreatetime + '\'' +
                ", imagetitle='" + imagetitle + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.image);
        dest.writeString(this.imagecreatetime);
        dest.writeString(this.imagetitle);
    }

    public Images() {
    }

    protected Images(Parcel in) {
        this.image = in.readString();
        this.imagecreatetime = in.readString();
        this.imagetitle = in.readString();
    }

    public static final Parcelable.Creator<Images> CREATOR = new Parcelable.Creator<Images>() {
        @Override
        public Images createFromParcel(Parcel source) {
            return new Images(source);
        }

        @Override
        public Images[] newArray(int size) {
            return new Images[size];
        }
    };
}
