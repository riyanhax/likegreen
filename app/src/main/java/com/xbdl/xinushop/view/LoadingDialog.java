package com.xbdl.xinushop.view;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.xbdl.xinushop.R;



/**
 * 加载Dialog
 * @author YSL
 */
public class LoadingDialog extends ProgressDialog {
	private TextView tvContent;
	Context context;
	public LoadingDialog(Context context) {
		super(context);
		this.context = context;
	}
	public LoadingDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
/*		if (context instanceof Activity) {
			if(ProjectDonateActivity.class.getName().equals(context.getClass().getName())){
				((Activity) context).finish();
			}else{
				if(!((Activity) context).isFinishing()){
					this.dismiss();
				}
			}
		}*/
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_gray);
		setCancelable(true);
		setCanceledOnTouchOutside(true);
	}

	public LoadingDialog setLoadingDialogTitle(String txt){
		if(tvContent == null){
			tvContent = (TextView)findViewById(R.id.tv_loding_msg);
		}
		if(tvContent != null){
			tvContent.setText(txt);
		}
		return this;
	}

}
