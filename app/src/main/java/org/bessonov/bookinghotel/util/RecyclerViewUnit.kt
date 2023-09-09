package org.bessonov.bookinghotel.util

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.scrollToPosition(
    position: Int,
    onScrollFinished: () -> Unit,
) {
    val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(
            recyclerView: RecyclerView,
            newState: Int
        ) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                onScrollFinished()
                recyclerView.removeOnScrollListener(this)
            }

        }
    }
    this.removeOnScrollListener(onScrollListener)
    this.addOnScrollListener(onScrollListener)
    this.smoothScrollToPosition(position)
}