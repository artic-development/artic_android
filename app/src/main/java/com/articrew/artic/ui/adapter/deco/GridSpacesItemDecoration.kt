package com.articrew.artic.ui.adapter.deco

import androidx.recyclerview.widget.RecyclerView
import android.R.attr.right
import android.R.attr.left
import android.content.Context
import android.graphics.Rect
import android.view.View


class GridSpacesItemDecoration(val context : Context, private val horizontalSpace : Int, private val verticalSpace : Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = horizontalSpace
        outRect.bottom = verticalSpace
    }
}