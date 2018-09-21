package com.xbdl.xinushop.adapter;

import android.content.Context;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.xbdl.xinushop.R;


import de.hdodenhof.circleimageview.CircleImageView;

public class MyProductsReleaseAdapter extends ListBaseAdapter<String> {
    private LayoutInflater mLayoutInflater;

    public MyProductsReleaseAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyProductsReleaseAdapter.ViewHolder(mLayoutInflater.inflate(R.layout.item_my_products_release, parent, false));
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
