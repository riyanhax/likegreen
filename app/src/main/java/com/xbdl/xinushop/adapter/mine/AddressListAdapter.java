package com.xbdl.xinushop.adapter.mine;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.ListBaseAdapter;
import com.xbdl.xinushop.bean.AddressBean;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.UserInfo;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by theWind on 2018/8/10.
 * 收货地址列表
 */

public class AddressListAdapter extends ListBaseAdapter<AddressBean.AddressListBean> {
    private LayoutInflater mLayoutInflater;

    public AddressListAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_address, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (mDataList!=null){
            final AddressBean.AddressListBean bean = mDataList.get(position);
            ViewHolder viewHolder= (ViewHolder) holder;
            viewHolder.tname.setText(bean.getConsignee());
            viewHolder.tphone.setText(bean.getContactWay());
            String detailAddress="收货地址："+bean.getProvince()+bean.getCity()+bean.getDistrict()+bean.getContactAddress();
            viewHolder.tdetail.setText(detailAddress);

            int isDefaultAddress = bean.getIsDefaultAddress();
            if (isDefaultAddress==1){
                viewHolder.ivdefault.setImageResource(R.drawable.xuanzhong);
            }else {
                viewHolder.ivdefault.setImageResource(R.drawable.weixuanzhong);
            }
          /*  viewHolder.ivdefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    click.checkboxClick(bean.getUserAddressID());
                }
            });*/
            viewHolder.lldefault.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("nihaoma","position"+position+"  adapter地址"+bean.toString());
                    click.checkboxClick(bean.getUserAddressID());
                }
            });
            viewHolder.lledit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.editClick(bean);
                }
            });
            viewHolder.lldelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.deleteClick(bean.getUserAddressID());
                }
            });
        }

    }



    private class ViewHolder extends RecyclerView.ViewHolder {
       private  TextView tname;
       private  TextView tphone;
       private  TextView tdetail;
       private ImageView ivdefault;
       private View lledit,lldelete,lldefault;
        public ViewHolder(View itemView) {
            super(itemView);
            tname= itemView.findViewById(R.id.tname);
            tphone= itemView.findViewById(R.id.tphone);
            tdetail= itemView.findViewById(R.id.tdetail);
            ivdefault= itemView.findViewById(R.id.ivdefault);
            lldefault= itemView.findViewById(R.id.lldefault);
            lledit= itemView.findViewById(R.id.lledit);
            lldelete= itemView.findViewById(R.id.lldelete);
        }
    }
    private AddressClick click;
    public void setAddressClick(AddressClick click){
        this.click=click;
    }

    public interface AddressClick{
         void checkboxClick(int addressId);
         void editClick(AddressBean.AddressListBean bean);
         void deleteClick(int addressId);
    }
}
