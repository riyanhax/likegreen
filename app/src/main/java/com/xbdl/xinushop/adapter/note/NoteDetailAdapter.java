package com.xbdl.xinushop.adapter.note;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.PhtotViewActivity;
import com.xbdl.xinushop.adapter.ListBaseAdapter;
import com.xbdl.xinushop.view.MultiImageView;

public class NoteDetailAdapter extends ListBaseAdapter<String> {
    private LayoutInflater mLayoutInflater;

    public NoteDetailAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoteDetailAdapter.ViewHolder(mLayoutInflater.inflate(R.layout.item_note_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDataList!=null){
            ViewHolder viewHolder= (ViewHolder) holder;

        }

    }



    private class ViewHolder extends RecyclerView.ViewHolder {
        private MultiImageView photo;



        public ViewHolder(View itemView) {
            super(itemView);

        }
    }

}
