package feature

import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.fileTemplates.FileTemplateUtil
import com.intellij.psi.PsiDirectory
import java.util.*

fun PsiDirectory.createEiffelFile(
    type: FileType,
    templateManager: FileTemplateManager,
    properties: Properties
): CreateFileResult {
    val template = templateManager.getInternalTemplate(type.templateName)
    return try {
        FileTemplateUtil.createFromTemplate(template, type.fileName, properties, this)
        CreateFileResult.Created
    } catch (e: Exception) {
        CreateFileResult.AlreadyExists(type.fileName)
    }
}

sealed class CreateFileResult {

    object Created : CreateFileResult()
    data class AlreadyExists(val name: String) : CreateFileResult()
}
