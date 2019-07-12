package com.articrew.artic.ui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.viewpager.widget.ViewPager

// Thanks to https://wimir-dev.tistory.com/56 , https://mobikul.com/viewpager/
/**
 * it enable wrap_content viewpager!
 * */
class HeightWrappingViewPager : ViewPager {
    private var currentView: View? = null
    private var swipeEnabled =  true

    constructor(context: Context): super(context) {
        initPageChangeListener()
    }
    constructor(context: Context, attrs: AttributeSet?): super(context, attrs){
        initPageChangeListener()
    }

    private fun initPageChangeListener() {
        addOnPageChangeListener(object : SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                requestLayout()
            }
        })
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        try {

            if (this.swipeEnabled) {

                //				Log.i("INFO", "스크롤 중..");

                return super.onTouchEvent(event)

            }

        } catch (e: Exception) {

//            val exceptionAsStrting = sw.toString()

            Log.e("INFO", "exception")

        }






        return false
    }

    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        if (this.swipeEnabled) {
            return super.onInterceptTouchEvent(event)

        }

        return false
    }

    public fun setPagingDisabled() { //이 메소드를 이용해서 스크롤을 막아줍니다.

        this.swipeEnabled = false;

    }

    public fun setPagingEnabled() { //이 메소드를 이용해서 스크롤을 막아줍니다.

        this.swipeEnabled = true;

    }


    // Thanks to https://buptfarmer.wordpress.com/2016/05/27/wrappingviewpager-support-different-height-of-fragments/
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

//        var heightMeasureSpec = heightMeasureSpec
//        val mode = MeasureSpec.getMode(heightMeasureSpec)
//        // Unspecified means that the ViewPager is in a ScrollView WRAP_CONTENT.
//        // At Most means that the ViewPager is not in a ScrollView WRAP_CONTENT.
//        if (mode == MeasureSpec.UNSPECIFIED || mode == MeasureSpec.AT_MOST) {
//            // super has to be called in the beginning so the child views can be initialized.
//            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//            var height = 0
//            for (i in 0 until childCount) {
//                val child = getChildAt(i)
//                child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))
//                val h = child.measuredHeight
//                if (h > height) height = h
//            }
//            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
//        }
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (currentView == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            return
        }
        // currentView 의 height에 맞게 설정한다.
        currentView?.let {cur ->
            cur.measure(widthMeasureSpec, MeasureSpec.UNSPECIFIED)
            val height = cur.measuredHeight
            val hms =  MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)

            super.onMeasure(widthMeasureSpec, hms)
        }
    }

    fun measureCurrentView(current: View) {
        currentView = current
        requestLayout()
    }
}