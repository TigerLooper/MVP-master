package com.my.mvp.utils;

import android.content.Context;
import android.graphics.Picture;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lp on 2016/8/25.
 */
public class CustomViewTest extends View {
    private int mWidth, mHeight;
    private Picture mPicture = new Picture();

    public CustomViewTest(Context context) {
        super(context);
    }

    public CustomViewTest(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }

        return super.onTouchEvent(event);
    }
}
