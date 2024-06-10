package com.utbm.sy43.f_one_companion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.utbm.sy43.f_one_companion.ui.login.screens.*
import com.utbm.sy43.f_one_companion.ui.theme.FOneCompanionTheme

@Composable
fun NavGraph(startDestination: String = "login") {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") {LoginScreen(navController) }
        composable("signup") { SignupScreen(navController) }
    }
}
