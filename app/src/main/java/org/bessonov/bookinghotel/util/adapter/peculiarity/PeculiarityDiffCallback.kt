package org.bessonov.bookinghotel.util.adapter.peculiarity

import androidx.recyclerview.widget.DiffUtil

object PeculiarityDiffCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}