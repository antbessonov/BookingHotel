package org.bessonov.bookinghotel.fragment.booking.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.bessonov.bookinghotel.R
import org.bessonov.bookinghotel.databinding.MainInfoHotelBinding
import org.bessonov.bookinghotel.domain.model.MainInfoHotel
import org.bessonov.bookinghotel.model.RatingUi

class MainInfoHotelViewHolder(val parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.main_info_hotel,
        parent,
        false
    )
) {

    val binding = MainInfoHotelBinding.bind(itemView)

    fun onBind(rating: RatingUi, mainInfoHotel: MainInfoHotel) {
        binding.mainInfoHotel.setRating(
            rating = itemView.resources.getString(
                R.string.rating_value_with_description,
                rating.value,
                rating.description
            )
        )
        binding.mainInfoHotel.setName(name = mainInfoHotel.name)
        binding.mainInfoHotel.setAddress(address = mainInfoHotel.address)
    }
}