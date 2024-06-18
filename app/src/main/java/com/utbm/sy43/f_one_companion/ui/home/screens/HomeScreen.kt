package com.utbm.sy43.f_one_companion.ui.home.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.utbm.sy43.f_one_companion.ui.components.image.ImageComponent
import com.utbm.sy43.f_one_companion.ui.components.standings.DriverStandingsComponent
import com.utbm.sy43.f_one_companion.ui.home.ErgastViewModel
import com.utbm.sy43.f_one_companion.ui.components.standings.TeamStandingsComponent
import com.utbm.sy43.f_one_companion.ui.home.HomeViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel){
    val ergastViewModel : ErgastViewModel = viewModel()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        item {
            ImageComponent()
        }
        item {
            FavDriverListScreen(ergastUiState = ergastViewModel._uistate, homeViewModel = homeViewModel, extraData = true)
        }

        item {
            FavTeamListScreen(ergastUiState = ergastViewModel._uistate, homeViewModel = homeViewModel, extraData = true)
        }
        item {
            TeamStandingsComponent(ergastUiState = ergastViewModel._uistate)
        }
        item {
            DriverStandingsComponent(ergastUiState = ergastViewModel._uistate, listSize = 10)
        }
    }
}