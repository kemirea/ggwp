package com.kemikalreaktion.ggwp.data

data class Character (
    val name: String,
    val otherNames: List<String> = emptyList(),
    val url: String? = null,
    val images: List<String> = emptyList(),
    val frameData: List<FrameData> = emptyList(),
)