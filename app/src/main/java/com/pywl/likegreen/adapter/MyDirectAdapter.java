package com.pywl.likegreen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pywl.likegreen.R;

import cn.jpush.im.android.api.model.Conversation;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by theWind on 2018/8/10.
 * 私信adapter
 */

public class MyDirectAdapter extends ListBaseAdapter<Conversation> {
    private LayoutInflater mLayoutInflater;

    public MyDirectAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_my_direct_msg, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Conversation conversation = mDataList.get(position);

        ViewHolder viewHolder = (ViewHolder) holder;

    }


    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView directMsg;
        private TextView userName;
        private TextView directData;
        private CircleImageView headIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            directMsg = (TextView) itemView.findViewById(R.id.tv_mydirect_word_item);
            userName = (TextView) itemView.findViewById(R.id.tv_mydirect_username_item);
            headIcon = (CircleImageView) itemView.findViewById(R.id.iv_my_direct_headicon_item);
            directData = (TextView) itemView.findViewById(R.id.tv_mydirect_date_item);
        }
    }
}
