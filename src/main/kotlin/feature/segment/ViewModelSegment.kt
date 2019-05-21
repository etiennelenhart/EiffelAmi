package feature.segment

import feature.CreateFileResult
import feature.FileType
import feature.createEiffelFile
import util.createOrGetSubdirectory

val viewModelSegment: Segment = { templateManager, properties, _, config ->
    createEiffelSegment { skippedFiles ->
        createOrGetSubdirectory("viewmodel").run {
            createEiffelFile(FileType.ViewModel(config), templateManager, properties).let {
                if (it is CreateFileResult.AlreadyExists) skippedFiles.add(it.name)
            }
            if (config.generateFactory) {
                createEiffelFile(FileType.Factory(config), templateManager, properties).let {
                    if (it is CreateFileResult.AlreadyExists) skippedFiles.add(it.name)
                }
            }
        }
    }
}
