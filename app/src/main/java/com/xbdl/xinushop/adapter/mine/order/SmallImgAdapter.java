package com.xbdl.xinushop.adapter.mine.order;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.bean.ImageItem;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.ListBaseAdapter;

import java.util.ArrayList;

/**
 * 全部订单
 */

public class SmallImgAdapter extends ListBaseAdapter<ImageItem> {
    private LayoutInflater mLayoutInflater;

    public SmallImgAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_small_img, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDataList!=null){
            ViewHolder viewHolder= (ViewHolder) holder;
            ImageItem imageItem = mDataList.get(position);
            Glide.with(mContext).load(imageItem.path).into(viewHolder.img);
        }

    }



    private class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
             img = itemView.findViewById(R.id.iv_item);
        }
    }
}
