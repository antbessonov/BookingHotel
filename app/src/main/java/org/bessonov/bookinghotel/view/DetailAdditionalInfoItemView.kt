package org.bessonov.bookinghotel.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import org.bessonov.bookinghotel.R
import org.bessonov.bookinghotel.databinding.DetailAdditionalInfoItemBinding

class DetailAdditionalInfoItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: DetailAdditionalInfoItemBinding

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.detail_additional_info_item, this, true)
        binding = DetailAdditionalInfoItemBinding.bind(this)
        initializeAttributes(attrs = attrs, defStyleAttr = defStyleAttr, defStyleRes = defStyleRes)
    }

    private fun initializeAttributes(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.DetailAdditionalInfoItemView,
            defStyleAttr,
            defStyleRes
        )

        val icon = typedArray.getDrawable(R.styleable.DetailAdditionalInfoItemView_icon)
        setIcon(icon = icon)

        val title = typedArray.getString(R.styleable.DetailAdditionalInfoItemView_title)
        setTitle(title = title)

        typedArray.recycle()
    }

    private fun setIcon(icon: Drawable?) {
        binding.iconIv.setImageDrawable(icon)
    }

    private fun setTitle(title: String?) {
        binding.titleTv.text = title
    }
}