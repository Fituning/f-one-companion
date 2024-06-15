package com.utbm.sy43.f_one_companion.data.model.serializable_model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Driver(
    @SerialName("driverId") val driverId: String,
    @SerialName("permanentNumber") val permanentNumber: Int ,
    @SerialName("code") val code: String,
    @SerialName("url") val url: String,
    @SerialName("givenName") val givenName: String,
    @SerialName("familyName") val familyName: String,
    @SerialName("dateOfBirth") val dateOfBirth: String,
    @SerialName("nationality") val nationality: String
)


@Serializable
data class DriverTable(
    @SerialName("Drivers") val drivers: List<Driver> = emptyList()
)

@Serializable
data class DriverStandings(
    @SerialName("position") val position : Int,
    @SerialName("points") val points : Int,
    @SerialName("wins") val wins : Int,
    @SerialName("Driver") val driver: Driver,
    @SerialName("Constructors") val constructors: List<Constructor>,
)