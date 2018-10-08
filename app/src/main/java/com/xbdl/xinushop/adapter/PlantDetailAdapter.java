package com.xbdl.xinushop.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.baseadapter.BaseViewHolder;
import com.xbdl.xinushop.adapter.baseadapter.SimpleAdapter;
import com.xbdl.xinushop.bean.PlantDetailBean;
import com.xbdl.xinushop.utils.AppPhoneMgr;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlantDetailAdapter extends SimpleAdapter<PlantDetailBean> {
    public PlantDetailAdapter(Context context) {
        super(context, R.layout.item_plantdetaillayout);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, final PlantDetailBean item) {
        Log.i("asdf","getPdId"+item.getP().getPdId());
        AppCompatTextView tvDay = viewHoder.getAppCompatTextView(R.id.tv_day);
        tvDay.setText("第" + item.getDay() + "天");

        AppCompatTextView tvMinute = viewHoder.getAppCompatTextView(R.id.tv_minute);
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd");

        tvMinute.setText(simpleFormatter.format(new Date(item.getP().getTime())));


        AppCompatImageView imageView = viewHoder.getAppCompatImageView(R.id.iv_icon);

        int width = AppPhoneMgr.getInstance().getPhoneWidth(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width / 2, width * 2 / 3);
        imageView.setLayoutParams(params);
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher);
        Glide.with(imageView.getContext()).load(item.getP().getImgpath()).apply(options).into(imageView);


        //like
        AppCompatTextView tvLike = viewHoder.getAppCompatTextView(R.id.tv_like);
        tvLike.setText(item.getLikeCount() + "");

        AppCompatTextView tvComment = viewHoder.getAppCompatTextView(R.id.tv_comment);
        tvComment.setText(item.getCommentCount() + "");

        tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onPlantDetailItemListener!=null)
                {
                    onPlantDetailItemListener.onItemComment(item);
                }
            }
        });

        AppCompatTextView tvLocation = viewHoder.getAppCompatTextView(R.id.tv_location);
        tvLocation.setText(item.getP().getAddress());

        AppCompatTextView tvWeath = viewHoder.getAppCompatTextView(R.id.tv_weath);
        tvWeath.setText(item.getP().getDesc());
    }

    public void setOnPlantDetailItemListener(OnPlantDetailItemListener onPlantDetailItemListener) {
        this.onPlantDetailItemListener = onPlantDetailItemListener;
    }

    OnPlantDetailItemListener onPlantDetailItemListener;



    public interface  OnPlantDetailItemListener{
        void onItemComment(PlantDetailBean item);
    }
}
