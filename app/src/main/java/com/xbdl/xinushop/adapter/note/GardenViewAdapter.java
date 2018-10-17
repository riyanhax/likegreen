package com.xbdl.xinushop.adapter.note;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xbdl.xinushop.R;


import java.util.ArrayList;

/**
 * 花园的预览
 * Created by zy on 2018/2/3.
 */
public class GardenViewAdapter extends RecyclerView.Adapter<GardenViewAdapter.Hodler> {
    private ArrayList<String> list;
    private Context mcon;

    public GardenViewAdapter(ArrayList<String> list, Context mcon) {
        this.list = list;
        this.mcon = mcon;
    }

    @Override
    public Hodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcon).inflate(R.layout.item_garden_view, parent, false);
        return new Hodler(view);
    }

    @Override
    public void onBindViewHolder(final Hodler holder, final int position) {
      /*  Pot pot = list.get(position);

        AsynImageLoader.showImageAsyn(mcon, holder.iv_pic, pot.getFace());
        holder.tv_name.setText(pot.getName());
        holder.tv_date.setText(pot.getDate());

        clickItem(holder.lay2, position);*/
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class Hodler extends RecyclerView.ViewHolder {
        View itemview, lay2;
        ImageView iv_pic;
        TextView tv_name, tv_date;

        public Hodler(View itemView) {
            super(itemView);
            this.itemview = itemView;

            lay2 = itemView.findViewById(R.id.lay2);
            iv_pic = (ImageView) itemView.findViewById(R.id.iv_pic);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }

    protected void clickItem(View v, final int index) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(list.get(index));
                }
            }
        });
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String pot);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
