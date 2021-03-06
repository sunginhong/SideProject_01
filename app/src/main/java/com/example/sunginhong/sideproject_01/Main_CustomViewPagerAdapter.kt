package com.example.sunginhong.sideproject_01

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.support.v4.util.Pools
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.*
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.sunginhong.sideproject_01.MainActivity.Companion.mScroller
import com.example.sunginhong.sideproject_01.Utils_Folder.Utils_Animation
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.util.*
import android.util.Pair as UtilPair







class Main_CustomViewPagerAdapter(val context: Context, val userList:ArrayList<Main_User_min>) :
    PagerAdapter(), View.OnClickListener, View.OnTouchListener {

    internal var items: MutableList<String> = ArrayList()

    var ctx = context
    private val mInflater: LayoutInflater
    private val mMyViewPool: Pools.SimplePool<View>
    private val vpSize = 0.8f
    private var itemDeatilHeight = 0f

    var item_vp_main_cardview_detailrl_Array = arrayOfNulls<View>(5)
    var item_vp_main_cardview_detailBtn_Array = arrayOfNulls<View>(5)

    companion object {
        private var dragState = false
        private var dragViewChange = false
        private var touchStartX: Float = 0.toFloat()
        private var touchStartY: Float = 0.toFloat()
        private var touchMoveX: Float = 0.toFloat()
        private var touchMoveY: Float = 0.toFloat()
        private var lastAction: Int = 0
        var detailBtnClick = false
        private val MAX_POOL_SIZE = 10
        var item_vp_main_cardview_rl_Array = arrayOfNulls<View>(5)

        @JvmStatic fun viewPosReset(currentIndex : Int) {
            cardPositionReset(currentIndex)
        }

        private fun cardPositionReset(currentIndex : Int) {
            for (i in item_vp_main_cardview_rl_Array.indices) {
                if (currentIndex == i){
                    item_vp_main_cardview_rl_Array[i]!!.y = 0f
                }
            }
        }
    }

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

    @SuppressLint("ClickableViewAccessibility")
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
        val item_vp_main_cardview_detailbutton = view.findViewById(R.id.item_vp_main_cardview_detailbutton) as Button

        item_vp_main_cardview_rl.id = position
        item_vp_main_cardview_rl_Array[position] = item_vp_main_cardview_rl
        item_vp_main_cardview_rl_main.id = position
        item_vp_main_cardview_rl_main.setOnClickListener(this)
        item_vp_main_cardview_rl_main.setOnTouchListener(this)
        item_vp_main_cardview_detailBtn_Array[position] = item_vp_main_cardview_detailbutton

        item_vp_main_cardview_detailbutton.setOnClickListener {
            if(item_vp_main_cardview_detailbutton.alpha == 2.0f){
                detailBtnClick = true
                infoAnim_depth2(view)
            } else {
                infoAnim_depth1(view!!, true)
            }
        }

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

    override fun onClick(view: View) {
        val clickDetailView = item_vp_main_cardview_detailrl_Array[view.id]
        val Button = item_vp_main_cardview_detailBtn_Array[view!!.id]

        mScroller.isAccessible = true
        mScroller.set(MainActivity.mainVp, CustomScroller(context, DecelerateInterpolator(1.5.toFloat()),400))
        MainActivity.mainVp!!.currentItem = view.id

        if (Button!!.alpha == 0.0f|| Button!!.alpha == 2.0f){
            infoAnim_depth1(view!!, true)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        var action = event!!.action
        var dragView = view
        val cardView = item_vp_main_cardview_rl_Array[view!!.id]
        val innerView = item_vp_main_cardview_detailrl_Array[view!!.id]
        val Button = item_vp_main_cardview_detailBtn_Array[view!!.id]
        val DRAGMOVE_RATE = 7.5f
//        disallowTouch(dragView!!.parent, true)
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                handler.removeCallbacks(runnable)
                touchStartX = cardView!!.x - event.rawX
                touchStartY = cardView!!.y - event.rawY
                lastAction = MotionEvent.ACTION_DOWN
            }
            MotionEvent.ACTION_MOVE ->{
                handler.removeCallbacks(runnable)
                dragState = true
                touchMoveX = event.rawX + touchStartX
                touchMoveY = event.rawY + touchStartY
//                dragView!!.x = touchMoveX
                cardView!!.y = touchMoveY
//                Log.d("ssss", "ssss"+touchMoveY)
                lastAction = MotionEvent.ACTION_MOVE
                if (-cardView!!.y > cardView.height/DRAGMOVE_RATE){
                    cardView.y = -(cardView.height/DRAGMOVE_RATE).toFloat()
                    touchMoveY = cardView.y
                }
                if (cardView!!.y > cardView.height/DRAGMOVE_RATE){
                    cardView.y = (cardView.height/DRAGMOVE_RATE).toFloat()
                    touchMoveY = cardView.y
                }
            }
            MotionEvent.ACTION_UP ->{
                lastAction = MotionEvent.ACTION_UP
                if (dragState){
                    if ( -cardView!!.y >= cardView!!.height/DRAGMOVE_RATE ){
                        infoAnim_depth1(dragView!!, true)
                        if (Button!!.alpha == 2.0f){ infoAnim_depth2(dragView) }
                    } else if (-cardView!!.y >= 0){
                        if (Button!!.alpha == 0.0f){
                            infoAnim_depth1(dragView!!, false)
                        } else {
                            infoAnim_depth1(dragView!!, true)
                        }
                    }
                    if ( cardView!!.y >= cardView.height/DRAGMOVE_RATE ){
                        infoAnim_depth1(dragView!!, true)
                        if (Button!!.alpha == 2.0f){ infoAnim_depth2(dragView) }
                    } else if (cardView!!.y >= 0){
                        if (Button!!.alpha == 0.0f){
                            infoAnim_depth1(dragView!!, false)
                        } else {
                            infoAnim_depth1(dragView!!, true)
                        }
                    }
                    cardView.y = 0f
                    Utils_Animation.TransAnim(cardView, 0f, 0f, touchMoveY, 0f, 400)
                    handler.postDelayed(runnable, 100)
                }
            }
        }
        return false
    }

    private fun disallowTouch(parent: ViewParent?, isDisallow: Boolean) {
        parent?.requestDisallowInterceptTouchEvent(isDisallow)
    }

    private val handler = Handler(Looper.getMainLooper())
    private val runnable = Runnable { dragState = false }

    @SuppressLint("Range")
    private fun infoAnim_depth1(view: View, boolean: Boolean){
        val View = item_vp_main_cardview_detailrl_Array[view!!.id]
        val Button = item_vp_main_cardview_detailBtn_Array[view!!.id]

        if (Button!!.alpha == 0f && boolean){
            Utils_Animation.TransAnim(View!!, 0f, 0f, 0f, itemDeatilHeight, 400)
            Button!!.alpha = 1f
            Handler().postDelayed({
                View!!.y = itemDeatilHeight
                Button!!.alpha = 2f
                Utils_Animation.TransAnim(View!!, 0f, 0f, 0f, 0f, 0)
            },400)
        }
        if (View!!.y == itemDeatilHeight && boolean){
            View!!.y = 0f
            Utils_Animation.TransAnim(View!!, 0f, 0f, itemDeatilHeight, 0f, 400)
            Handler().postDelayed({
                Utils_Animation.TransAnim(View!!, 0f, 0f, 0f, 0f, 0)
                Button!!.alpha = 0f
            },400)
        }
    }

    @SuppressLint("Range")
    private fun infoAnim_depth2(view: View) {
        val ViewRl = item_vp_main_cardview_rl_Array[view!!.id]
        val ViewDetailrl = item_vp_main_cardview_detailrl_Array[view!!.id]
        val Button = item_vp_main_cardview_detailBtn_Array[view!!.id]

        if (!detailBtnClick){
            Utils_Animation.TransAnim(ViewDetailrl!!, 0f, 0f, itemDeatilHeight, itemDeatilHeight, 0)
        } else {
            Utils_Animation.TransAnim(ViewDetailrl!!, 0f, 0f, 0f, 0f, 0)
        }

        Button!!.alpha = 1f
        Handler().postDelayed({
            ViewDetailrl!!.y = itemDeatilHeight
            Button!!.alpha = 2f
            Utils_Animation.TransAnim(ViewDetailrl!!, 0f, 0f, 0f, 0f, 0)
        },400)

//        val intent = Intent(view.context, Main_DetailActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        view.context.startActivity(intent)

//        val options = ActivityOptions.makeSceneTransitionAnimation(
//            this,
//            UtilPair.create(ViewRl, "card_detail_layout"))

//        val intent = Intent(view.context, Main_DetailActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        view.context.startActivity(intent, options.toBundle())

//        val intent = Intent(view.context, Main_DetailActivity::class.java)
//        val p1 = android.util.Pair.create(ViewRl, "card_detail_layout")
//        val options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.mainActivity, p1)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        view.context.startActivity(intent, options.toBundle())

        val intent = Intent(view.context, Main_DetailActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        view.context.startActivity(intent)

    }


}