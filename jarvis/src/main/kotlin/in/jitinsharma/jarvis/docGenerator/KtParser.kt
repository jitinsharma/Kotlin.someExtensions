package `in`.jitinsharma.jarvis.docGenerator

import org.jetbrains.kotlin.psi.*

data class Variable(
        var name: String,
        var type: String
)

fun KtFile.getImports(): List<String> {
    val imports = mutableListOf<String>()
    importList?.imports?.forEach {
        imports.add(it.text)
    }
    return imports
}

fun KtFile.getClassList(): List<KtClass> {
    val classList = mutableListOf<KtClass>()
    children.forEach {
        if (it is KtClass) classList.add(it)
    }
    children.forEach { psiElement ->
        when(psiElement) {
            is KtClass -> {}
            is KtNamedFunction -> {}
            is KtFunction -> {}
        }
    }
    return classList
}

fun KtFile.getFunctionList(): List<KtNamedFunction> {
    val functionList = mutableListOf<KtNamedFunction>()
    children.forEach {
        if (it is KtNamedFunction) functionList.add(it)
    }
    return functionList
}

fun KtClass.getFunctionList(): List<KtNamedFunction> {
    val functionList = mutableListOf<KtNamedFunction>()
    declarations.forEach {
        if (it is KtNamedFunction) functionList.add(it)
    }
    return functionList
}

fun KtClass.getGlobalVariables(): List<Variable> {
    val globalVariableList = mutableListOf<Variable>()
    declarations.forEach { ktDeclaration ->
        if (ktDeclaration is KtProperty) {
            ktDeclaration.children.forEach { child ->
                if (child is KtTypeReference) {
                    globalVariableList.add(
                        Variable(
                            name = ktDeclaration.name.toString(),
                            type = child.text.replace("?", "").replace("!!", "")
                        )
                    )
                }
            }
        }
    }
    return globalVariableList
}

fun KtNamedFunction.getAssignmentStatements(): List<String> {
    val statements = mutableListOf<String>()
    this.children.forEach { child ->
        if (child is KtBlockExpression) {
            child.statements.forEach { statement ->
                if (statement.text.contains(" = ")) {
                    statements.add(statement.text)
                }
            }
        }
    }
    return statements
}
