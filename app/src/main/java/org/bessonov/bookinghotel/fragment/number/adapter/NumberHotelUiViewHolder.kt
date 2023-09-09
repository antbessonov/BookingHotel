package org.bessonov.bookinghotel.fragment.number.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.bessonov.bookinghotel.R
import org.bessonov.bookinghotel.util.adapter.image.ImageListAdapter
import org.bessonov.bookinghotel.util.adapter.peculiarity.PeculiarityListAdapter
import org.bessonov.bookinghotel.databinding.NumberHotelItemBinding
import org.bessonov.bookinghotel.util.ZoomOutPageTransformer
import org.bessonov.bookinghotel.util.removeOverScroll

class NumberHotelUiViewHolder(
    val parent: ViewGroup,
    private val onItemClick: ((Int) -> Unit)?
) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.number_hotel_item,
            parent,
            false
        )
    ) {
    val binding = NumberHotelItemBinding.bind(itemView)

    init {

        setPeculiarityListAdapter()
        setImageListAdapter()
        setItemClickListener()
    }

    private fun setPeculiarityListAdapter() {
        val peculiarityListAdapter = PeculiarityListAdapter()
        binding.peculiarityListRv.adapter = peculiarityListAdapter
    }

    private fun setImageListAdapter() {
        val imageListAdapter = ImageListAdapter()
        val pageTransformer = ZoomOutPageTransformer()
        binding.imageListVp.adapter = imageListAdapter
        binding.imageListVp.setPageTransformer(pageTransformer)
        binding.imageListVp.removeOverScroll()
    }

    private fun setItemClickListener() {
        binding.selectNumberBtn.setOnClickListener {
            onItemClick?.invoke(adapterPosition)
        }
    }
}