import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtNamedFunction
import java.io.File
import java.io.IOException
import java.util.*

@Throws(IOException::class)
fun File.asString(): String {
    val fileContents = StringBuilder(this.length().toInt())
    Scanner(this).use { scanner ->
        while (scanner.hasNextLine()) {
            fileContents.append(scanner.nextLine() + System.lineSeparator())
        }
        return fileContents.toString()
    }
}

fun KtFile.getFunctionList(): List<KtNamedFunction> {
    val functionList = mutableListOf<KtNamedFunction>()
    children.forEach {
        if (it is KtNamedFunction) functionList.add(it)
    }
    return functionList
}