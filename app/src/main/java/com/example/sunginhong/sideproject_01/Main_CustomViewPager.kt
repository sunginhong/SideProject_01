package com.example.sunginhong.sideproject_01

import android.content.Context
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
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return if (this.enabled!!) {
            super.onInterceptTouchEvent(event)
        } else false

    }

    fun setPagingEnabled(enabled: Boolean) {
        this.enabled = enabled
    }

}