package feature

import com.fleshgrinder.extensions.kotlin.toLowerSnakeCase

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

    class Fragment(config: FeatureConfig) : FileType() {
        override val templateName = "fragment.kt"
        override val fileName = ktFileName(config.name, "Fragment")
    }

    class FragmentLayout(config: FeatureConfig) : FileType() {
        override val templateName = "fragmentlayout.xml"
        override val fileName = xmlFileName("fragment", config.name)
    }
}
