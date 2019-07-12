package com.articrew.artic.util

import android.graphics.Outline
import android.os.Build
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.annotation.RequiresApi

/**
 * if you call this function, then the image view only has top round.
 * @param curveRadius how round
 * @author greedy0110
 * */
fun ImageView.setTopRound(curveRadius: Float) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        outlineProvider = object : ViewOutlineProvider() {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun getOutline(view: View?, outline: Outline?) {
                outline?.setRoundRect(0, 0, view!!.width, (view.height + curveRadius).toInt(), curveRadius)
            }
        }
        clipToOutline = true

    }
}

/**
 * if you call this function, then the image view only has top round.
 * @param curveRadius how round
 * @author greedy0110
 * */
fun ImageView.setLeftRound(curveRadius: Float) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        outlineProvider = object : ViewOutlineProvider() {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun getOutline(view: View?, outline: Outline?) {
                outline?.setRoundRect(0, 0, (view!!.width + curveRadius).toInt() , view.height, curveRadius)
            }
        }
        clipToOutline = true

    }
}

/**
 * if you call this function, then the image view only has top round.
 * @param curveRadius how round
 * @author greedy0110
 * */
fun ImageView.setRightRound(curveRadius: Float) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        outlineProvider = object : ViewOutlineProvider() {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun getOutline(view: View?, outline: Outline?) {
                outline?.setRoundRect(-curveRadius.toInt(), 0, view!!.width, view.height, curveRadius)
            }
        }
        clipToOutline = true

    }
}