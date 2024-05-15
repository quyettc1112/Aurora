package com.aurora.aurora.Until

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager

class NonScrollableGridLayoutManager(context: Context, spanCount: Int) : GridLayoutManager(context, spanCount) {
    override fun canScrollVertically(): Boolean {
        // Luôn trả về false để ngăn không cho RecyclerView cuộn dọc
        return false
    }
}