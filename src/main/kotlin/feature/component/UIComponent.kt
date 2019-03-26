package feature.component

import feature.FileType
import feature.CreateFileResult
import feature.createEiffelFile
import util.createOrGetSubdirectory

val uiComponent: Component = { templateManager, properties, rootDirectory, featureName ->
    createEiffelComponent { skippedFiles ->
        createOrGetSubdirectory("ui").run {
            createEiffelFile(FileType.Fragment(featureName), templateManager, properties).let {
                if (it is CreateFileResult.AlreadyExists) skippedFiles.add(it.name)
            }
        }
        rootDirectory.createOrGetSubdirectory("res").createOrGetSubdirectory("layout").run {
            createEiffelFile(FileType.FragmentLayout(featureName), templateManager, properties).let {
                if (it is CreateFileResult.AlreadyExists) skippedFiles.add(it.name)
            }
        }
    }
}
