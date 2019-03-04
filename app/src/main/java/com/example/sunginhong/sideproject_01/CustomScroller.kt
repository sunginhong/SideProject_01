package com.example.sunginhong.sideproject_01

import android.content.Context
import android.view.animation.Interpolator
import android.widget.Scroller


class CustomScroller(context: Context, interpolator: Interpolator, duration: Int) : Scroller(context, interpolator) {
    private var mDuration = 0

    var customDuration: Int
        get() = mDuration
        set(customDuration) = if (customDuration <= MIN) {
            mDuration = MIN
        } else if (customDuration >= MAX) {
            mDuration = MAX
        } else {
            mDuration = customDuration
        }

    init {
        mDuration = duration
    }

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration)
    }

    companion object {

        private val MIN = 0
        private val MAX = 3000
    }
}