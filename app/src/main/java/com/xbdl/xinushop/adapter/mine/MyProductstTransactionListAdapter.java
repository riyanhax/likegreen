package com.xbdl.xinushop.adapter.mine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.ListBaseAdapter;
import com.xbdl.xinushop.bean.SellerOrderListBean;

/*
* 商品交易详情   售后详情  里面的列表
* */
public class MyProductstTransactionListAdapter extends ListBaseAdapter<SellerOrderListBean.PageInfoBean.ListBean.OrderCommodityListBean> {
    private LayoutInflater mLayoutInflater;

    public MyProductstTransactionListAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyProductstTransactionListAdapter.ViewHolder(mLayoutInflater.inflate(R.layout.item_my_products_transaction, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDataList!=null){
            SellerOrderListBean.PageInfoBean.ListBean.OrderCommodityListBean bean = mDataList.get(position);
            ViewHolder viewHolder= (ViewHolder) holder;
            //viewHolder.products_name.setText(bean.get);
            viewHolder.sales_volume.setText("x"+bean.getBuyCount());
        }

    }



    private class ViewHolder extends RecyclerView.ViewHolder {

        private  ImageView products_icon;
        private TextView products_name,sales_volume;

        public ViewHolder(View itemView) {
            super(itemView);
             products_icon = (ImageView)itemView.findViewById(R.id.iv_products_icon);
            products_name= itemView.findViewById(R.id.tv_products_name);

            sales_volume= itemView.findViewById(R.id.tv_sales_volume);//出售量


        }
    }


}
