package com.utbm.sy43.f_one_companion.ui.home.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.utbm.sy43.f_one_companion.ui.components.image.ImageComponent
import com.utbm.sy43.f_one_companion.ui.components.standings.DriverStandingsComponent
import com.utbm.sy43.f_one_companion.ui.components.standings.StandingsViewModel
import com.utbm.sy43.f_one_companion.ui.components.standings.TeamStandingsComponent
import com.utbm.sy43.f_one_companion.ui.home.HomeViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel, navController: NavController){
    val standingsViewModel : StandingsViewModel = viewModel()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        item {
            ImageComponent()
        }
        item {
            TeamStandingsComponent(standingsUiState = standingsViewModel._uistate)
        }
        item {
            DriverStandingsComponent(standingsUiState = standingsViewModel._uistate, listSize = 10)
        }
    }
}