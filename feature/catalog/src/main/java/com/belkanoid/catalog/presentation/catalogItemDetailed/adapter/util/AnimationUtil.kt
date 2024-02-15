package com.belkanoid.catalog.presentation.catalogItemDetailed.adapter.util

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import androidx.core.animation.doOnEnd

fun View.slideDownWith(view: View, useAlpha: Boolean = true, doOnStart: () -> Unit = {visibility = View.VISIBLE}) {
    view.translationY = -this.height.toFloat()
    val set1 = AnimatorSet().also {
        it.play(ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, 0f).apply {
            duration = 500
            doOnStart()
        })
    }

    val set2 = AnimatorSet().also {
        it.play(ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, 0f).setDuration(500)) }

    val set3 = AnimatorSet().also {
        if (useAlpha)
            it.play(ObjectAnimator.ofFloat(this, View.ALPHA, 0f, 0.5f, 1f).setDuration(500))
    }

    AnimatorSet().also {
        it.playTogether(set1, set2, set3)
    }.start()
}

fun View.slideUpWith(view: View, height: Float = this.height.toFloat(), useAlpha: Boolean = true, doOnEnd: () -> Unit = {visibility = View.GONE}) {
    val tranY = view.translationY
    val set1 = AnimatorSet().also {
        it.play(ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, -height).apply {
            duration = 500
            doOnEnd { doOnEnd() }
        })
    }

    val set2 = AnimatorSet().also {
        it.play(ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, -height).apply {
            duration = 500
            doOnEnd { view.translationY = tranY }
        })
    }

    val set3 = AnimatorSet().also {
        if (useAlpha)
            it.play(ObjectAnimator.ofFloat(this, View.ALPHA, 1f, 0.5f, 0f).setDuration(500))
    }

    AnimatorSet().also {
        it.playTogether(set1, set2, set3)
    }.start()
}