package org.bessonov.bookinghotel.util

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import org.bessonov.bookinghotel.R
import org.bessonov.bookinghotel.util.adapter.image.ImageListAdapter

fun ViewPager2.removeOverScroll() {
    (getChildAt(0) as? RecyclerView)?.overScrollMode = View.OVER_SCROLL_NEVER
}

fun ViewPager2.setDots(imageDotsLayout: LinearLayout) {
    val dotsCount = (this.adapter as ImageListAdapter).currentList.size
    val dots: MutableList<ImageView> = mutableListOf()
    for (i in 0 until dotsCount) {
        dots.add(ImageView(context))
        dots[i].setImageDrawable(
            ContextCompat.getDrawable(
                context,
                R.drawable.dot_default
            )
        )
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        params.setMargins(0, 0, 5.toPx.toInt(), 0)
        imageDotsLayout.addView(dots[i], params)
    }
    dots[0].setImageDrawable(
        ContextCompat.getDrawable(
            context,
            R.drawable.dot_selected
        )
    )
    this.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            for (i in 0 until dotsCount) {
                dots[i].setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.dot_default
                    )
                )
                dots[position].setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.dot_selected
                    )
                )
            }
        }
    })
}