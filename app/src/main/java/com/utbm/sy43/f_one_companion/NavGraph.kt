package com.utbm.sy43.f_one_companion

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.utbm.sy43.f_one_companion.ui.home.HomeViewModel
import com.utbm.sy43.f_one_companion.ui.home.screens.DriverListScreen
import com.utbm.sy43.f_one_companion.ui.home.screens.HomeScreen

@Composable
fun NavGraph(startDestination: String = "login") {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {

    }
}

@Composable
fun HomeNavGraph(startDestination: String = "home"){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable("home") {
            val homeViewModel : HomeViewModel = viewModel()
            HomeScreen(homeViewModel = homeViewModel, navController = navController)
        }
        composable("driver_list") {
            val homeViewModel : HomeViewModel = viewModel()
            DriverListScreen(homeViewModel = homeViewModel, navController = navController)
        }
    }
}
