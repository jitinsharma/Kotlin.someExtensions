package `in`.jitinsharma.kotlin.some.extensions.views

import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.use

/**
 * Visibility modifiers and check functions
 */

fun View.isVisibile(): Boolean = this.visibility == View.VISIBLE

fun View.isGone(): Boolean = this.visibility == View.GONE

fun View.isInvisible(): Boolean = this.visibility == View.INVISIBLE

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

/**
 * Returns typedArray from Styleable given defStyleAttr and defStyleRes can be defaulted to 0.
 * Also will call recycle() on typeArray once operations are complete.
 */
inline fun View.readAttributes(
    attrs: AttributeSet?,
    styleableArray: IntArray,
    block: (TypedArray) -> Unit
) {
    val typedArray = context.theme.obtainStyledAttributes(attrs, styleableArray, 0, 0)
    typedArray.use(block)  // From androidx.core
}