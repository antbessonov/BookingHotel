package org.bessonov.bookinghotel.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.FontRes
import org.bessonov.bookinghotel.R
import org.bessonov.bookinghotel.databinding.BookingPriceItemBinding

class BookingPriceItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: BookingPriceItemBinding

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.booking_price_item, this, true)
        binding = BookingPriceItemBinding.bind(this)
        initializeAttributes(attrs = attrs, defStyleAttr = defStyleAttr, defStyleRes = defStyleRes)
    }

    private fun initializeAttributes(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.BookingPriceItemView,
            defStyleAttr,
            defStyleRes
        )

        val title = typedArray.getString(R.styleable.BookingPriceItemView_title)
        setTitle(title = title)

        val value = typedArray.getString(R.styleable.BookingPriceItemView_value)
        setValue(value = value)

        typedArray.recycle()
    }

    private fun setTitle(title: String?) {
        binding.titleTv.text = title
    }

    fun setValue(value: String?) {
        binding.valueTv.text = value
    }

    fun setColor(@ColorInt colorInt: Int) {
        binding.valueTv.setTextColor(colorInt)
    }

    fun setFontFamily(@FontRes fontFamilyInt: Int) {
        binding.valueTv.typeface = resources.getFont(fontFamilyInt)
    }
}