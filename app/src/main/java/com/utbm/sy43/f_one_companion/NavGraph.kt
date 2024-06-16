package com.utbm.sy43.f_one_companion

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.utbm.sy43.f_one_companion.ui.login.screens.*
import com.utbm.sy43.f_one_companion.ui.home.HomeViewModel
import com.utbm.sy43.f_one_companion.ui.home.screens.DriverListScreen
import com.utbm.sy43.f_one_companion.ui.home.screens.HomeScreen
import com.utbm.sy43.f_one_companion.ui.home.screens.UserInfoScreen

@Composable
fun NavGraph(startDestination: String = "login") {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") {LoginScreen(navController) }
        composable("signup") { SignupScreen(navController) }
    }
}

@Composable
fun HomeNavGraph(
    navController: NavHostController = rememberNavController(),
    homeViewModel: HomeViewModel = viewModel(),
    startDestination: String = "home"
){
    NavHost(
        navController = navController,
        startDestination = startDestination,
        ) {
        composable("home") {
            HomeScreen(homeViewModel = homeViewModel, navController = navController)
        }
        composable("driver_list") {
            DriverListScreen(
                //homeViewModel = homeViewModel,
                // navController = navController
            )
        }
        composable("user_info") {
            UserInfoScreen(homeViewModel = homeViewModel, navController = navController)
        }
    }
}
