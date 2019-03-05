package com.example.sunginhong.sideproject_01

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout



class Main_CustomDragView : RelativeLayout {

    companion object {

    }

    private var RelativeLayoutListener: RelativeLayout? = null
    private var currentlyTouching = false
    private var currentlyScrolling = false
    private val MOVE_Y = 0f
    private val MOVE_X = 0f

    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}


}