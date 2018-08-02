package com.pywl.likegreen.customtabentitys;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Created by theWind on 2018/7/31.
 */

public class DirMsgEntity implements CustomTabEntity {
    @Override
    public String getTabTitle() {
        return "消息";
    }

    @Override
    public int getTabSelectedIcon() {
        return 0;
    }

    @Override
    public int getTabUnselectedIcon() {
        return 0;
    }
}
