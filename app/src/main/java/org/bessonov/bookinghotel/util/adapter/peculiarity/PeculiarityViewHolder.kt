package org.bessonov.bookinghotel.util.adapter.peculiarity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.bessonov.bookinghotel.R
import org.bessonov.bookinghotel.databinding.PeculiarityItemBinding

class PeculiarityViewHolder(val parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.peculiarity_item,
        parent,
        false
    )
) {
    val binding = PeculiarityItemBinding.bind(itemView)
}