package com.pywl.likegreen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pywl.likegreen.R;

/**
 * Created by theWind on 2018/8/10.
 */

public class Myadapter extends ListBaseAdapter<String> {
    private LayoutInflater mLayoutInflater;

    public Myadapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_my_direct_msg, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String item = mDataList.get(position);

        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.textView.setText(item);
    }


    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_mydirect_word_item);
        }
    }
}
