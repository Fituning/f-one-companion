package com.utbm.sy43.f_one_companion.ui.home.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.utbm.sy43.f_one_companion.ui.components.ImageComponent
import com.utbm.sy43.f_one_companion.ui.components.StandingsComponent
import com.utbm.sy43.f_one_companion.ui.components.TopAppBar
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