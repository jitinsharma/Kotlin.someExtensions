package `in`.jitinsharma.kotlin.some.extensions.activity

import android.graphics.drawable.ColorDrawable
import android.util.DisplayMetrics
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction

/**
 * AppCompatActivity's toolbar visibility modifiers
 */

fun AppCompatActivity.hideToolbar() {
    supportActionBar?.hide()
}

fun AppCompatActivity.showToolbar() {
    supportActionBar?.show()
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
    this.supportActionBar?.setBackgroundDrawable(
        ColorDrawable(
            ContextCompat.getColor(this, color)
        )
    )
}

/**
 * Perform replace for a support fragment
 */
inline fun AppCompatActivity.transact(action: FragmentTransaction.() -> Unit) {
    supportFragmentManager.beginTransaction().apply {
        action()
    }.commit()
}
