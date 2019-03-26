package feature

import com.fleshgrinder.extensions.kotlin.toLowerSnakeCase

sealed class FileType {

    abstract val templateName: String
    abstract val fileName: String

    protected fun ktFileName(featureName: String, suffix: String) = "${featureName.capitalize()}$suffix.kt"

    protected fun xmlFileName(prefix: String, featureName: String) =
        "${prefix}_${featureName.toLowerSnakeCase()}.xml"

    class Action(featureName: String) : FileType() {
        override val templateName = "action.kt"
        override val fileName = ktFileName(featureName, "Action")
    }

    class State(featureName: String) : FileType() {
        override val templateName = "state.kt"
        override val fileName = ktFileName(featureName, "State")
    }

    class ViewModel(featureName: String) : FileType() {
        override val templateName = "viewmodel.kt"
        override val fileName = ktFileName(featureName, "ViewModel")
    }

    class Fragment(featureName: String) : FileType() {
        override val templateName = "fragment.kt"
        override val fileName = ktFileName(featureName, "Fragment")
    }

    class FragmentLayout(featureName: String) : FileType() {
        override val templateName = "fragmentlayout.xml"
        override val fileName = xmlFileName("fragment", featureName)
    }
}
