
# ViewExtensions.kt 
 ```kotlin 
 /**
 * Visibility modifiers and check functions
 */

fun View.isVisibile(): Boolean = this.visibility == View.VISIBLE 
 
, , , , , , /**
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
 ```