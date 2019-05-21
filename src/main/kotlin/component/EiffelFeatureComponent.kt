package component

import com.intellij.openapi.components.AbstractProjectComponent
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project
import feature.FeatureConfig

@State(name = "EiffelFeatureComponent", storages = [Storage(value = "eiffelFeatureComponent.xml")])
class EiffelFeatureComponent private constructor(
    project: Project? = null
) : AbstractProjectComponent(project), PersistentStateComponent<FeatureConfig> {

    var config = FeatureConfig()
        private set

    override fun getState() = config

    override fun loadState(state: FeatureConfig) {
        config = state
    }

    companion object {

        fun instance(project: Project): EiffelFeatureComponent =
            project.getComponent(EiffelFeatureComponent::class.java)
    }
}
