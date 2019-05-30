package component

import com.intellij.openapi.components.AbstractProjectComponent
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project
import feature.FeatureConfig
import feature.asConfig

@State(name = "EiffelFeatureComponent", storages = [Storage(value = "eiffelFeatureState.xml")])
class EiffelFeatureComponent private constructor(
    project: Project? = null
) : AbstractProjectComponent(project), PersistentStateComponent<EiffelFeatureState> {

    val config: FeatureConfig
        get() = state.asConfig()

    private var state = EiffelFeatureState()

    override fun getState() = state

    override fun loadState(state: EiffelFeatureState) {
        this.state = state
    }

    companion object {

        fun instance(project: Project): EiffelFeatureComponent =
            project.getComponent(EiffelFeatureComponent::class.java)
    }
}

data class EiffelFeatureState(
    @Transient var name: String = "",
    var addInterceptions: Boolean = false,
    var generateFactory: Boolean = false,
    var viewType: ViewType = ViewType.ACTIVITY
) {

    enum class ViewType { ACTIVITY, FRAGMENT }
}
