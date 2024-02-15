package com.belkanoid.core.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(
    private val left: Int = 0,
    private val right: Int = 0,
    private val top: Int = 0,
    private val bottom: Int = 0,
    private val bottomAfterFirstItem: Int = 0,
) : RecyclerView.ItemDecoration() {


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        if(parent.getChildAdapterPosition(view) == 0){
            outRect.bottom = bottomAfterFirstItem
            return
        }

        outRect.left = left
        outRect.top = top
        outRect.right = right
        outRect.bottom = bottom
    }

}