package com.utbm.sy43.f_one_companion.ui.home

import com.utbm.sy43.f_one_companion.data.model.serializable_model.ConstructorStandings
import com.utbm.sy43.f_one_companion.data.model.serializable_model.DriverStandings

sealed interface ErgastUiState {
    data class Success(
        val driversStandings: List<DriverStandings>?,
        val constructors: List<ConstructorStandings>?,
    ) : ErgastUiState
    object Error : ErgastUiState
    object Loading : ErgastUiState
}