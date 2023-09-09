package org.bessonov.bookinghotel.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import org.bessonov.bookinghotel.R
import org.bessonov.bookinghotel.databinding.BookingInfoItemBinding

class BookingInfoItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: BookingInfoItemBinding

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.booking_info_item, this, true)
        binding = BookingInfoItemBinding.bind(this)
        initializeAttributes(attrs = attrs, defStyleAttr = defStyleAttr, defStyleRes = defStyleRes)
    }

    private fun initializeAttributes(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.BookingInfoItemView,
            defStyleAttr,
            defStyleRes
        )

        val title = typedArray.getString(R.styleable.BookingInfoItemView_title)
        setTitle(title = title)

        val value = typedArray.getString(R.styleable.BookingInfoItemView_value)
        setValue(value = value)

        typedArray.recycle()
    }

    private fun setTitle(title: String?) {
        binding.titleTv.text = title
    }

    fun setValue(value: String?) {
        binding.valueTv.text = value
    }
}