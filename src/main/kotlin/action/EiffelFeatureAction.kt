package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import feature.CreateFeatureResult
import feature.createEiffelFeature
import ui.NewFeatureDialog
import util.showErrorBalloon
import util.showWarningBalloon

class EiffelFeatureAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        NewFeatureDialog().run {
            if (!showAndGet()) return

            val project = checkNotNull(event.project) { "Project for '$event' is null!" }
            val currentDirectory = event.getData(LangDataKeys.IDE_VIEW)?.orChooseDirectory

            when (val result = currentDirectory?.createEiffelFeature(project, featureName)) {
                CreateFeatureResult.Created -> Unit
                is CreateFeatureResult.AlreadyExists -> {
                    project.showErrorBalloon(ERROR_TITLE, "Feature '$featureName' already exists.")
                }
                CreateFeatureResult.MissingManifestPackage -> {
                    project.showErrorBalloon(ERROR_TITLE, "AndroidManifest.xml does not contain package name.")
                }
                is CreateFeatureResult.NoRootDirectory -> {
                    project.showErrorBalloon(ERROR_TITLE, "No root directory found for '${result.directoryName}'")
                }
                is CreateFeatureResult.SkippedFiles -> {
                    project.showWarningBalloon(
                        WARNING_TITLE,
                        "Skipped ${result.names.size} already existing file(s): ${result.names.joinToString()}"
                    )
                }
                null -> project.showErrorBalloon(ERROR_TITLE, "Target directory is null.")
            }
        }
    }

    private companion object {
        const val ERROR_TITLE = "Failed creating Eiffel feature"
        const val WARNING_TITLE = "Created incomplete Eiffel feature"
    }
}
