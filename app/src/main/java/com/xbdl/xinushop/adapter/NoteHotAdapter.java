package com.xbdl.xinushop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.PhtotViewActivity;
import com.xbdl.xinushop.view.MultiImageView;

import uk.co.senab.photoview.PhotoView;

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
            ViewHolder viewHolder= (ViewHolder) holder;
            viewHolder.photo.setList(mDataList);
            viewHolder.photo.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(view.getContext(), PhtotViewActivity.class);
                    view.getContext().startActivity(intent);
                }
            });
        }

    }



    private class ViewHolder extends RecyclerView.ViewHolder {
        private MultiImageView photo;



        public ViewHolder(View itemView) {
            super(itemView);
            photo =(MultiImageView) itemView.findViewById(R.id.iv_note_photo);


        }
    }

}
