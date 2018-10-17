package com.xbdl.xinushop.adapter.note;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xbdl.xinushop.R;

import com.xbdl.xinushop.view.RectImageView;

import java.util.ArrayList;

/**
 * Created by jh on 2016/9/1.
 */
public class LifelogAdapter extends RecyclerView.Adapter<LifelogAdapter.Hodler> {
    private ArrayList<String> list;
    private Context mcon;

    public LifelogAdapter(ArrayList<String> list, Context mcon) {
        this.list = list;
        this.mcon = mcon;
    }

    @Override
    public Hodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcon).inflate(R.layout.item_lifelog, parent, false);
        Hodler hodler = new Hodler(view);
        return hodler;
    }

    @Override
    public void onBindViewHolder(final Hodler holder, final int position) {
        String item = list.get(position);
       /* holder.title.setText(item.getTitle());
        holder.tcontext.setText(item.getContent());
        holder.tuname.setText(item.getUsername());
        holder.tcomit.setText(item.getComment());
        holder.tcollect.setText(item.getMark());
        holder.tzan.setText(item.getLike());
        AsynImageLoader.showImageAsyn(mcon, holder.iv, item.getAvatar(), R.mipmap.head_blank);
        ArrayList<String> photos = item.getPhotos();
        int length = photos.size();
        holder.ivpic1.setVisibility(View.INVISIBLE);
        holder.ivpic2.setVisibility(View.INVISIBLE);
        holder.ivpic3.setVisibility(View.INVISIBLE);
        switch (length) {
            case 1:
                holder.ivpic1.setVisibility(View.VISIBLE);
                AsynImageLoader.showImageAsyn(mcon, holder.ivpic1, photos.get(0), R.mipmap.head_blank);
                break;
            case 2:
                holder.ivpic1.setVisibility(View.VISIBLE);
                holder.ivpic2.setVisibility(View.VISIBLE);
                AsynImageLoader.showImageAsyn(mcon, holder.ivpic1, photos.get(0), R.mipmap.head_blank);
                AsynImageLoader.showImageAsyn(mcon, holder.ivpic2, photos.get(1), R.mipmap.head_blank);
                break;
            case 3:
                holder.ivpic1.setVisibility(View.VISIBLE);
                holder.ivpic2.setVisibility(View.VISIBLE);
                holder.ivpic3.setVisibility(View.VISIBLE);
                AsynImageLoader.showImageAsyn(mcon, holder.ivpic1, photos.get(0), R.mipmap.head_blank);
                AsynImageLoader.showImageAsyn(mcon, holder.ivpic2, photos.get(1), R.mipmap.head_blank);
                AsynImageLoader.showImageAsyn(mcon, holder.ivpic3, photos.get(2), R.mipmap.head_blank);
                break;
        }
        clickItem(holder.itemview, position);*/
    }

    @Override
    public int getItemCount() {
//        return list.size();
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    protected void clickItem(View v, final int index) {
        v.setTag(index);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onLifelogItemClick(list.get(index));
                }
            }
        });
    }

    class Hodler extends RecyclerView.ViewHolder {
        View itemview;
        RectImageView iv;
        ImageView ivpic1, ivpic2, ivpic3;
        TextView title, tcontext, tuname, tcomit, tcollect, tzan;

        public Hodler(View itemView) {
            super(itemView);
            this.itemview = itemView;

            tcontext = ((TextView) itemView.findViewById(R.id.tcontext));
            tuname = ((TextView) itemView.findViewById(R.id.cname));
            ivpic1 = ((ImageView) itemView.findViewById(R.id.iv1));
            ivpic2 = ((ImageView) itemView.findViewById(R.id.iv2));
            ivpic3 = ((ImageView) itemView.findViewById(R.id.iv3));
            title = ((TextView) itemView.findViewById(R.id.ttitle));
            tcomit = ((TextView) itemView.findViewById(R.id.numcmit));
            tcollect = ((TextView) itemView.findViewById(R.id.numcollect));
            tzan = ((TextView) itemView.findViewById(R.id.numzan));
            iv = ((RectImageView) itemView.findViewById(R.id.ivavatar));
        }
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onLifelogItemClick(String lifelog);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
