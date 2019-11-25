package com.articrew.artic.ui.adapter.deco

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class GridSpacesItemDecoration(val context : Context, private val horizontalSpace : Int, private val verticalSpace : Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = horizontalSpace
        outRect.bottom = verticalSpace
    }
}