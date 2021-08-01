package com.kemikalreaktion.ggwp.data

data class FrameData(
    val character: String,
    val input: String,
    val damage: Int,
    val guard: Guard,
    val startup: Int,
    val active: Int,
    val recovery: Int,
    val onBlock: Int,
    val onHit: Int,
    val url: String? = null,
    val name: String? = null,
    val images: List<String> = emptyList(),
) {
    enum class Guard {
        LOW,
        HIGH,
        ALL,
    }
}