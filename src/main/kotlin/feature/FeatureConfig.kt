package feature

data class FeatureConfig(@Transient var name: String = "", var generateFactory: Boolean = false)
