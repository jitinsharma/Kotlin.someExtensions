package `in`.jitinsharma.kotlin.some.extensions.views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import androidx.annotation.StyleableRes
import androidx.core.content.ContextCompat

/**
 * Extension to get Vector Drawable from TypedArray which internally doesn't support
 * inflating Vector Drawables. Can be used for normal drawables as well.
 */
fun TypedArray.getVectorDrawable(context: Context, @StyleableRes id: Int): Drawable? {
    val resource = getResourceId(id, 0)
    if (resource != 0) {
        return ContextCompat.getDrawable(context, resource)
    }
    return null
}
