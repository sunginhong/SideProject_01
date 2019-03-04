package com.example.sunginhong.sideproject_01.Utils_Folder

import android.animation.ValueAnimator
import android.view.View
import android.view.animation.*


internal object Utils_Animation {

    fun TransAnim(view:View, startX:Float, endX:Float, startY:Float, endY:Float, duration:Long ) {
        val anim = TranslateAnimation(
            startX, endX,
            startY, endY
        )
        anim.fillAfter = true
        anim.interpolator = DecelerateInterpolator(1.5.toFloat())
        anim.duration = duration
        view.startAnimation(anim)
    }

    fun AlphaAnim(view:View, startAlpha:Float, endAlpha:Float, duration:Long ) {
        val anim = AlphaAnimation(startAlpha, endAlpha)
        anim.fillAfter = true
        anim.interpolator = DecelerateInterpolator(1.5.toFloat())
        anim.duration = duration
        view.startAnimation(anim)
    }

    fun SclaeAnim(view:View, startScaleX:Float, endScaleX:Float, startScaleY:Float,endScaleY:Float, originX:Float, originY:Float, duration:Long) {
        val anim = ScaleAnimation(startScaleX, endScaleX, startScaleY, endScaleY, Animation.RELATIVE_TO_SELF, originX, Animation.RELATIVE_TO_SELF, originY)
        anim.fillAfter = true
        anim.interpolator = DecelerateInterpolator(1.5.toFloat())
        anim.duration = duration
        view.startAnimation(anim)
    }

    fun LayoutHeightAnim(view:View, targetHeight:Int, duration:Long){
        val resizeHeightAnimation =
            Resize_HeightAnimation(view, targetHeight)
        resizeHeightAnimation.fillAfter = true
        resizeHeightAnimation.interpolator = DecelerateInterpolator(1.5.toFloat())
        resizeHeightAnimation.duration = duration
        view.startAnimation(resizeHeightAnimation)
    }

    fun LayoutFitAnim(view:View, targetHeight:Int, targetWidth: Int, duration:Long){
        val resizeFitAnimation =
            Resize_FitAnimation(view, targetHeight, targetWidth)
        resizeFitAnimation.fillAfter = true
        resizeFitAnimation.interpolator = DecelerateInterpolator(1.5.toFloat())
        resizeFitAnimation.duration = duration
        view.startAnimation(resizeFitAnimation)
    }

    fun ValueAnimator(startPos: Float, endPos: Float, duration: Int):Float {
        val animator = ValueAnimator.ofFloat(startPos, endPos)
        var resultN = 0f
        animator.duration = duration.toLong()
        animator.interpolator = DecelerateInterpolator(java.lang.Float.valueOf(1.5.toString()))
        animator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animator: ValueAnimator) {
                resultN = animator.animatedValue as Float
            }
        })

        animator.start()
        return resultN
    }
    fun sum(a: Int, b: Int): Int {
        return a + b
    }

}