package com.xbdl.xinushop.bean;

/**
 * 种植日记列表adapter
 */
public class PlantingDiaryAdapterBean {
    private PlantingDiaryBean.PBean p;
    private int day;

    public PlantingDiaryBean.PBean getP() {
        return p;
    }

    public void setP(PlantingDiaryBean.PBean p) {
        this.p = p;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "PlantingDiaryAdapterBean{" +
                "p=" + p +
                ", day=" + day +
                '}';
    }
}
