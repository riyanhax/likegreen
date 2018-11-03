package com.xbdl.xinushop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.note.PlantDetailActivity;
import com.xbdl.xinushop.adapter.baseadapter.BaseViewHolder;
import com.xbdl.xinushop.adapter.baseadapter.SimpleAdapter;
import com.xbdl.xinushop.bean.PlantingDiaryAdapterBean;
import com.xbdl.xinushop.bean.PlantingDiaryBean;
import com.xbdl.xinushop.utils.AppPhoneMgr;

import java.util.ArrayList;
import java.util.List;

public class PlantingDiaryMyGrardenAdapter extends SimpleAdapter<PlantingDiaryBean> {
    public PlantingDiaryMyGrardenAdapter(Context context) {
        super(context, R.layout.item_plantingdirayrmygradenlayout);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, final PlantingDiaryBean item) {
        RecyclerView recyclerView = (RecyclerView) viewHoder.getView(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        PlantingDiaryMyGrardenItemAdapter adapter = new PlantingDiaryMyGrardenItemAdapter(context);
        recyclerView.setAdapter(adapter);

        List<PlantingDiaryAdapterBean> adapterBeans = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            PlantingDiaryAdapterBean plantingDiaryAdapterBean = new PlantingDiaryAdapterBean();
            if (i == 0) {

                plantingDiaryAdapterBean.setDay(item.getDay1());
                plantingDiaryAdapterBean.setP(item.getP1());
                if (item.getP1() != null) {
                    adapterBeans.add(plantingDiaryAdapterBean);
                }
            } else if (i == 1) {
                plantingDiaryAdapterBean.setDay(item.getDay2());
                plantingDiaryAdapterBean.setP(item.getP2());
                if (item.getP2() != null) {
                    adapterBeans.add(plantingDiaryAdapterBean);
                }
            } else {
                plantingDiaryAdapterBean.setDay(item.getDay3());
                plantingDiaryAdapterBean.setP(item.getP3());
                if (item.getP3() != null) {
                    adapterBeans.add(plantingDiaryAdapterBean);
                }
            }


        }
        if (adapterBeans != null && adapterBeans.size() > 0) {
            adapter.refreshData(adapterBeans);
        }


        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent plantitent = new Intent(context, PlantDetailActivity.class);
                plantitent.putExtra("plantingDiaryBean", item);
                context.startActivity(plantitent);
            }
        });
    }


    private class PlantingDiaryMyGrardenItemAdapter extends SimpleAdapter<PlantingDiaryAdapterBean> {

        public PlantingDiaryMyGrardenItemAdapter(Context context) {
            super(context, R.layout.item_plantingdiraymygradeitemlayout);
        }

        @Override
        protected void convert(BaseViewHolder viewHoder, PlantingDiaryAdapterBean item) {
            AppCompatImageView ivIcon = (AppCompatImageView) viewHoder.getView(R.id.iv_icon);
            AppCompatTextView tvTime = (AppCompatTextView) viewHoder.getView(R.id.tv_time);

            RequestOptions options = new RequestOptions().
                    placeholder(R.mipmap.ic_launcher)                //加载成功之前占位图               
                    .error(R.mipmap.ic_launcher)
                    //加载错误之后的错误图               
                    .centerCrop();//指定图片的缩放类型为centerCrop （等比例缩放图片，直到图片的狂高都大于等于ImageView的宽度，然后截取中间的显示。） // ;
            //               .centerCrop()                .circleCrop()//指定图片的缩放类型为centerCrop （圆形）                .skipMemoryCache(true)							//跳过内存缓存                .diskCacheStrategy(DiskCacheStrategy.ALL)		//缓存所有版本的图像                .diskCacheStrategy(DiskCacheStrategy.NONE)		//跳过磁盘缓存                .diskCacheStrategy(DiskCacheStrategy.DATA)		//只缓存原来分辨率的图片                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)	//只缓存最终的图片                ;

            int width = AppPhoneMgr.getInstance().getPhoneWidth(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width / 3, width / 3);
            ivIcon.setLayoutParams(params);

            Glide.with(context).load(item.getP().getImgpath()).apply(options).into(ivIcon);


            tvTime.setText("第" + item.getDay() + "天");
        }
    }
}
