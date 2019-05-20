package feature.segment

import feature.CreateFileResult
import feature.FileType
import feature.createEiffelFile
import util.createOrGetSubdirectory

val uiSegment: Segment = { templateManager, properties, rootDirectory, featureName ->
    createEiffelSegment { skippedFiles ->
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
