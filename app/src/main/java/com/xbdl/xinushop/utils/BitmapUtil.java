package com.xbdl.xinushop.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 *
 * Created by huang on 2017/4/12.
 */

public class BitmapUtil {
    /**
     * 图片按长宽各自比例进行缩小
     *
     * @param bitmap 进行缩放的图
     * @param scalX 缩放宽度
     * @param scalY 缩放高度
     * @return Bitmap
     */
    public static Bitmap adaptiveFor_RHW(Bitmap bitmap, float scalX, float scalY, float rotateDegree) {
        Matrix matrix = new Matrix();
        int width = bitmap.getWidth();// 获取资源位图的宽
        int height = bitmap.getHeight();// 获取资源位图的高
        float w = scalX / bitmap.getWidth();
        float h = scalY / bitmap.getHeight();
        matrix.postScale(w, h);// 获取缩放比例
        matrix.postRotate(rotateDegree);
        // 根据缩放比例获取新的位图
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        return newbmp;
    }

    /***
     * 七牛图片固定宽度 高度自适应
     * @param url 原地址
     * @param w  宽度 eg 100 头像建议100 其他缩略图建议200
     * @return
     */
    public static String qiniuFixWith(String url, int w){
        if (StringUtils.isEmpty(url) || !url.contains("bkt.clouddn.com")){
            return url;
        }
        if (url.contains("?")){
            url += "|imageView2/2/w/" + w;
        }else{
            url += "?imageView2/2/w/" + w;
        }
        return url;
    }

}
