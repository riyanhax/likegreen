package com.xbdl.xinushop.adapter.note;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.mine.UserDetailActivity;
import com.xbdl.xinushop.adapter.ListBaseAdapter;
import com.xbdl.xinushop.bean.NoteHotBean;
import com.xbdl.xinushop.utils.AppPhoneMgr;
import com.xbdl.xinushop.utils.HttpUtils2;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlantImgListAdapter extends ListBaseAdapter<String> {
    private LayoutInflater mLayoutInflater;

    public PlantImgListAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_math_img, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (mDataList!=null) {
            String url = mDataList.get(position);
            ViewHolder viewHolder= (ViewHolder) holder;
            int width = AppPhoneMgr.getInstance().getPhoneWidth(mContext);
            LinearLayout.LayoutParams params = null;
            params = new LinearLayout.LayoutParams(width, width * 2 / 3);
            viewHolder.iv_plant.setLayoutParams(params);
            Glide.with(MyApplication.context).load(url).into(viewHolder.iv_plant);
        }
    }



    private class ViewHolder extends RecyclerView.ViewHolder {
            ImageView iv_plant;
        public ViewHolder(View itemView) {
            super(itemView);
             iv_plant = itemView.findViewById(R.id.iv_plant);
        }
    }

}
