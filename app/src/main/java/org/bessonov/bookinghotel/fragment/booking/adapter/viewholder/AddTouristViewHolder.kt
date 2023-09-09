package org.bessonov.bookinghotel.fragment.booking.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.bessonov.bookinghotel.R
import org.bessonov.bookinghotel.databinding.AddTouristBinding

class AddTouristViewHolder(
    val parent: ViewGroup,
    private val onAddTouristClick: (() -> Unit)?
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.add_tourist,
        parent,
        false
    )
) {
    val binding = AddTouristBinding.bind(itemView)

    init {

        setAddTouristClickListener()
    }

    private fun setAddTouristClickListener() {
        binding.addTouristTitleBtn.setOnClickListener {
            onAddTouristClick?.invoke()
        }
    }

}