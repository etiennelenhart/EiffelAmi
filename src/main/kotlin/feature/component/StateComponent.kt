package feature.component

import feature.FileType
import feature.CreateFileResult
import feature.createEiffelFile
import util.createOrGetSubdirectory

val stateComponent: Component = { templateManager, properties, _, featureName ->
    createEiffelComponent { skippedFiles ->
        createOrGetSubdirectory("state").run {
            createEiffelFile(FileType.Action(featureName), templateManager, properties).let {
                if (it is CreateFileResult.AlreadyExists) skippedFiles.add(it.name)
            }
            createEiffelFile(FileType.State(featureName), templateManager, properties).let {
                if (it is CreateFileResult.AlreadyExists) skippedFiles.add(it.name)
            }
        }
    }
}
