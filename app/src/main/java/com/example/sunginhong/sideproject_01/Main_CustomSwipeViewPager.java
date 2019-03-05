package com.example.sunginhong.sideproject_01;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class Main_CustomSwipeViewPager extends ViewPager {

    private static final int OFF_SET = 10;


    private float preX;

    public Main_CustomSwipeViewPager(Context context) {
        super(context);
    }

    public Main_CustomSwipeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        final int action = ev.getAction() & MotionEventCompat.ACTION_MASK;

        switch (action){

            case MotionEvent.ACTION_DOWN:
                preX = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:

                float x = ev.getX();
                if((x - OFF_SET <= preX && preX <= x+ OFF_SET)){
                    return false;
                }else{
                    return true;
                }
        }

        return super.onInterceptTouchEvent(ev);
    }

}

