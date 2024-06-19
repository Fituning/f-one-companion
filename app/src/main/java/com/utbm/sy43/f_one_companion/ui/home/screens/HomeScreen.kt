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
import com.utbm.sy43.f_one_companion.R

@Composable
fun HomeScreen(homeViewModel: HomeViewModel){
    val ergastViewModel : ErgastViewModel = viewModel()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        item {
            ImageComponent(R.drawable.motorsinside_podium_monaco_leclerc_piastri_sainz, "Monaco Podium 2024")
        }
        item {
            FavDriverListScreen(ergastUiState = ergastViewModel._uistate, homeViewModel = homeViewModel, extraData = true)
        }
        item {
            TeamStandingsComponent(ergastUiState = ergastViewModel._uistate)
        }
        item {
            DriverStandingsComponent(ergastUiState = ergastViewModel._uistate, listSize = 10)
        }

        item {
            ImageComponent()
        }
    }
}