package com.xbdl.xinushop.adapter.mine;

import android.content.Context;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.mine.AdMsgInputActivity;
import com.xbdl.xinushop.adapter.ListBaseAdapter;
import com.xbdl.xinushop.bean.MyProductsReleaseBean;

public class MyProductsReleaseAdapter extends ListBaseAdapter<MyProductsReleaseBean.ListBean> {
    private LayoutInflater mLayoutInflater;

    public MyProductsReleaseAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyProductsReleaseAdapter.ViewHolder(mLayoutInflater.inflate(R.layout.item_my_products_release, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDataList!=null){
            ViewHolder viewHolder= (ViewHolder) holder;
            final MyProductsReleaseBean.ListBean listBean = mDataList.get(position);
            viewHolder.products_name.setText(listBean.getCommodityName());
            viewHolder.products_price.setText("¥ "+listBean.getCommodityPrice());
            viewHolder.sales_volume.setText("已售： "+listBean.getSalesVolume());
            if (listBean.getCommodityStatus()==0){
                //已下架
                viewHolder.sales_state.setText(mContext.getText(R.string.saledown));
                viewHolder.sales_state.setBackground( mContext.getResources().getDrawable(R.drawable.bg_products_saleoff));
                viewHolder.sales_state.setTextColor(mContext.getResources().getColor(R.color.maintextcolor));

            }else {
                //销售中
                viewHolder.sales_state.setText(mContext.getText(R.string.salein));
                viewHolder.sales_state.setBackground( mContext.getResources().getDrawable(R.drawable.bg_products_salein));
                viewHolder.sales_state.setTextColor(mContext.getResources().getColor(R.color.colorfocuse));
            }
            viewHolder.created_date.setText(listBean.getCreatedDate());
            Glide.with(mContext).load(listBean.getTitleUrl()).into(viewHolder.products_icon);
            //编辑
            viewHolder.prodict_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent AdMsgInputintent = new Intent(mContext, AdMsgInputActivity.class);
                    AdMsgInputintent.putExtra("bean", listBean);
                    mContext.startActivity(AdMsgInputintent);
                }
            });
            //删除
            viewHolder.prodict_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    }



    private class ViewHolder extends RecyclerView.ViewHolder {

        private  ImageView products_icon;
        private TextView products_name,products_price,browsing_volume,sales_volume,inventory,sales_state,created_date;
        private View prodict_edit,prodict_delete,prodict_share;
        public ViewHolder(View itemView) {
            super(itemView);
             products_icon = (ImageView)itemView.findViewById(R.id.iv_products_icon);
            products_name= itemView.findViewById(R.id.tv_products_name);
            products_price= itemView.findViewById(R.id.tv_products_price);//价格
            //browsing_volume= itemView.findViewById(R.id.tv_browsing_volume);//浏览量
            sales_volume= itemView.findViewById(R.id.tv_sales_volume);//出售量
           // inventory= itemView.findViewById(R.id.tv_inventory);//库存量
            sales_state= itemView.findViewById(R.id.tv_sales_state);//出售状态
            created_date= itemView.findViewById(R.id.tv_created_date);//发布时间
            prodict_edit= itemView.findViewById(R.id.ll_prodict_edit);//编辑
            prodict_delete= itemView.findViewById(R.id.ll_prodict_delete);//删除
           // prodict_share= itemView.findViewById(R.id.ll_prodict_share);//分享

        }
    }


}
