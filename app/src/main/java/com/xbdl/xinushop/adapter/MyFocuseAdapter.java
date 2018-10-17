package com.xbdl.xinushop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.bean.MyFansBean;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 我的关注和粉丝adapter
 */

public class MyFocuseAdapter extends ListBaseAdapter<MyFansBean.PdBean.ListBean> {
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
        if (mDataList!=null){
            final MyFansBean.PdBean.ListBean bean = mDataList.get(position);
            MyHolder viewHolder = (MyHolder) holder;
            viewHolder.username.setText(bean.getUser_name());
            if (TextUtils.isEmpty(bean.getSignature())){
                viewHolder.word.setText("个性签名：这人很懒没有留下什么");
            }else {
                viewHolder.word.setText("个性签名："+bean.getSignature());
            }
            if (!TextUtils.isEmpty(bean.getHead_portrait())){
                Glide.with(mContext).load(bean.getHead_portrait()).into(viewHolder.focuseHead);
            }
            viewHolder.focuseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myClick.focuseClick(bean);
                }
            });
            viewHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myClick.itemClick(bean);
                }
            });
        }

    }
    public class MyHolder extends RecyclerView.ViewHolder{
        private CircleImageView focuseHead;
        private TextView username,word,focuseBtn;
        private View view;
        public MyHolder(View itemView) {
            super(itemView);
            view=itemView;
            focuseHead = (CircleImageView)itemView.findViewById(R.id.ci_focuse_head);
            username=(TextView)itemView.findViewById(R.id.tv_focuse_username);
            word=(TextView)itemView.findViewById(R.id.tv_focuse_word);
            focuseBtn=(TextView)itemView.findViewById(R.id.my_home_focuse_btn);//关注按钮
        }
    }
    private MyClick myClick;
    public void setClick(MyClick myClick){
        this.myClick=myClick;
    }
    public interface MyClick{
        void itemClick( MyFansBean.PdBean.ListBean bean);
        void focuseClick( MyFansBean.PdBean.ListBean bean);
    }
}
