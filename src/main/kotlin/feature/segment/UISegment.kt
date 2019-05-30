package feature.segment

import component.EiffelFeatureState
import feature.CreateFileResult
import feature.FileType
import feature.createEiffelFile
import util.createOrGetSubdirectory

val uiSegment: Segment = { templateManager, properties, rootDirectory, config ->
    createEiffelSegment { skippedFiles ->
        createOrGetSubdirectory("ui").run {
            val fileType = when (config.viewType) {
                EiffelFeatureState.ViewType.ACTIVITY -> FileType.Activity(config)
                EiffelFeatureState.ViewType.FRAGMENT -> FileType.Fragment(config)
            }
            createEiffelFile(fileType, templateManager, properties).let {
                if (it is CreateFileResult.AlreadyExists) skippedFiles.add(it.name)
            }

        }
        rootDirectory.createOrGetSubdirectory("res").createOrGetSubdirectory("layout").run {
            createEiffelFile(FileType.Layout(config), templateManager, properties).let {
                if (it is CreateFileResult.AlreadyExists) skippedFiles.add(it.name)
            }
        }
    }
}
