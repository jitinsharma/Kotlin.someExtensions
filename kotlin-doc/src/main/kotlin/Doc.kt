import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.com.intellij.openapi.util.Disposer
import org.jetbrains.kotlin.com.intellij.psi.PsiManager
import org.jetbrains.kotlin.com.intellij.testFramework.LightVirtualFile
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.idea.KotlinFileType
import org.jetbrains.kotlin.psi.KtFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

private val project by lazy {
    KotlinCoreEnvironment.createForProduction(
            Disposer.newDisposable(),
            CompilerConfiguration(),
            EnvironmentConfigFiles.JVM_CONFIG_FILES
    ).project

}

fun main() {
    val currentPath = System.getProperty("user.dir")
    val filesPath = currentPath.replace("kotlin-doc", "")
    val files = findFiles(File(filesPath), ".kt")
    files.forEach { file ->
        val codeString = file.asString()
        val ktFile = codeString.toKtFile()
        val functions = ktFile.getFunctionList()
        var functionDocTexts = ""
        functions.forEach { ktFunction ->
            val doc = ktFunction.docComment
            val functionDocText = ktFunction.text
            doc?.let {
                if (functionDocText.isNotEmpty()) {
                    functionDocTexts += "$functionDocText \n \n"
                }
            }
        }
        if (functionDocTexts.isNotEmpty()) {
            val doc = "\n# ${file.name} \n ```kotlin \n ${functionDocTexts.trimEnd()} \n ```"
            val readMePath = currentPath.replace("kotlin-doc", "README.md")
            Files.write(Paths.get(readMePath), doc.toByteArray(), StandardOpenOption.APPEND)
        }
    }
}

fun String.toKtFile() =
        PsiManager.getInstance(project).findFile(LightVirtualFile("a.kt", KotlinFileType.INSTANCE, this)) as KtFile

