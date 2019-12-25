package `in`.jitinsharma.jarvis.docGenerator

import `in`.jitinsharma.jarvis.utils.asString
import `in`.jitinsharma.jarvis.utils.currentPath
import `in`.jitinsharma.jarvis.utils.getProjectKtFiles
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.com.intellij.openapi.util.Disposer
import org.jetbrains.kotlin.com.intellij.psi.PsiManager
import org.jetbrains.kotlin.com.intellij.testFramework.LightVirtualFile
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.idea.KotlinFileType
import org.jetbrains.kotlin.psi.KtFile
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

fun generateDocs() {
    println("Generating docs")
    val files = getProjectKtFiles()
    files.forEach { file ->
        val codeString = file.asString()
        val ktFile = createKtFile(codeString, file.name)
        val functions = ktFile.getFunctionList()
        functions.joinToString { ktFunction ->
            val doc = ktFunction.docComment
            doc?.let {
                val functionDocText = ktFunction.text
                return@joinToString "$functionDocText \n \n"
            } ?: return@joinToString ""
        }.takeIf { functionDocTexts ->
            functionDocTexts.isNotEmpty()
        }.also {
            val doc = "\n# ${file.name} \n ```kotlin \n ${it?.trimEnd()} \n ```"
            val readMePath = "$currentPath/doc/${file.name.replace(".kt", "")}.md"
            Files.write(
                Paths.get(readMePath),
                doc.toByteArray(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
            )
        }
    }
}

fun createKtFile(codeString: String, fileName: String) =
    PsiManager.getInstance(project)
        .findFile(
            LightVirtualFile(fileName, KotlinFileType.INSTANCE, codeString)
        ) as KtFile

private val project by lazy {
    KotlinCoreEnvironment.createForProduction(
        Disposer.newDisposable(),
        CompilerConfiguration(),
        EnvironmentConfigFiles.JVM_CONFIG_FILES
    ).project
}