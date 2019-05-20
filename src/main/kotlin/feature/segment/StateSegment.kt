package feature.segment

import feature.CreateFileResult
import feature.FileType
import feature.createEiffelFile
import util.createOrGetSubdirectory

val stateSegment: Segment = { templateManager, properties, _, featureName ->
    createEiffelSegment { skippedFiles ->
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
