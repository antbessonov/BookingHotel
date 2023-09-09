package org.bessonov.bookinghotel.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import org.bessonov.bookinghotel.R
import org.bessonov.bookinghotel.databinding.MainInfoHotelItemBinding

class MainInfoHotelItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: MainInfoHotelItemBinding

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.main_info_hotel_item, this, true)
        binding = MainInfoHotelItemBinding.bind(this)
        initializeAttributes(attrs = attrs, defStyleAttr = defStyleAttr, defStyleRes = defStyleRes)
    }

    private fun initializeAttributes(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.MainInfoHotelItemView,
            defStyleAttr,
            defStyleRes
        )

        val rating = typedArray.getString(R.styleable.MainInfoHotelItemView_rating)
        setRating(rating = rating)

        val name = typedArray.getString(R.styleable.MainInfoHotelItemView_name)
        setName(name = name)

        val address = typedArray.getString(R.styleable.MainInfoHotelItemView_address)
        setAddress(address = address)

        typedArray.recycle()
    }

    fun setRating(rating: String?) {
        binding.ratingTv.text = rating
    }

    fun setName(name: String?) {
        binding.nameTv.text = name
    }

    fun setAddress(address: String?) {
        binding.addressTv.text = address
    }


}