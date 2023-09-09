package org.bessonov.bookinghotel.util.adapter.image

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.bessonov.bookinghotel.R
import org.bessonov.bookinghotel.databinding.ImageItemBinding

class ImageViewHolder(val parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.image_item,
        parent,
        false
    )
) {
    val binding = ImageItemBinding.bind(itemView)
}