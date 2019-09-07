
# ActivityExtensions.kt 
 ```kotlin 
 /**
 * AppCompatActivity's toolbar visibility modifiers
 */

fun AppCompatActivity.hideToolbar() {
    supportActionBar?.hide()
} 
 
/**
 * Returns display density as ...DPI
 */
fun AppCompatActivity.getDisplayDensity(): String {
    val metrics = DisplayMetrics()
    this.windowManager.defaultDisplay.getMetrics(metrics)
    return when (metrics.densityDpi) {
        DisplayMetrics.DENSITY_LOW -> "LDPI"
        DisplayMetrics.DENSITY_MEDIUM -> "MDPI"
        DisplayMetrics.DENSITY_HIGH -> "HDPI"
        DisplayMetrics.DENSITY_XHIGH -> "XHDPI"
        DisplayMetrics.DENSITY_XXHIGH -> "XXHDPI"
        DisplayMetrics.DENSITY_XXXHIGH -> "XXXHDPI"
        else -> "XXHDPI"
    }
} 
 
/**
 * Sets color to toolbar in AppCompatActivity
 */
fun AppCompatActivity.setToolbarColor(@ColorRes color: Int) {
    this.supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this,
            color)))
} 
 
/**
 * Perform replace for a support fragment
 */
inline fun AppCompatActivity.transact(action: FragmentTransaction.() -> Unit) {
    supportFragmentManager.beginTransaction().apply {
        action()
    }.commit()
} 
 ```