package feature

import component.EiffelFeatureState

data class FeatureConfig(
    var name: String = "",
    var addInterceptions: Boolean = false,
    var generateFactory: Boolean = false
)

fun EiffelFeatureState.asConfig() = FeatureConfig(
    name = name,
    addInterceptions = addInterceptions,
    generateFactory = addInterceptions || generateFactory
)
