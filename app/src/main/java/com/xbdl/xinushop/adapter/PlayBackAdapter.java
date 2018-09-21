package com.xbdl.xinushop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by theWind on 2018/8/6.
 */

public class PlayBackAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> data;

    public PlayBackAdapter(Context context, List<String> data){
        this.context = context;
        this.data = data;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class PlayBackViewHodler extends RecyclerView.ViewHolder{

        public PlayBackViewHodler(View itemView) {
            super(itemView);
        }
    }
}
