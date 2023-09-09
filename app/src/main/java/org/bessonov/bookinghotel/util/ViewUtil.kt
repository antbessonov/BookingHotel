package org.bessonov.bookinghotel.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.view.View
import com.google.android.material.textfield.TextInputLayout


const val longAnimationDuration = 500L

fun View.fadeIn(startDelay: Long = 0) {
    if (visibility == View.GONE) {
        alpha = 0f
        visibility = View.VISIBLE
        animate()
            .alpha(1.0f)
            .setDuration(longAnimationDuration)
            .setStartDelay(startDelay)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    alpha = 1.0f
                }
            })
    }
}

fun TextInputLayout.changeColorBg(colorTo: Int) {
    ObjectAnimator.ofObject(this, "boxBackgroundColor", ArgbEvaluator(), colorTo)
        .setDuration(longAnimationDuration)
        .start()
}