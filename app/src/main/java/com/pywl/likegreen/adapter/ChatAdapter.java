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
            case TYPE_RECEIVE_TXT:
                return new TextViewHolder(createViewByType(mDataList.get(viewType),viewType,parent));
            case TYPE_SEND_IMAGE:
            case TYPE_RECEIVER_IMAGE:
                return new ImgViewHolder(createViewByType(mDataList.get(viewType),viewType,parent));
            case TYPE_SEND_VIDEO:
            case TYPE_RECEIVE_VIDEO:
                return new VideoViewHolder(createViewByType(mDataList.get(viewType),viewType,parent));
            case TYPE_SEND_VOICE:
            case TYPE_RECEIVER_VOICE:
                return new VoiceViewHolder(createViewByType(mDataList.get(viewType),viewType,parent));
                default:
                    return null;
            }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDataList!=null){
            Message msg = mDataList.get(position);
            UserInfo userInfo = (UserInfo) msg.getTargetInfo();
            Log.v("nihaoma",mDataList.get(position).toString());
            if (holder instanceof TextViewHolder){
                final TextViewHolder textViewHolder= (TextViewHolder) holder;
                textViewHolder.mSendWord.setText(((TextContent) msg.getContent()).getText());
                //设置头像
                userInfo.getAvatarBitmap(new GetAvatarBitmapCallback() {
                    @Override
                    public void gotResult(int i, String s, Bitmap bitmap) {
                        if (bitmap!=null){
                            textViewHolder.civSendHead.setImageBitmap(bitmap);
                        }

                    }
                });
            }
            if (holder instanceof ImgViewHolder){
                final ImgViewHolder imgViewHolder= (ImgViewHolder) holder;
                ImageContent imageContent = (ImageContent) msg.getContent();
                final String path = imageContent.getLocalThumbnailPath();
                imageContent.downloadThumbnailImage(msg, new DownloadCompletionCallback() {
                    @Override
                    public void onComplete(int i, String s, File file) {
                        Log.v("nihaoma",file.toString());
                        Glide.with(mContext).load(file).into(imgViewHolder.mSendImg);
                    }
                });
                //Glide.with(mContext).load(path).into(imgViewHolder.mSendImg);

            }
            if (holder instanceof VoiceViewHolder){
                VoiceViewHolder voiceViewHolder= (VoiceViewHolder) holder;

            }
        }
    }
    private View createViewByType(Message msg, int position,ViewGroup parent) {
        // 会话类型
        switch (msg.getContentType()) {
            case text:
                return getItemViewType(position) == TYPE_SEND_TXT ?
                        mLayoutInflater.inflate(R.layout.chat_send_text,parent,false):
                        mLayoutInflater.inflate(R.layout.chat_receive_text,parent,false);
            case image:
                return getItemViewType(position) == TYPE_SEND_IMAGE ?
                        mLayoutInflater.inflate(R.layout.chat_send_img,parent,false):
                        mLayoutInflater.inflate(R.layout.chat_receive_img,parent,false);
            case voice:
                return getItemViewType(position) == TYPE_SEND_VOICE ?
                        mLayoutInflater.inflate(R.layout.chat_send_voice,parent,false):
                        mLayoutInflater.inflate(R.layout.chat_receive_voice,parent,false);
            case video:
                return getItemViewType(position) == TYPE_SEND_VIDEO ?
                        mLayoutInflater.inflate(R.layout.chat_send_video,parent,false):
                        mLayoutInflater.inflate(R.layout.chat_receive_video,parent,false);
                default:
                    return null;
        }
    }
    @Override
    public int getItemViewType(int position) {
        Message msg = mDataList.get(position);
        switch (msg.getContentType()){
            case text:
                return msg.getDirect() == MessageDirect.send ? TYPE_SEND_TXT
                        : TYPE_RECEIVE_TXT;
            case image:
                return msg.getDirect() == MessageDirect.send ? TYPE_SEND_IMAGE
                        : TYPE_RECEIVER_IMAGE;
            case video:
                return msg.getDirect() == MessageDirect.send ? TYPE_SEND_VIDEO
                        : TYPE_RECEIVE_VIDEO;
            case voice:
                return msg.getDirect() == MessageDirect.send ? TYPE_SEND_VOICE
                        : TYPE_RECEIVER_VOICE;
        }
        return super.getItemViewType(position);
    }



    private class TextViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView civSendHead;
        private TextView mSendWord;
        private TextView mSendTime;
        public TextViewHolder(View itemView) {
            super(itemView);
            civSendHead= (CircleImageView)itemView.findViewById(R.id.iv_chat_send_head);
            mSendWord=(TextView)itemView.findViewById(R.id.ib_chat_send_word);
            mSendTime=(TextView)itemView.findViewById(R.id.tv_chat_send_time);
        }
    }
    private class VoiceViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView civSendHead;
        private TextView mSendVoice;
        private TextView mSendTime;
        public VoiceViewHolder(View itemView) {
            super(itemView);
            civSendHead= (CircleImageView)itemView.findViewById(R.id.iv_chat_send_head);
            mSendVoice=(TextView)itemView.findViewById(R.id.ib_chat_send_voice);
            mSendTime=(TextView)itemView.findViewById(R.id.tv_chat_send_time);
        }
    }
    private class ImgViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView civSendHead;
        private ImageView mSendImg;
        private TextView mSendTime;
        public ImgViewHolder(View itemView) {
            super(itemView);
            civSendHead= (CircleImageView)itemView.findViewById(R.id.iv_chat_send_head);
            mSendImg=(ImageView)itemView.findViewById(R.id.ib_chat_send_img);
            mSendTime=(TextView)itemView.findViewById(R.id.tv_chat_send_time);
        }
    }
    private class VideoViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView civSendHead;
        private ImageView mSendVideo;
        private TextView mSendTime;
        public VideoViewHolder(View itemView) {
            super(itemView);
            civSendHead= (CircleImageView)itemView.findViewById(R.id.iv_chat_send_head);
            mSendVideo=(ImageView)itemView.findViewById(R.id.iv_chat_send_video);
            mSendTime=(TextView)itemView.findViewById(R.id.tv_chat_send_time);
        }
    }

}
