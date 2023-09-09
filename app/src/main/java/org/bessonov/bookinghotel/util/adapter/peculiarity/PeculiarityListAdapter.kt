package org.bessonov.bookinghotel.util.adapter.peculiarity

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import javax.inject.Inject

class PeculiarityListAdapter @Inject constructor() :
    ListAdapter<String, PeculiarityViewHolder>(PeculiarityDiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PeculiarityViewHolder {
        return PeculiarityViewHolder(parent = parent)
    }

    override fun onBindViewHolder(holder: PeculiarityViewHolder, position: Int) {
        val peculiarity = getItem(position)
        holder.binding.valueTv.text = peculiarity
    }
}