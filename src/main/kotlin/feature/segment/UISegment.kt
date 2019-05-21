package feature.segment

import feature.CreateFileResult
import feature.FileType
import feature.createEiffelFile
import util.createOrGetSubdirectory

val uiSegment: Segment = { templateManager, properties, rootDirectory, config ->
    createEiffelSegment { skippedFiles ->
        createOrGetSubdirectory("ui").run {
            createEiffelFile(FileType.Fragment(config), templateManager, properties).let {
                if (it is CreateFileResult.AlreadyExists) skippedFiles.add(it.name)
            }
        }
        rootDirectory.createOrGetSubdirectory("res").createOrGetSubdirectory("layout").run {
            createEiffelFile(FileType.FragmentLayout(config), templateManager, properties).let {
                if (it is CreateFileResult.AlreadyExists) skippedFiles.add(it.name)
            }
        }
    }
}
