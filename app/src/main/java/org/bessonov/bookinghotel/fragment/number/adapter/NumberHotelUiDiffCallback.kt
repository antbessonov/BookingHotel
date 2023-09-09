package org.bessonov.bookinghotel.fragment.number.adapter

import androidx.recyclerview.widget.DiffUtil
import org.bessonov.bookinghotel.model.NumberHotelUi

object NumberHotelUiDiffCallback : DiffUtil.ItemCallback<NumberHotelUi>() {

    override fun areItemsTheSame(oldItem: NumberHotelUi, newItem: NumberHotelUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NumberHotelUi, newItem: NumberHotelUi): Boolean {
        return oldItem == newItem
    }
}