package com.pywl.likegreen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pywl.likegreen.R;

public class NoteHotAdapter extends ListBaseAdapter<String> {
    private LayoutInflater mLayoutInflater;

    public NoteHotAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoteHotAdapter.ViewHolder(mLayoutInflater.inflate(R.layout.item_note_hot, parent, false));
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
