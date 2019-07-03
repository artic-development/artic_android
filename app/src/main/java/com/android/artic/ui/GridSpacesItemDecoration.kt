package com.android.artic.ui

import androidx.recyclerview.widget.RecyclerView
import android.R.attr.right
import android.R.attr.left
import android.content.Context
import android.graphics.Rect
import android.view.View


class GridSpacesItemDecoration(val context : Context, private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = space
        outRect.right = space
        outRect.bottom = space
    }
}