package com.example.sunginhong.sideproject_01.Utils_Folder

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.WindowManager


internal object Utils_Func {
//    fun dpToPx(dp: Int): Int {
//        return (dp * Resources.getSystem().getDisplayMetrics().density) as Int
//    }
//
//    fun pxToDp(px: Int): Int {
//        return (px / Resources.getSystem().getDisplayMetrics().density) as Int
//    }

    fun updateStatusBarColor(context: Activity, color: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = context.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.parseColor(color)
        }
    }

    //컬러 리소스로 변경(예 : R.color.deep_blue)
    fun updateStatusBarColor_string(context: Activity, colorId: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = context.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(context, colorId)
        }
    }

    fun bgColorAnim(view: View, startColor: Any, endColor: Any, duration: Int) {
        val backgroundColorAnimator =
            ObjectAnimator.ofObject(view, "backgroundColor", ArgbEvaluator(), startColor, endColor)
        backgroundColorAnimator.setDuration(duration.toLong())
        backgroundColorAnimator.start()
    }
}