package feature.segment

import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.psi.PsiDirectory
import java.util.*

typealias Segment = PsiDirectory.(
    manager: FileTemplateManager,
    properties: Properties,
    rootDirectory: PsiDirectory,
    featureName: String
) -> CreateSegmentResult

fun createEiffelSegment(create: (skippedFiles: MutableList<String>) -> Unit): CreateSegmentResult {
    val skippedFiles = mutableListOf<String>()
    create(skippedFiles)
    return if (skippedFiles.isNotEmpty()) {
        CreateSegmentResult.SkippedFiles(skippedFiles)
    } else {
        CreateSegmentResult.Created
    }
}

sealed class CreateSegmentResult {

    object Created : CreateSegmentResult()
    data class SkippedFiles(val names: List<String>) : CreateSegmentResult()
}
