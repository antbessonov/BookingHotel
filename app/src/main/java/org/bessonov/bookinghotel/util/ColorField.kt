package org.bessonov.bookinghotel.util

import androidx.annotation.ColorRes
import org.bessonov.bookinghotel.R

enum class ColorField(@ColorRes val colorInt: Int) {
    DEFAULT(R.color.md_theme_light_textfield),
    ERROR(R.color.md_theme_light_textfield_error)
}