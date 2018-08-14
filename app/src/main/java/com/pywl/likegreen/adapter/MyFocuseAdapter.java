package com.pywl.likegreen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pywl.likegreen.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by theWind on 2018/8/14.
 */

public class MyFocuseAdapter extends ListBaseAdapter<String> {
    private LayoutInflater mLayoutInflater;
    public MyFocuseAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(mLayoutInflater.inflate(R.layout.item_my_focuse_and_fans,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        MyHolder viewHolder = (MyHolder) holder;

    }
    public class MyHolder extends RecyclerView.ViewHolder{
        private CircleImageView focuseHead;
        private TextView username,word,focuseBtn;
        public MyHolder(View itemView) {
            super(itemView);
            focuseHead = (CircleImageView)itemView.findViewById(R.id.ci_focuse_head);
            username=(TextView)itemView.findViewById(R.id.tv_focuse_username);
            word=(TextView)itemView.findViewById(R.id.tv_focuse_word);
            focuseBtn=(TextView)itemView.findViewById(R.id.my_home_focuse_btn);//关注按钮
        }
    }
}
