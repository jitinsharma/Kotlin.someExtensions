package io.github.jitinsharma.kotlinsomeextensions

import android.graphics.drawable.Drawable
import android.support.v4.graphics.drawable.DrawableCompat

/**
 * Returns a compat drawable with tint added
 */
fun Drawable.withTint(colorInt: Int): Drawable {
    return with(this) {
        DrawableCompat.wrap(this).apply {
            DrawableCompat.setTint(this, colorInt)
        }
    }
}
