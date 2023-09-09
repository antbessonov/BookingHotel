package org.bessonov.bookinghotel.fragment.booking.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.bessonov.bookinghotel.R
import org.bessonov.bookinghotel.databinding.BookingInfoBinding
import org.bessonov.bookinghotel.model.BookingInfoUi

class BookingInfoViewHolder(val parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.booking_info,
        parent,
        false
    )
) {

    val binding = BookingInfoBinding.bind(itemView)

    fun onBind(bookingInfo: BookingInfoUi) {
        binding.departure.setValue(bookingInfo.transfer.departure)
        binding.arrivalWithCountry.setValue(bookingInfo.transfer.arrival)
        binding.dates.setValue(
            itemView.resources.getString(
                R.string.dates_sample,
                bookingInfo.dateTour.start,
                bookingInfo.dateTour.stop
            )
        )
        binding.numberNights.setValue(bookingInfo.numberNights)
        binding.nameHotelTv.setValue(bookingInfo.mainInfoHotel.name)
        binding.numberHotel.setValue(bookingInfo.numberHotel)
        binding.nutrition.setValue(bookingInfo.nutrition)
    }
}