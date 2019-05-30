package feature

import com.fleshgrinder.extensions.kotlin.toLowerSnakeCase
import component.EiffelFeatureState

sealed class FileType {

    abstract val templateName: String
    abstract val fileName: String

    protected fun ktFileName(featureName: String, suffix: String) = "${featureName.capitalize()}$suffix.kt"

    protected fun xmlFileName(prefix: String, featureName: String) =
        "${prefix}_${featureName.toLowerSnakeCase()}.xml"

    class Action(config: FeatureConfig) : FileType() {
        override val templateName = "action.kt"
        override val fileName = ktFileName(config.name, "Action")
    }

    class State(config: FeatureConfig) : FileType() {
        override val templateName = "state.kt"
        override val fileName = ktFileName(config.name, "State")
    }

    class ViewModel(config: FeatureConfig) : FileType() {
        override val templateName = "viewmodel.kt"
        override val fileName = ktFileName(config.name, "ViewModel")
    }

    class Factory(config: FeatureConfig) : FileType() {
        override val templateName = "factory.kt"
        override val fileName = ktFileName(config.name, "Factory")
    }

    class Activity(config: FeatureConfig) : FileType() {
        override val templateName = "activity.kt"
        override val fileName = ktFileName(config.name, "Activity")
    }

    class Fragment(config: FeatureConfig) : FileType() {
        override val templateName = "fragment.kt"
        override val fileName = ktFileName(config.name, "Fragment")
    }

    class Layout(config: FeatureConfig) : FileType() {
        override val templateName = "layout.xml"
        override val fileName = xmlFileName(
            when (config.viewType) {
                EiffelFeatureState.ViewType.ACTIVITY -> "activity"
                EiffelFeatureState.ViewType.FRAGMENT -> "fragment"
            },
            config.name
        )
    }
}
