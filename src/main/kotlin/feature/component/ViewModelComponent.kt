package feature.component

import feature.FileType
import feature.CreateFileResult
import feature.createEiffelFile
import util.createOrGetSubdirectory

val viewModelComponent: Component = { templateManager, properties, _, featureName ->
    createEiffelComponent { skippedFiles ->
        createOrGetSubdirectory("viewmodel").run {
            createEiffelFile(FileType.ViewModel(featureName), templateManager, properties).let {
                if (it is CreateFileResult.AlreadyExists) skippedFiles.add(it.name)
            }
        }
    }
}
