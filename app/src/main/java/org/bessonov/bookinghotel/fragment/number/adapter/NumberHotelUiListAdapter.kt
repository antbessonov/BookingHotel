package org.bessonov.bookinghotel.fragment.number.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.bessonov.bookinghotel.R
import org.bessonov.bookinghotel.databinding.NumberHotelItemBinding
import org.bessonov.bookinghotel.util.adapter.image.ImageListAdapter
import org.bessonov.bookinghotel.util.adapter.peculiarity.PeculiarityListAdapter
import org.bessonov.bookinghotel.model.NumberHotelUi
import org.bessonov.bookinghotel.util.setDots
import javax.inject.Inject


class NumberHotelUiListAdapter @Inject constructor() :
    ListAdapter<NumberHotelUi, NumberHotelUiViewHolder>(NumberHotelUiDiffCallback) {

    var onItemClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NumberHotelUiViewHolder {
        return NumberHotelUiViewHolder(parent = parent, onItemClick = onItemClick)
    }

    override fun onBindViewHolder(holder: NumberHotelUiViewHolder, position: Int) {
        val numberHotel = getItem(position)
        setImageList(binding = holder.binding, numberHotel = numberHotel)
        setName(binding = holder.binding, numberHotel = numberHotel)
        setPeculiarityList(binding = holder.binding, numberHotel = numberHotel)
        setPrice(binding = holder.binding, numberHotel = numberHotel)
    }

    private fun setImageList(binding: NumberHotelItemBinding, numberHotel: NumberHotelUi) {
        (binding.imageListVp.adapter as ImageListAdapter).submitList(numberHotel.imageList)
        binding.imageListVp.setDots(imageDotsLayout = binding.imageDots)
    }

    private fun setName(binding: NumberHotelItemBinding, numberHotel: NumberHotelUi) {
        binding.nameTv.text = numberHotel.name
    }

    private fun setPeculiarityList(binding: NumberHotelItemBinding, numberHotel: NumberHotelUi) {
        (binding.peculiarityListRv.adapter as PeculiarityListAdapter).submitList(numberHotel.peculiarityList)
    }

    private fun setPrice(binding: NumberHotelItemBinding, numberHotel: NumberHotelUi) {
        binding.valuePriceTv.text = binding.root.context.getString(
            R.string.price_with_symbol,
            numberHotel.price.value
        )
        binding.descriptionPriceTv.text = numberHotel.price.description
    }
}