package com.xbdl.xinushop.fragment.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xbdl.xinushop.R;

/**
 * Created by theWind on 2018/8/1.
 * 广告视频 审核
 */

public class AuditFragment extends Fragment implements View.OnClickListener {
    private View mRlinAudit,mRlunAudit;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audit, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View v) {
        mRlinAudit = v.findViewById(R.id.rl_in_audit);
        mRlunAudit = v.findViewById(R.id.rl_unaudit);
        mRlinAudit.setOnClickListener(this);
        mRlunAudit.setOnClickListener(this);
    }

    private void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_in_audit:
                break;
            case R.id.rl_unaudit:
                break;
        }

    }
}
