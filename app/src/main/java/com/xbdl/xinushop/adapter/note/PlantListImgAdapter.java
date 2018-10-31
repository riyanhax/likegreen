package com.xbdl.xinushop.adapter.note;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbdl.xinushop.MyApplication;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.PhtotViewActivity;
import com.xbdl.xinushop.adapter.ListBaseAdapter;
import com.xbdl.xinushop.bean.NoteDetailBean;
import com.xbdl.xinushop.constant.UrlConstant;
import com.xbdl.xinushop.utils.HttpUtils2;
import com.xbdl.xinushop.utils.ToastUtil;
import com.xbdl.xinushop.view.MultiImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlantListImgAdapter extends ListBaseAdapter<ImageItem> {
    private LayoutInflater mLayoutInflater;

    public PlantListImgAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlantListImgAdapter.ViewHolder(mLayoutInflater.inflate(R.layout.list_item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDataList!=null) {
            ImageItem imageItem = mDataList.get(position);
            ViewHolder viewHolder= (ViewHolder) holder;
            Glide.with(MyApplication.context).load(imageItem.path).into( viewHolder.iv_img);
           viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   click.click();
               }
           });
        }

    }




    private class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_img;
        View itemView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            iv_img = itemView.findViewById(R.id.iv_img);
        }
    }
    MyClick click;
    public void setClick(MyClick click){
        this.click=click;
    }
    public interface MyClick{
        void click();
    }
}
