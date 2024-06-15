package com.utbm.sy43.f_one_companion.ui.home.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.utbm.sy43.f_one_companion.ui.home.HomeViewModel

@Composable
fun DriverListScreen( homeViewModel: HomeViewModel, navController: NavController) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "driver list")
    }


}