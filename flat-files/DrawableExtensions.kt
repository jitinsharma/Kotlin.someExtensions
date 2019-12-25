package `in`.jitinsharma.kotlin.some.extensions.drawable

import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.DrawableCompat

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
