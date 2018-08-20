package com.pywl.likegreen.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.pywl.likegreen.R;
import cn.jpush.im.android.api.content.EventNotificationContent;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.content.TextContent;

import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by theWind on 2018/8/17.
 * 首页直播fragment，聊天室adapter，只发送文字
 */

public class LivingRoomAudienceSayAdapter extends ListBaseAdapter<Message> {
    private LayoutInflater mLayoutInflater;

    public LivingRoomAudienceSayAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LivingRoomAudienceSayAdapter.ViewHolder(mLayoutInflater.inflate(R.layout.item_living_room, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDataList!=null){
            Message msg = mDataList.get(position);
            LivingRoomAudienceSayAdapter.ViewHolder viewHolder = (LivingRoomAudienceSayAdapter.ViewHolder) holder;
            viewHolder.userName.setText(msg.getFromID()+"：");

            String text = ((TextContent) msg.getContent()).getText();
            viewHolder.directMsg.setText(text);

            //获取用户信息
            JMessageClient.getUserInfo(msg.getFromID(), new GetUserInfoCallback() {
                @Override
                public void gotResult(int i, String s, UserInfo userInfo) {
                    userInfo.getAvatarBitmap(new GetAvatarBitmapCallback() {
                        @Override
                        public void gotResult(int i, String s, Bitmap bitmap) {
                            Log.v("nihaoma",i+"mm"+s+"mm");
                        }
                    });
                }
            });
        }

    }


    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView directMsg;
        private TextView userName;
        private CircleImageView headIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            directMsg = (TextView) itemView.findViewById(R.id.tv_living_word);
            userName = (TextView) itemView.findViewById(R.id.tv_living_username);
            headIcon = (CircleImageView) itemView.findViewById(R.id.civ_living_head);

        }
    }
}
