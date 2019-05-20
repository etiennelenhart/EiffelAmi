package feature.segment

import feature.FileType
import feature.CreateFileResult
import feature.createEiffelFile
import util.createOrGetSubdirectory

val viewModelSegment: Segment = { templateManager, properties, _, featureName ->
    createEiffelSegment { skippedFiles ->
        createOrGetSubdirectory("viewmodel").run {
            createEiffelFile(FileType.ViewModel(featureName), templateManager, properties).let {
                if (it is CreateFileResult.AlreadyExists) skippedFiles.add(it.name)
            }
        }
    }
}
