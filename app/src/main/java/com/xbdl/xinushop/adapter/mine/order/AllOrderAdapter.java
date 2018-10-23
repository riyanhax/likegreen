package com.xbdl.xinushop.adapter.mine.order;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.ListBaseAdapter;
import com.xbdl.xinushop.bean.DiscountCouponBean;

import java.util.ArrayList;

/**
 * 全部订单
 */

public class AllOrderAdapter extends ListBaseAdapter<String> {
    private LayoutInflater mLayoutInflater;

    public AllOrderAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_oder_list, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDataList!=null){
            ViewHolder viewHolder= (ViewHolder) holder;
            OrderDetailAdapter orderDetailAdapter = new OrderDetailAdapter(mContext);
            ArrayList<String> strings = new ArrayList<>();
            strings.add("ss");
            strings.add("ss");
            strings.add("ss");
            orderDetailAdapter.setDataList(strings);
            viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
            viewHolder.recyclerView.setAdapter(orderDetailAdapter);
        }

    }



    private class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recyclerView);
        }
    }
}
