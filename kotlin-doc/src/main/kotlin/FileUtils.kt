import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.function.BiPredicate
import kotlin.streams.toList

fun findFiles(folder: File, endsWith: String): List<File> {
    try {
        Files.find(Paths.get(folder.absolutePath), 10, BiPredicate { path, _ ->
            path.fileName.toString().endsWith(endsWith)
        }).use { stream ->
            return stream.map { path ->
                path.toFile()
            }.toList()
        }
    } catch (e: IOException) {
        e.printStackTrace()
        return emptyList()
    }
}