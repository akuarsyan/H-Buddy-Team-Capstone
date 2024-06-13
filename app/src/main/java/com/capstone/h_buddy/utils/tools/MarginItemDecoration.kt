package com.capstone.h_buddy.utils.tools

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(private val verticalMargin: Int, private val horizontalMargin: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        parent.layoutManager?.let {
            val orientation = if (it.canScrollVertically()) RecyclerView.VERTICAL else RecyclerView.HORIZONTAL
            if (orientation == RecyclerView.VERTICAL) {
                outRect.bottom = verticalMargin
            } else {
                outRect.right = horizontalMargin
            }
        }
    }
}