package com.my.mvp.utils;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by lp on 2016/3/23.
 */
public class Aimn {
    public void rotateyAnimRun(final View view) {
        ObjectAnimator animator = ObjectAnimator
                .ofFloat(view, "rotationX", 0.0F, 360.0F)//
                .setDuration(500);
        animator.start();
    }

    public void verticalRun(final View view) {

        ObjectAnimator anim = ObjectAnimator
                .ofFloat(view, "lp", 1.0F, 0.0F)//
                .setDuration(500);//
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                view.setAlpha(cVal);
                view.setScaleX(cVal);
                view.setScaleY(cVal);
            }
        });
    }

    //第二种方式
    public void propertyValuesHolder(View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                0, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ).setDuration(1000).start();
    }
}
