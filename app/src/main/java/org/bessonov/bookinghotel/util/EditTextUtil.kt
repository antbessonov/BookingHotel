package org.bessonov.bookinghotel.util

import android.text.TextUtils
import android.util.Patterns

fun isValidEmail(value: CharSequence): Boolean {
    return !TextUtils.isEmpty(value) && Patterns.EMAIL_ADDRESS.matcher(value).matches()
}

fun isValidPhone(value: CharSequence): Boolean {
    val pattern = "\\*+".toRegex()
    return !pattern.containsMatchIn(value)
}