package org.bessonov.bookinghotel.fragment.booking.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.bessonov.bookinghotel.R
import org.bessonov.bookinghotel.databinding.BookingPriceBinding
import org.bessonov.bookinghotel.model.BookingPriceUi

class BookingPriceViewHolder(val parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.booking_price,
        parent,
        false
    )
) {

    val binding = BookingPriceBinding.bind(itemView)

    fun onBind(bookingPrice: BookingPriceUi) {
        binding.tourPrice.setValue(
            itemView.resources.getString(
                R.string.price_with_symbol,
                bookingPrice.tourPrice
            )
        )
        binding.fuelCharge.setValue(
            itemView.resources.getString(
                R.string.price_with_symbol,
                bookingPrice.fuelCharge
            )
        )
        binding.serviceCharge.setValue(
            itemView.resources.getString(
                R.string.price_with_symbol,
                bookingPrice.serviceCharge
            )
        )
        binding.totalPrice.setValue(
            itemView.resources.getString(
                R.string.price_with_symbol,
                bookingPrice.totalPrice
            )
        )
        binding.totalPrice.setColor(
            itemView.resources.getColor(
                R.color.md_theme_light_total_price,
                null
            )
        )
        binding.totalPrice.setFontFamily(R.font.sf_pro_display_semibold)
    }
}