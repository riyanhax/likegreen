package com.pywl.likegreen.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pywl.likegreen.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by theWind on 2018/8/4.
 */

public class MyDriectAdapter extends RecyclerView.Adapter {
   private ArrayList<Object> mData;
    public MyDriectAdapter(ArrayList<Object> data){
        this.mData=data;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_direct_msg, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
     class MyViewHolder extends RecyclerView.ViewHolder{
         CircleImageView iv_my_direct_headicon_item;
         TextView tv_mydirect_username_item;
         TextView tv_mydirect_word_item;
         TextView tv_mydirect_date_item;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv_my_direct_headicon_item=(CircleImageView)itemView.findViewById(R.id.iv_my_direct_headicon_item);
            tv_mydirect_username_item =(TextView)itemView.findViewById(R.id.tv_mydirect_username_item);
            tv_mydirect_word_item=(TextView)itemView.findViewById(R.id.tv_mydirect_word_item);
            tv_mydirect_date_item=(TextView)itemView.findViewById(R.id.tv_mydirect_date_item);
        }
    }
}
