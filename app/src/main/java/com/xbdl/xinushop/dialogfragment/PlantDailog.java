package com.xbdl.xinushop.dialogfragment;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.xbdl.xinushop.R;
import com.xbdl.xinushop.view.VerticalViewPager;


public class PlantDailog extends DialogFragment implements View.OnClickListener {
    public interface LoginInputListener {
        void onLoginInputComplete(String userName);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //设置背景透明
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    EditText ed_plantname;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_plant, null);
        ed_plantname = view.findViewById(R.id.ed_plantname);
        view.findViewById(R.id.tv_canscl).setOnClickListener(this);
        view.findViewById(R.id.tv_sure).setOnClickListener(this);


        builder.setView(view);

        return builder.create();
    }
    public void setPlantname(String plantname){
        ed_plantname.setText(plantname);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sure:
                LoginInputListener listener = (LoginInputListener) getActivity();
                listener.onLoginInputComplete(ed_plantname.getText().toString());
                dismiss();
                break;
            case R.id.tv_canscl:
                dismiss();
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.6), ViewGroup.LayoutParams.WRAP_CONTENT);
        }

    }
}