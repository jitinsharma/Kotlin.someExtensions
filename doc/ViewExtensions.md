
# ViewExtensions.kt 
 ```kotlin 
 /**
 * Visibility modifiers and check functions
 */

fun View.isVisibile(): Boolean = this.visibility == View.VISIBLE 
 
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
 
/**
 * Sets color to status bar
 */
fun Window.addStatusBarColor(@ColorRes color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        this.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        this.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        this.statusBarColor = ContextCompat.getColor(this.context, color)
    }
} 
 ```