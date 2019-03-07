package com.example.sunginhong.sideproject_01

import android.content.Context
import android.support.v4.view.MotionEventCompat
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent


class Main_CustomViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    var enabled: Boolean? = false

    init {
        this.enabled = true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (this.enabled!!) {
            super.onTouchEvent(event)
        } else false

    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {

        val action = event.action and MotionEventCompat.ACTION_MASK

        when (action) {

            MotionEvent.ACTION_DOWN -> {

            }
            MotionEvent.ACTION_MOVE -> {
                Main_CustomViewPagerAdapter.viewPosReset(MainActivity.mainVp!!.currentItem)
            }
        }

        try {
            return super.onInterceptTouchEvent(event)
        } catch (e: IllegalArgumentException) {
            return false
        }
        return super.onInterceptTouchEvent(event)
    }
//    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
//        try {
//            return super.onInterceptTouchEvent(event)
//        } catch (e: IllegalArgumentException) {
//            return false
//        }
//    }

    fun setPagingEnabled(enabled: Boolean) {
        this.enabled = enabled
    }

}
