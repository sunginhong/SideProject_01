package com.example.sunginhong.sideproject_01.Utils_Folder

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation

class Resize_FitAnimation(var view: View, val targetHeight: Int, val  targetWidth: Int) :
    Animation() {
    val startHeight = view.height
    val startWidth = view.width

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        val newHeight = ( startHeight + (targetHeight - startHeight) * interpolatedTime).toInt()
        val newWidth = ( startWidth + (targetWidth - startWidth) * interpolatedTime).toInt()
        view.getLayoutParams().height = newHeight
        view.getLayoutParams().width = newWidth
        view.requestLayout()
    }

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
    }

    override fun willChangeBounds(): Boolean {
        return true
    }
}

class Resize_HeightAnimation(var view: View, val targetHeight: Int) :
    Animation() {
    val startHeight = view.height

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        val newHeight = ( startHeight + (targetHeight - startHeight) * interpolatedTime).toInt()
        view.getLayoutParams().height = newHeight
        view.requestLayout()
    }

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
    }

    override fun willChangeBounds(): Boolean {
        return true
    }
}