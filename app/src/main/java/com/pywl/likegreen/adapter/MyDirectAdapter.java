package com.pywl.likegreen.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pywl.likegreen.R;
import com.pywl.likegreen.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theWind on 2018/8/10.
 */

public class MyDirectAdapter extends RecyclerView.Adapter<MyDirectAdapter.MyViewHolder> {
    ArrayList<String> mData=new ArrayList<String>();
    public MyDirectAdapter(ArrayList<String> data){
        this.mData=data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_direct_msg, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String s = mData.get(position);
        holder.mTvMyDirectWord.setText(s);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private OnItemClickListener mOnItemClickListener;//声明接口

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mTvMyDirectWord;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTvMyDirectWord = (TextView)itemView.findViewById(R.id.tv_mydirect_word_item);
        }
    }

}
