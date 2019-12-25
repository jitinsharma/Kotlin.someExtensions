
# ImageExtensions.kt 
 ```kotlin 
 /**
 * Convert byte array to bitmap
 */
fun ByteArray.convertBytesToBitmap(): Bitmap =
    BitmapFactory.decodeByteArray(this, 0, size) 
 
, /**
 * Convert bitmap to a byte array
 */
fun Bitmap.convertBitmapToBytes(): ByteArray {
    val stream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.PNG, 0, stream)
    return stream.toByteArray()
} 
 
, /**
 * Make ImageView image GrayScale
 */
fun ImageView.makeGrayscale() {
    val matrix = ColorMatrix()
    matrix.setSaturation(0f)
    colorFilter = ColorMatrixColorFilter(matrix)
} 
 ```