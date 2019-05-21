package feature.segment

import feature.CreateFileResult
import feature.FileType
import feature.createEiffelFile
import util.createOrGetSubdirectory

val stateSegment: Segment = { templateManager, properties, _, config ->
    createEiffelSegment { skippedFiles ->
        createOrGetSubdirectory("state").run {
            createEiffelFile(FileType.Action(config), templateManager, properties).let {
                if (it is CreateFileResult.AlreadyExists) skippedFiles.add(it.name)
            }
            createEiffelFile(FileType.State(config), templateManager, properties).let {
                if (it is CreateFileResult.AlreadyExists) skippedFiles.add(it.name)
            }
        }
    }
}
