package com.example.porschehistory.recycler

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HistoryItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val itemPosition = parent.getChildAdapterPosition(view)

        val itemWidth = view.layoutParams.width
        val offset = (parent.width - itemWidth) / 2
        Log.d("ItemOffsetLog", "${parent.width}")
        Log.d("ItemOffsetLog", "$itemWidth")
        Log.d("ItemOffsetLog", "$offset")

        if (itemPosition == 0) {
            outRect.left = offset
        } else if (itemPosition == state.itemCount - 1) {
            outRect.right = offset
        }
    }
}