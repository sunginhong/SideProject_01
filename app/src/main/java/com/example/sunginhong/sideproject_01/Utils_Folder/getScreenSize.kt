package com.example.sunginhong.sideproject_01.Utils_Folder

import android.app.Activity
import android.graphics.Point

fun getScreenSize(context: Activity): Point {
    val display = context.windowManager.defaultDisplay
    val size = Point()

    if (android.os.Build.VERSION.SDK_INT >= 13) {
        display.getSize(size)
    } else {

        size.set(display.width, display.height)
    }

    return size
}