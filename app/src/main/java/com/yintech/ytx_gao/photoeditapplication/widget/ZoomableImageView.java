package com.yintech.ytx_gao.photoeditapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.yintech.ytx_gao.photoeditapplication.widget.zoomable.DefaultZoomableController;
import com.yintech.ytx_gao.photoeditapplication.widget.zoomable.ZoomableController;

/**
 * Created by ytx_gao on 12/09/2017.
 */

public class ZoomableImageView extends android.support.v7.widget.AppCompatImageView implements ZoomableController.Listener {

    private static final String TAG = "ZoomableImageView";

    private static final int TOUCH_TIME = 250; // 触摸间隔时间

    private ZoomableController mZoomableController = DefaultZoomableController.newInstance();

    public ZoomableImageView(Context context) {
        this(context, null);
    }

    public ZoomableImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZoomableImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mZoomableController.setListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int saveCount = canvas.save();
        canvas.concat(mZoomableController.getTransform());
        super.onDraw(canvas);
        canvas.restoreToCount(saveCount);
    }

    public long mCurrDownTime = 0;

    private OnClickListener mOnClickListener;

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        this.mOnClickListener = l;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mCurrDownTime = event.getEventTime();
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (event.getEventTime() - mCurrDownTime <= TOUCH_TIME) {
                //点击
                if (mOnClickListener != null) {
                    mOnClickListener.onClick(this);
                }
            }
        }

        if (mZoomableController.onTouchEvent(event)) {
            if (mZoomableController.getScaleFactor() > 1.0f) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
//      FLog.v(TAG, "onTouchEvent: view %x, handled by zoomable controller", this.hashCode());
            return true;
        }
//    FLog.v(TAG, "onTouchEvent: view %x, handled by the super", this.hashCode());
        return super.onTouchEvent(event);
    }

    @Override
    public void onTransformChanged(Matrix transform) {

    }
}
