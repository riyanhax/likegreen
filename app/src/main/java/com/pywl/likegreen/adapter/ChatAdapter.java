package com.pywl.likegreen.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import cn.jpush.im.android.api.callback.DownloadCompletionCallback;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.MessageDirect;
import cn.jpush.im.android.api.model.Message;

import com.bumptech.glide.Glide;
import com.pywl.likegreen.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.UserInfo;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by theWind on 2018/8/10.
 * 私信adapter
 */

public class ChatAdapter extends ListBaseAdapter<Message> {
    private static final int TYPE_SEND_TXT = 0;
    private static final int TYPE_RECEIVE_TXT = 1;
    private static final int TYPE_SEND_IMAGE = 2;
    private static final int TYPE_RECEIVER_IMAGE = 3;
    private static final int TYPE_SEND_VIDEO = 4;
    private static final int TYPE_RECEIVE_VIDEO = 5;
    private static final int TYPE_SEND_VOICE = 6;
    private static final int TYPE_RECEIVER_VOICE = 7;

    private LayoutInflater mLayoutInflater;
    private Conversation conv;
    public ChatAdapter(Context context,Conversation conversation) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        this.conv=conversation;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_SEND_TXT:
            case TYPE_SEND_IMAGE:
            case TYPE_SEND_VOICE:
            case TYPE_RECEIVE_TXT:



            case TYPE_RECEIVER_IMAGE:
            case TYPE_RECEIVE_VIDEO:
            case TYPE_RECEIVER_VOICE:

                default:
                    return null;
            }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDataList!=null){

        }
    }

    @Override
    public int getItemViewType(int position) {
        Message msg = mDataList.get(position);

        return super.getItemViewType(position);
    }



}
