package com.xbdl.xinushop.bean;

import java.util.List;

import cn.jpush.im.android.api.model.Message;

/**
 * Created by theWind on 2018/8/20.
 */

public class LivingRoomMsgBean {
   private  List<Message> data;
   private String myWord;

    public List<Message> getData() {
        return data;
    }

    public void setData(List<Message> data) {
        this.data = data;
    }

    public String getMyWord() {
        return myWord;
    }

    public void setMyWord(String myWord) {
        this.myWord = myWord;
    }
}
