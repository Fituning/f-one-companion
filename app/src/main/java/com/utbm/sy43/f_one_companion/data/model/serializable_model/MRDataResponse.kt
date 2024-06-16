package com.utbm.sy43.f_one_companion.data.model.serializable_model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MRDataResponse(
    @SerialName("total") val total: Int,
    @SerialName("DriverTable") val driverTable: DriverTable? = null,
    @SerialName("StandingsTable") val standingsTable: StandingsTable? = null
)


//basic response
@Serializable
data class MRData(
    @SerialName("MRData") val MRData : MRDataResponse
)


//standing response

@Serializable
data class StandingsTable(
    @SerialName("StandingsLists") val standingsLists: List<StandingsLists>
)

@Serializable
data class StandingsLists(
    @SerialName("season") val season: String,
    @SerialName("round") val round: String,
    @SerialName("DriverStandings") val driversStandings: List<DriverStandings> = emptyList(),
    @SerialName("ConstructorStandings") val constructorStandings: List<ConstructorStandings> = emptyList(),
)
