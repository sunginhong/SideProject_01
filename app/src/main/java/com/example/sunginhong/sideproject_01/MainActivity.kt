package com.example.sunginhong.sideproject_01

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.example.sunginhong.sideproject_01.Utils_Folder.getScreenSize
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private  val vpPaddingRate = 6
    companion object {
        var mainActivity : MainActivity? = null
        var mainVp: ViewPager? = null
        var mScroller = ViewPager::class.java.getDeclaredField("mScroller")
        var Vp_CardView_Array = arrayOfNulls<ImageView>(0)
        var vpHeight = 0
        var mainVpBool = false
        var screenHeight = 0
        var screenWidth = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ctx = context() ?: return
        init(ctx)
    }

    fun context(): Context {
        return applicationContext
    }

    fun init (ctx: Context) {
        mainActivity = this
        val screenSize = getScreenSize(this)
        screenWidth = screenSize.x
        screenHeight = screenSize.y

        Handler().postDelayed({
            vpHeight = vp.height
        }, 15)
        Handler().postDelayed({
            Vp_CardView_Array = arrayOfNulls<ImageView>(UserList_Min.size)
            mainVp = vp
            vp.adapter = Main_CustomViewPagerAdapter(ctx, UserList_Min)
            vp.clipToPadding = false
            vp.offscreenPageLimit = UserList_Min.size
            vp.currentItem = 0
            tab_layout.setupWithViewPager(vp, true)
            vp.setPadding(vp.width/vpPaddingRate, 0, vp.width/(vpPaddingRate*4), 0)
            vp.pageMargin = vp.width/(vpPaddingRate*4)
            vpHeight = vp.height

        }, 100)
    }

}
