package io.github.jitinsharma.kotlinsomeextensions

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView

/**
 * Visibility modifiers and check functions
 */

fun View.isVisibile(): Boolean = this.visibility == View.VISIBLE

fun View.isGone(): Boolean = this.visibility == View.GONE

fun View.isInvisible(): Boolean = this.visibility == View.INVISIBLE

fun View.makeVisible() { this.visibility = View.VISIBLE }

fun View.makeGone() { this.visibility = View.GONE }

fun View.makeInvisible() { this.visibility = View.INVISIBLE }

/**
 * Sets text and content description using same string
 */
fun TextView.setTextWithContentDescription(value : String?) {
    text = value
    contentDescription = value
}

/**
 * Button enabling/disabling modifiers
 */

fun Button.disableButton() {
    isEnabled = false
    alpha = 0.3f
}

fun Button.enableButton() {
    isEnabled = true
    alpha = 1.0f
}

/**
 * Add Color tint to drawable image
 */
fun Drawable.addTintWithCompat(colorInt: Int): Drawable {
    var drawable = this
    drawable = DrawableCompat.wrap(drawable)
    DrawableCompat.setTint(drawable.mutate(), colorInt)
    return drawable
}

/**
 * Sets color to status bar
 */
fun Window.setStatusBarColor(@ColorRes color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        this.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        this.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        this.statusBarColor = ContextCompat.getColor(this.context, color)
    }
}