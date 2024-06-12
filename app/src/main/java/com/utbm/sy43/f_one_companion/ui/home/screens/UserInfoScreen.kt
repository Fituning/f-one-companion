package com.utbm.sy43.f_one_companion.ui.home.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.utbm.sy43.f_one_companion.ui.components.TopAppBar
import com.utbm.sy43.f_one_companion.ui.home.HomeViewModel

@Composable
fun UserInfoScreen( homeViewModel: HomeViewModel, navController: NavController) {
    val homeUiState by homeViewModel.uiState.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "first name :"+ (homeUiState.user?.firstName ?: "first name undefined"))
        Text(text = "last name :"+ (homeUiState.user?.lastName ?: ""))
        Text(text = "user name :"+ (homeUiState.user?.userName ?: ""))
    }
}