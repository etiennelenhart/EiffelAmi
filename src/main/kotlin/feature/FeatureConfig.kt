package feature

import component.EiffelFeatureState

data class FeatureConfig(
    val name: String = "",
    val addInterceptions: Boolean = false,
    val generateFactory: Boolean = false,
    val viewType: EiffelFeatureState.ViewType = EiffelFeatureState.ViewType.ACTIVITY
)

fun EiffelFeatureState.asConfig() = FeatureConfig(
    name = name,
    addInterceptions = addInterceptions,
    generateFactory = addInterceptions || generateFactory,
    viewType = viewType
)
