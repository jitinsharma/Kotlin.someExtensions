package `in`.jitinsharma.jarvis

import `in`.jitinsharma.jarvis.docGenerator.generateDocs
import `in`.jitinsharma.jarvis.utils.currentPath
import `in`.jitinsharma.jarvis.utils.getProjectKtFiles
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

fun main() {
    generateDocs()
    copyFiles()
}

fun copyFiles() {
    getProjectKtFiles().forEach { file ->
        Files.copy(
            file.toPath(),
            Paths.get("$currentPath/flat-files/${file.name}"),
            StandardCopyOption.REPLACE_EXISTING
        )
    }
}
