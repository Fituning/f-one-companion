package com.utbm.sy43.f_one_companion.data.model.serializable_model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Constructor(
    @SerialName("constructorId") val constructorId : String,
    @SerialName("url") val url : String,
    @SerialName("name") val name : String,
    @SerialName("nationality") val nationality : String,
)

@Serializable
data class ConstructorStandings(
    @SerialName("position") val position : Int,
    @SerialName("points") val points : Int,
    @SerialName("wins") val wins : Int,
    @SerialName("Constructor") val constructor: Constructor,
)
