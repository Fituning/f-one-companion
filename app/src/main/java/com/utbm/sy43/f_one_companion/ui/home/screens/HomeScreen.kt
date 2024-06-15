package com.utbm.sy43.f_one_companion.ui.home.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.utbm.sy43.f_one_companion.ui.components.image.ImageComponent
import com.utbm.sy43.f_one_companion.ui.components.standings.DriverStandingsComponent
import com.utbm.sy43.f_one_companion.ui.components.standings.StandingsViewModel
import com.utbm.sy43.f_one_companion.ui.home.HomeViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel, navController: NavController){
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val standingsViewModel : StandingsViewModel = viewModel()
        ImageComponent()
        DriverStandingsComponent(standingsUiState = standingsViewModel._uistate)
    }
}