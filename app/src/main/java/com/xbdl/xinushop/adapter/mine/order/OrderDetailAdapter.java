package com.xbdl.xinushop.adapter.mine.order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.ListBaseAdapter;

/**
 * 全部订单 订单详情
 */

public class OrderDetailAdapter extends ListBaseAdapter<String> {
    private LayoutInflater mLayoutInflater;

    public OrderDetailAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_order_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDataList!=null){

        }

    }



    private class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);

        }
    }
}
