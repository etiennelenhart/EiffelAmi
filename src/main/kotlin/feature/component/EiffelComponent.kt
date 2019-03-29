package feature.component

import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.psi.PsiDirectory
import java.util.*

typealias Component = PsiDirectory.(
    manager: FileTemplateManager,
    properties: Properties,
    rootDirectory: PsiDirectory,
    featureName: String
) -> CreateComponentResult

fun createEiffelComponent(create: (skippedFiles: MutableList<String>) -> Unit): CreateComponentResult {
    val skippedFiles = mutableListOf<String>()
    create(skippedFiles)
    return if (skippedFiles.isNotEmpty()) {
        CreateComponentResult.SkippedFiles(skippedFiles)
    } else {
        CreateComponentResult.Created
    }
}

sealed class CreateComponentResult {

    object Created : CreateComponentResult()
    data class SkippedFiles(val names: List<String>) : CreateComponentResult()
}
