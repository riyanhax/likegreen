package com.xbdl.xinushop.adapter.mine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.activity.mine.AdMsgInputActivity;
import com.xbdl.xinushop.adapter.ListBaseAdapter;
import com.xbdl.xinushop.bean.MyProductsReleaseBean;
import com.xbdl.xinushop.bean.SellerOrderListBean;

import java.util.List;

/*
* 商品交易详情   售后详情
* */
public class MyProductstTransactionAdapter extends ListBaseAdapter<SellerOrderListBean.PageInfoBean.ListBean> {
    private LayoutInflater mLayoutInflater;

    public MyProductstTransactionAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyProductstTransactionAdapter.ViewHolder(mLayoutInflater.inflate(R.layout.item_my_products_transaction_list, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mDataList!=null){
            SellerOrderListBean.PageInfoBean.ListBean bean = mDataList.get(position);
            ViewHolder viewHolder= (ViewHolder) holder;
            viewHolder.orderNo.setText(bean.getOrdersNo());
            switch (bean.getOrdersStatus()){
                case -1:
                    //订单关闭
                    viewHolder.state.setText(mContext.getResources().getString(R.string.orderclose));
                    viewHolder.created_date.setText("下单时间："+bean.getCreatedDate());
                    viewHolder.send_shopping.setVisibility(View.GONE);
                    break;
                case 0:
                    //待发货
                    viewHolder.state.setText(mContext.getResources().getString(R.string.waitsend));
                    viewHolder.created_date.setText("下单时间："+bean.getCreatedDate());
                    viewHolder.send_shopping.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    //待收货
                    viewHolder.state.setText(mContext.getResources().getString(R.string.waitgoods));
                    viewHolder.created_date.setText("发货时间："+bean.getDeliveryTime());
                    viewHolder.send_shopping.setVisibility(View.GONE);
                    break;
                case 2:
                    //待评价
                    viewHolder.state.setText(mContext.getResources().getString(R.string.waitevaluate));
                    viewHolder.created_date.setText("收货时间："+bean.getReceivingTime());
                    viewHolder.send_shopping.setVisibility(View.GONE);
                    break;
                case 3:
                    //申请售后
                    viewHolder.state.setText(mContext.getResources().getString(R.string.applyforaftersale));
                    viewHolder.created_date.setText("收货时间："+bean.getReceivingTime());
                    viewHolder.send_shopping.setVisibility(View.GONE);
                    break;
            }
            RecyclerView recyclerView=viewHolder.recyclerView;
            //recyclerView.setPullRefreshEnabled(false);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
            MyProductstTransactionListAdapter myProductstTransactionListAdapter = new MyProductstTransactionListAdapter(mContext);
            //LRecyclerViewAdapter lRecyclerViewAdapter = new LRecyclerViewAdapter(myProductstTransactionListAdapter);

            myProductstTransactionListAdapter.setDataList(bean.getOrderCommodityList());
            recyclerView.setAdapter(myProductstTransactionListAdapter);
          /*  DividerDecoration divider = new DividerDecoration.Builder(mContext)
                    .setHeight(R.dimen.dp_3)
                    .setColorResource(R.color.enptyviewbackground)
                    .build();
            recyclerView.addItemDecoration(divider);*/

        }

    }



    private class ViewHolder extends RecyclerView.ViewHolder {


        private TextView orderNo,state,created_date,send_shopping;
        private RecyclerView recyclerView;
        public ViewHolder(View itemView) {
            super(itemView);

            orderNo= itemView.findViewById(R.id.tv_order_no);
            state= itemView.findViewById(R.id.tv_pay_state);//价格
            created_date= itemView.findViewById(R.id.tv_created_date);//时间
            send_shopping= itemView.findViewById(R.id.tv_send_shopping);//发货按钮

            recyclerView= itemView.findViewById(R.id.recyclerView);//出售量



        }
    }


}
