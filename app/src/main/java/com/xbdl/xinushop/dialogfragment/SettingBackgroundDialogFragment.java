package com.xbdl.xinushop.dialogfragment;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.xbdl.xinushop.R;
import com.xbdl.xinushop.utils.AppPhoneMgr;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingBackgroundDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 * 选择拍照  选择相册 dialogfragment
 */
public class SettingBackgroundDialogFragment extends DialogFragment {
    public SettingBackgroundDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0xffffff0));
        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
    }

    public static SettingBackgroundDialogFragment newInstance() {
        SettingBackgroundDialogFragment fragment = new SettingBackgroundDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_background_dialog, container, false);

        onPhotoLinstener = (OnPhotoLinstener) getActivity();

        getDialog().setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        window.setAttributes(wlp);
//        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
        lp.width = AppPhoneMgr.getInstance().getPhoneWidth(getActivity());
        getDialog().getWindow().setAttributes(lp);

        TextView tvTakephoto = (TextView) view.findViewById(R.id.tv_takephoto);
        TextView tvPhotoView = (TextView) view.findViewById(R.id.tv_photoview);
        TextView tvDismiss = (TextView) view.findViewById(R.id.tv_dismiss);

        tvDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });


        tvTakephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPhotoLinstener != null) {
                    onPhotoLinstener.takePhoto(v);
                }
                getDialog().dismiss();
            }
        });
        tvPhotoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPhotoLinstener != null) {
                    onPhotoLinstener.takePhoto(v);
                }
                getDialog().dismiss();
            }
        });
        return view;
    }

    OnPhotoLinstener onPhotoLinstener;

    public interface OnPhotoLinstener {
        void takePhoto(View view);
    }
}
