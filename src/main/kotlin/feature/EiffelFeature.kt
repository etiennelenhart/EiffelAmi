package feature

import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.projectView.ProjectView
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ProjectFileIndex
import com.intellij.psi.JavaDirectoryService
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiManager
import feature.component.CreateComponentResult
import feature.component.stateComponent
import feature.component.uiComponent
import feature.component.viewModelComponent
import util.createOrGetSubdirectory
import util.manifestPackage
import java.util.*

fun PsiDirectory.createEiffelFeature(project: Project, featureName: String): CreateFeatureResult {
    val rootDirectory = ProjectFileIndex.SERVICE.getInstance(project).getSourceRootForFile(virtualFile)?.let {
        PsiManager.getInstance(project).findDirectory(it)?.parent
    }
    val featureNameLowerCase = featureName.toLowerCase()

    if (findSubdirectory(featureNameLowerCase) != null) {
        return CreateFeatureResult.AlreadyExists
    }
    return if (rootDirectory != null) {
        val manifestPackage = rootDirectory.manifestPackage() ?: return CreateFeatureResult.MissingManifestPackage
        val skippedFiles = mutableListOf<String>()

        WriteCommandAction.runWriteCommandAction(project) {
            val featureDirectory = createOrGetSubdirectory(featureNameLowerCase)
            val templateManager = FileTemplateManager.getInstance(project)
            val featurePackage = JavaDirectoryService.getInstance().getPackage(featureDirectory)?.qualifiedName
            val properties = Properties(templateManager.defaultProperties).apply {
                setProperty("MODULE_PACKAGE", manifestPackage)
                setProperty("FEATURE_PACKAGE", featurePackage)
                setProperty("FEATURE_NAME_LOWERCASE", featureNameLowerCase)
                setProperty("FEATURE_NAME_CAPITALIZED", featureName.capitalize())
            }

            featureDirectory.run {
                for (component in listOf(stateComponent, viewModelComponent, uiComponent)) {
                    component(templateManager, properties, rootDirectory, featureName).let {
                        if (it is CreateComponentResult.SkippedFiles) skippedFiles.addAll(it.names)
                    }
                }
            }
            ProjectView.getInstance(project).refresh()
        }

        if (skippedFiles.isNotEmpty()) CreateFeatureResult.SkippedFiles(skippedFiles) else CreateFeatureResult.Created
    } else {
        CreateFeatureResult.NoRootDirectory(name)
    }
}

sealed class CreateFeatureResult {

    object Created : CreateFeatureResult()
    object AlreadyExists : CreateFeatureResult()
    object MissingManifestPackage : CreateFeatureResult()
    data class NoRootDirectory(val directoryName: String) : CreateFeatureResult()
    data class SkippedFiles(val names: List<String>) : CreateFeatureResult()
}
