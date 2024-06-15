package com.utbm.sy43.f_one_companion.ui.components.team_standings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TeamStandingsViewModel : ViewModel() {
    private var _uistate = MutableStateFlow(TeamStandingsUiState())
    var uiState : StateFlow<TeamStandingsUiState> = _uistate.asStateFlow()

}