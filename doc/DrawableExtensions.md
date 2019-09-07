
# DrawableExtensions.kt 
 ```kotlin 
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
 ```