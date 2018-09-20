package com.pywl.likegreen.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Images implements Serializable  {
    private static final long serialVersionUID = -706021054460046441L;
    private String image;
    public void setImage(String image) {
        this.image = image;
    }
    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Images{" +
                "image='" + image + '\'' +
                '}';
    }
}
