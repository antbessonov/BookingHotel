package org.bessonov.bookinghotel.util

import android.content.res.Resources
import android.util.TypedValue

val Number.toPx get() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    Resources.getSystem().displayMetrics)

fun Int.getNightRuAddition(): String {
    val preLastDigit = this % 100 / 10
    return if (preLastDigit == 1) {
        String.format("%d ночей", this)
    } else when (this % 10) {
        1 -> String.format("%d ночь", this)
        2, 3, 4 -> String.format("%d ночи", this)
        else -> String.format("%d ночей", this)
    }
}