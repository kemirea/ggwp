package com.kemikalreaktion.ggwp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FrameData(
    @SerialName("chara")
    val character: String,
    @SerialName("input")
    val input: String,
    @SerialName("damage")
    val damage: String,
    @SerialName("guard")
    val guard: String,
    @SerialName("startup")
    val startup: String,
    @SerialName("active")
    val active: String,
    @SerialName("recovery")
    val recovery: String,
    @SerialName("onBlock")
    val onBlock: String,
    @SerialName("onHit")
    val onHit: String,
    val url: String? = null,
    @SerialName("name")
    val name: String,
    @SerialName("images")
    val images: List<String> = emptyList(),
)
