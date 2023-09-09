package org.bessonov.bookinghotel.util.adapter.image

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception
import javax.inject.Inject

class ImageListAdapter @Inject constructor() :
    ListAdapter<String, ImageViewHolder>(ImageDiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageViewHolder {
        return ImageViewHolder(parent = parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = getItem(position)
        Picasso
            .get()
            .load(image)
            .fit()
            .centerCrop()
            .into(holder.binding.imageIv, object : Callback {
                override fun onSuccess() {
                    holder.binding.loadingProgress.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    holder.binding.loadingProgress.visibility = View.GONE
                    holder.binding.errorTv.visibility = View.VISIBLE
                }

            })
    }
}