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

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.DownloadCompletionCallback;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.MessageContent;
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
    private static final int TYPE_SEND = 0;
    private static final int TYPE_RECEIVE = 1;


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
            case TYPE_SEND:
               return new SendHolder(mLayoutInflater.inflate(R.layout.item_chat_send, parent, false));
            case TYPE_RECEIVE:
                return new ReceiveHolder(mLayoutInflater.inflate(R.layout.item_chat_receive, parent, false));
                default:
                    return null;
            }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDataList!=null){
            Message msg = mDataList.get(position);
            Log.v("nihaoma",msg.toString());
            if (holder instanceof SendHolder){
                SendHolder sendHolder= (SendHolder) holder;
                choosesnedMsgType(sendHolder, msg);

            }else {
                ReceiveHolder receiveHolder= (ReceiveHolder) holder;
                chooseReceiveMsgType(receiveHolder, msg);
            }
        }
    }

    private void choosesnedMsgType(final SendHolder sendHolder, Message msg) {
        JMessageClient.getUserInfo(msg.getTargetID(), new GetUserInfoCallback() {
            @Override
            public void gotResult(int i, String s, UserInfo userInfo) {
                userInfo.getAvatarBitmap(new GetAvatarBitmapCallback() {
                    @Override
                    public void gotResult(int i, String s, Bitmap bitmap) {
                        if (bitmap!=null){
                            sendHolder.sendhead.setImageBitmap(bitmap);
                        }
                    }
                });
            }
        });

        switch (msg.getContentType()){
            case text:
                sendHolder.rlsendText.setVisibility(View.VISIBLE);
                sendHolder.sendimg.setVisibility(View.GONE);
                TextContent direst= (TextContent) msg.getContent();
                sendHolder.sendword.setText(direst.getText());
                break;
            case voice:
                sendHolder.rlsendText.setVisibility(View.VISIBLE);
                sendHolder.sendimg.setVisibility(View.GONE);
                break;
            case image:
                sendHolder.rlsendText.setVisibility(View.GONE);
                sendHolder.sendimg.setVisibility(View.VISIBLE);
                ImageContent imageContent = (ImageContent) msg.getContent();
                //先从本地拿缩咯图
                String path = imageContent.getLocalThumbnailPath();
                if (path == null){
                        imageContent.downloadThumbnailImage(msg, new DownloadCompletionCallback() {
                            @Override
                            public void onComplete(int i, String s, File file) {
                                Glide.with(mContext).load(file).into(sendHolder.sendimg);
                            }
                        });
                }
                break;
            case video:
                sendHolder.rlsendText.setVisibility(View.GONE);
                sendHolder.sendimg.setVisibility(View.VISIBLE);
                break;

        }
    }
    private void chooseReceiveMsgType(final ReceiveHolder sendHolder, Message msg) {
        JMessageClient.getUserInfo(msg.getTargetID(), new GetUserInfoCallback() {
            @Override
            public void gotResult(int i, String s, UserInfo userInfo) {
                userInfo.getAvatarBitmap(new GetAvatarBitmapCallback() {
                    @Override
                    public void gotResult(int i, String s, Bitmap bitmap) {
                        if (bitmap!=null){
                            sendHolder.sendhead.setImageBitmap(bitmap);
                        }
                    }
                });
            }
        });

        switch (msg.getContentType()){
            case text:
                sendHolder.rlsendText.setVisibility(View.VISIBLE);

                TextContent direst= (TextContent) msg.getContent();
                sendHolder.sendword.setText(direst.getText());
                sendHolder.sendimg.setVisibility(View.GONE);
                break;
            case voice:
                sendHolder.rlsendText.setVisibility(View.VISIBLE);
                sendHolder.sendimg.setVisibility(View.GONE);
                break;
            case image:
                sendHolder.rlsendText.setVisibility(View.GONE);
                sendHolder.sendimg.setVisibility(View.VISIBLE);
                ImageContent imageContent = (ImageContent) msg.getContent();
                //先从本地拿缩咯图
                String path = imageContent.getLocalThumbnailPath();
                if (path == null){
                        imageContent.downloadThumbnailImage(msg, new DownloadCompletionCallback() {
                            @Override
                            public void onComplete(int i, String s, File file) {
                                Glide.with(mContext).load(file).into(sendHolder.sendimg);
                            }
                        });
                }
                break;
            case video:
                sendHolder.rlsendText.setVisibility(View.GONE);
                sendHolder.sendimg.setVisibility(View.VISIBLE);
                break;

        }
    }


    @Override
    public int getItemViewType(int position) {
        Message msg = mDataList.get(position);
        if (msg.getDirect()==MessageDirect.send){
            return TYPE_SEND;
        }else {
            return TYPE_RECEIVE;
        }
    }

    private class SendHolder extends RecyclerView.ViewHolder{
        private TextView sendTime;
        private TextView sendword;
        private View rlsendText;
        private ImageView sendimg;
        private CircleImageView sendhead;
        public SendHolder(View itemView) {
            super(itemView);
             sendTime = (TextView)itemView.findViewById(R.id.tv_chat_send_time);
             rlsendText = itemView.findViewById(R.id.rl_chat_send_text);
             sendword = (TextView)itemView.findViewById(R.id.ib_chat_send_word);
             sendimg = (ImageView)itemView.findViewById(R.id.iv_chat_send_img);
             sendhead = (CircleImageView)itemView.findViewById(R.id.iv_chat_send_head);
        }
    }
    private class ReceiveHolder extends RecyclerView.ViewHolder{
        private TextView sendTime;
        private TextView sendword;
        private View rlsendText;
        private ImageView sendimg;
        private CircleImageView sendhead;
        public ReceiveHolder(View itemView) {
            super(itemView);
            sendTime = (TextView)itemView.findViewById(R.id.tv_chat_send_time);
            rlsendText = itemView.findViewById(R.id.rl_chat_send_text);
            sendword = (TextView)itemView.findViewById(R.id.ib_chat_send_word);
            sendimg = (ImageView)itemView.findViewById(R.id.iv_chat_send_img);
            sendhead = (CircleImageView)itemView.findViewById(R.id.iv_chat_send_head);
        }
    }

}
