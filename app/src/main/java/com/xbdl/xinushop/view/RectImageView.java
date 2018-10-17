package com.xbdl.xinushop.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.xbdl.xinushop.R;


/**
 * Created by jh on 2017/3/28.
 */
//修改版本，原来的控件可用此替换
public class RectImageView extends android.support.v7.widget.AppCompatImageView {
    private static int RADIUS_DEFAULT = 14;
    private Paint mpaint,mwp;
    private BitmapShader mBitmapShader;
    private Matrix mMatrix;
    private RectF mRoundRect;

    /**
     * 图片的类型，圆形or圆角
     */
    private int type;
    private static final int TYPE_CIRCLE = 0;
    private static final int TYPE_ROUND = 1;

    private boolean pdw;

    /**
     * view的宽度
     */
    private int mWidth;
    /**
     * 圆角的半径
     */
    private int mRadius;

    public RectImageView(Context context) {
        super(context);
        init(context,null);
    }

    public RectImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public RectImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs){
        mMatrix=new Matrix();
        mpaint=new Paint();
        mwp=new Paint();
        mwp.setColor(Color.WHITE);
        mwp.setAntiAlias(true);
        mwp.setStyle(Paint.Style.STROKE);
        if(attrs==null){
            return;
        }

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.RectImageView);
        type = a.getInt(R.styleable.RectImageView_ctype, TYPE_ROUND);// 默认为Circle
        pdw  =a.getBoolean(R.styleable.RectImageView_pdw,false);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (type == TYPE_CIRCLE) {
            mWidth = Math.min(getMeasuredWidth(), getMeasuredHeight());
            mRadius = mWidth / 2;
            setMeasuredDimension(mWidth, mWidth);
        }
    }

    private Bitmap drawableToBitamp(Drawable drawable) {
//        int paddingleft=getPaddingLeft();
//        int paddingRight=getPaddingRight();
//        int paddingTop=getPaddingTop();
//        int paddingBottom=getPaddingBottom();
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
//            bd.setBounds(paddingleft,paddingTop,paddingRight,paddingBottom);
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
//        drawable.setBounds(paddingleft, paddingTop, w+paddingRight, h+paddingBottom);
        drawable.draw(canvas);
        return bitmap;
    }

    private void setUpShader() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }

        Bitmap bmp = drawableToBitamp(drawable);
        // 将bmp作为着色器，就是在指定区域内绘制bmp
        if(bmp==null){
            return;
        }
        mBitmapShader = new BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = 1.0f;
        // 如果图片的宽或者高与view的宽高不匹配，计算出需要缩放的比例；缩放后的图片的宽高，一定要大于我们view的宽高；所以我们这里取大值；
        scale = Math.max(getWidth() * 1.0f / bmp.getWidth(), getHeight() * 1.0f / bmp.getHeight());
        // shader的变换矩阵，我们这里主要用于放大或者缩小
        mMatrix.setScale(scale, scale);
        // 设置变换矩阵
        mBitmapShader.setLocalMatrix(mMatrix);
        // 设置shader
        mpaint.setShader(mBitmapShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }

        RADIUS_DEFAULT=dpToPx(3);
//        int paddingleft=getPaddingLeft();
//        int paddingRight=getPaddingRight();
//        int paddingTop=getPaddingTop();
//        int paddingBottom=getPaddingBottom();
        setUpShader();

        if(type==TYPE_ROUND){
//            mRoundRect.set(paddingleft,paddingTop,getWidth()-paddingRight,getHeight()-paddingBottom);
            canvas.drawRoundRect(mRoundRect, RADIUS_DEFAULT, RADIUS_DEFAULT, mpaint);
            if(pdw){
                canvas.drawRoundRect(mRoundRect, RADIUS_DEFAULT, RADIUS_DEFAULT, mwp);
            }
        }else{
//            canvas.drawCircle(mRadius,mRadius,mRadius-paddingleft,mpaint);
            canvas.drawCircle(mRadius,mRadius,mRadius,mpaint);
            canvas.drawCircle(mRadius,mRadius,mRadius,mwp);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRoundRect = new RectF(0, 0, getWidth(), getHeight());
    }

    private static final String STATE_INSTANCE = "state_instance";
    private static final String STATE_TYPE = "state_type";
    private static final String STATE_BORDER_RADIUS = "state_border_radius";

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_INSTANCE, super.onSaveInstanceState());
        bundle.putInt(STATE_TYPE, type);
        bundle.putInt(STATE_BORDER_RADIUS, RADIUS_DEFAULT);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle)
        {
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(((Bundle) state)
                    .getParcelable(STATE_INSTANCE));
            this.type = bundle.getInt(STATE_TYPE);
            this.RADIUS_DEFAULT = bundle.getInt(STATE_BORDER_RADIUS);
        } else
        {
            super.onRestoreInstanceState(state);
        }
    }

    @Override
    public void setImageResource(@DrawableRes int resId) {
        super.setImageResource(resId);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
    }

    /**
     * dp转化成为px
     *
     * @param dp
     * @return
     */
    private int dpToPx(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f * (dp >= 0 ? 1 : -1));
    }
}
