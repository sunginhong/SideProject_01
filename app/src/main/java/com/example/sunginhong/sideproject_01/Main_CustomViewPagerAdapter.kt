package com.example.sunginhong.sideproject_01

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.support.v4.util.Pools
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.sunginhong.sideproject_01.MainActivity.Companion.mScroller
import com.example.sunginhong.sideproject_01.Utils_Folder.Utils_Animation
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class Main_CustomViewPagerAdapter(val context: Context, val userList:ArrayList<Main_User_min>) :
    PagerAdapter(), View.OnClickListener {
    internal var items: MutableList<String> = ArrayList()

    var ctx = context
    private val mInflater: LayoutInflater
    private val mMyViewPool: Pools.SimplePool<View>
    private val vpSize = 0.8f
    private var itemDeatilHeight = 0f
    var item_vp_main_cardview_detailrl_Array = arrayOfNulls<View>(100)

    init {
        mInflater = LayoutInflater.from(context)
        mMyViewPool = Pools.SynchronizedPool(MAX_POOL_SIZE)
    }

    fun add(item: String) {
        items.add(item)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return userList.size
    }

    override fun getPageWidth(position: Int): Float {
        return vpSize
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view: View? = null
        view = mInflater.inflate(R.layout.item_vp_main_cardview, null)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rootView = inflater.inflate(R.layout.item_vp_main_cardview, container, false)

        val item_vp_main_cardview_rl = view.findViewById(R.id.item_vp_main_cardview_rl) as RelativeLayout
        val item_vp_main_cardview_rl_main = view.findViewById(R.id.item_vp_main_cardview_rl_main) as RelativeLayout
        val item_vp_main_cardview_title = view.findViewById(R.id.item_vp_main_cardview_title) as TextView
        val item_vp_main_cardview_date = view.findViewById(R.id.item_vp_main_cardview_date) as TextView
        val item_vp_main_cardview_thumb = view.findViewById(R.id.item_vp_main_cardview_thumb) as ImageView

        val item_vp_main_cardview_detailrl = view.findViewById(R.id.item_vp_main_cardview_detailview) as View
        val item_vp_main_cardview_detailtitle = view.findViewById(R.id.item_vp_main_cardview_detailtitle) as TextView

        item_vp_main_cardview_rl.id = position
        item_vp_main_cardview_rl.setOnClickListener(this)

        item_vp_main_cardview_title.text = userList[position].title
        item_vp_main_cardview_date.text = userList[position].date
        Picasso.get()
            .load(userList[position].imgThumb_Url)
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
            .into(item_vp_main_cardview_thumb)
        item_vp_main_cardview_rl_main.setBackgroundColor(Color.parseColor(userList[position].color))

        item_vp_main_cardview_detailrl.id = position
        Handler().postDelayed({ itemDeatilHeight = item_vp_main_cardview_detailrl.height.toFloat() },0)
        item_vp_main_cardview_detailrl_Array[position] = item_vp_main_cardview_detailrl
        item_vp_main_cardview_detailtitle.text = "This letter was first introduced in the United Kingdom and has been good luck to the recipient every year, and this letter to you now has to leave you within four days. Copying is also recommended. It may be a black superstition, but it is true. In the UK, a man named HGXWCH received this letter in 1930. He told the secretary to copy and send it. ."

        (container as ViewPager).addView(view, position)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        (container as ViewPager).removeView(view as ViewGroup)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    companion object {
        private val MAX_POOL_SIZE = 10
    }

    override fun onClick(view: View) {
        val clickDetailView = item_vp_main_cardview_detailrl_Array[view.id]

        mScroller.setAccessible(true)
        mScroller.set(MainActivity.mainVp, CustomScroller(context, DecelerateInterpolator(1.5.toFloat()),400))
        MainActivity.mainVp!!.currentItem = view.id

        if (clickDetailView!!.y == 0.0f){
            Utils_Animation.TransAnim(clickDetailView!!, 0f, 0f, 0f, itemDeatilHeight, 400)

            Handler().postDelayed({ clickDetailView!!.y = 1.0f },0)
        }
        if (clickDetailView!!.y == 1.0f){
            Utils_Animation.TransAnim(clickDetailView!!, 0f, 0f, itemDeatilHeight, 0f, 400)

            Handler().postDelayed({ clickDetailView!!.y = 0.0f },0)
        }
    }
}