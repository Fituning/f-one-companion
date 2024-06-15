package com.utbm.sy43.f_one_companion.ui.components.standings

import com.utbm.sy43.f_one_companion.data.model.serializable_model.Constructor
import com.utbm.sy43.f_one_companion.data.model.serializable_model.DriverStandings

sealed interface StandingsUiState {
    data class Success(
        val driversStandings: List<DriverStandings>?,
        val constructors: List<Constructor>?,
    ) : StandingsUiState
    object Error : StandingsUiState
    object Loading : StandingsUiState
}