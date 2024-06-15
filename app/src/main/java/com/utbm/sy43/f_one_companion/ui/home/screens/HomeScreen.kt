package com.utbm.sy43.f_one_companion.ui.home.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.utbm.sy43.f_one_companion.ui.components.image.ImageComponent
import com.utbm.sy43.f_one_companion.ui.components.team_standings.StandingsComponent
import com.utbm.sy43.f_one_companion.ui.home.HomeViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel, navController: NavController){
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ImageComponent()
        StandingsComponent()
    }
}