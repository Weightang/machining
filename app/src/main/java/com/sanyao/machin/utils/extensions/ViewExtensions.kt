package com.mapgis.mapsdk.utils.extensions

import android.animation.Animator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.content.res.Resources
import android.text.*
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.mapgis.baselib.R


val Number.dp: Int get() = (toInt() * Resources.getSystem().displayMetrics.density).toInt()

var View.paddingStartCompat: Int
    set(value) {
        setPadding(value, paddingTop, paddingEnd, paddingBottom)
    }
    get() = paddingStart

fun View.addPaddingStart(padding: Int) {
    addPaddingStart(padding)
}

var View.paddingTopCompat: Int
    set(value) {
        setPadding(paddingStart, value, paddingEnd, paddingBottom)
    }
    get() = paddingTop

fun View.addPaddingTop(padding: Int) {
    addPaddingTop(padding)
}

var View.paddingEndCompat: Int
    set(value) {
        setPadding(paddingStart, paddingTop, value, paddingBottom)
    }
    get() = paddingEnd

fun View.addPaddingEnd(padding: Int) {
    addPaddingEnd(padding)
}

var View.paddingBottomCompat: Int
    set(value) {
        setPadding(paddingStart, paddingTop, paddingEnd, value)
    }
    get() = paddingBottom

fun View.addPaddingBottom(padding: Int) {
    addPaddingBottom(padding)
}



fun TextView.tintHighlightText(highlightText: String, rawText: String) {
    text = rawText
    if (text.contains(highlightText, true)) {
        val builder = SpannableStringBuilder()
        val spannableString = SpannableString(text.toString())
        val start = text.indexOf(highlightText, 0, true)
        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorPrimary)),
            start, start + highlightText.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        builder.append(spannableString)
        text = builder
    }
}

fun ViewPager2.setCurrentItem(
    item: Int,
    duration: Long,
    interpolator: TimeInterpolator = AccelerateDecelerateInterpolator(),
    pagePxWidth: Int = width
) {
    val pxToDrag: Int = pagePxWidth * (item - currentItem)
    val animator = ValueAnimator.ofInt(0, pxToDrag)
    var previousValue = 0
    animator.addUpdateListener { valueAnimator ->
        val currentValue = valueAnimator.animatedValue as Int
        val currentPxToDrag = (currentValue - previousValue).toFloat()
        fakeDragBy(-currentPxToDrag)
        previousValue = currentValue
    }
    animator.addListener(object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator?) {
            beginFakeDrag()
        }

        override fun onAnimationEnd(animation: Animator?) {
            endFakeDrag()
        }

        override fun onAnimationCancel(animation: Animator?) {}
        override fun onAnimationRepeat(animation: Animator?) {}
    })
    animator.interpolator = interpolator
    animator.duration = duration
    animator.start()
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun AppCompatEditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}
