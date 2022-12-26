package com.omolleapaza.yapetechnicaltest.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RecipeResponse(
    @SerialName("Message") val msg: String?,
    @SerialName("Data") val data: List<RecipeDto>?
)