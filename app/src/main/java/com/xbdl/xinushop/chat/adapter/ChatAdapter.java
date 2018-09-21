package com.xbdl.xinushop.chat.adapter;

import cn.jiguang.imui.commons.ImageLoader;
import cn.jiguang.imui.messages.MsgListAdapter;

public class ChatAdapter extends MsgListAdapter {
    public ChatAdapter(String senderId, ImageLoader imageLoader) {
        super(senderId, imageLoader);
    }
}
