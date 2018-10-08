package com.xbdl.xinushop.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.utils.ToastUtil;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	
	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
	
    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pay_result);
        
    	api = WXAPIFactory.createWXAPI(this, MyConstants.APPIDWC, true);
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
        Log.v("WXCHAT", "type:" + resp.getType() + ",errcode:" + resp.errCode
                + ",errstr:" + resp.errStr + ",transaction:" + resp.transaction
                + ",openid:" + resp.openId + ",checkArgs:" + resp.checkArgs());
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			switch (resp.errCode){
				case BaseResp.ErrCode.ERR_OK://TODO 支付成功
					//支付成功则去后台查询支付结果再展示用户实际支付结果。
                    ToastUtil.shortToast(this,"支付成功");
					//startActivity(new Intent(this, BrowserActivity.class).putExtra("URL","file:///android_asset/orderlist.html"));
					break;
				case BaseResp.ErrCode.ERR_COMM://失败

                    ToastUtil.shortToast(this,"支付失败");
					break;
				case BaseResp.ErrCode.ERR_USER_CANCEL://取消

                    ToastUtil.shortToast(this,"支付取消");
					break;
			}
		}
		finish();
	}
}