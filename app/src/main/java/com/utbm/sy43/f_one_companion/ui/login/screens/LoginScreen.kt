package com.utbm.sy43.f_one_companion.ui.login.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.utbm.sy43.f_one_companion.NavGraph
import com.utbm.sy43.f_one_companion.ui.theme.FOneCompanionTheme


@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    FOneCompanionTheme {
        LoginScreen( rememberNavController())
    }
}
@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Button(onClick = {
            // Handle login logic here
            navController.navigate("home")//todo attention comprendre comment "home" renvoie vers la page d'accueil
        }) {
            Text("Login")
        }
        TextButton(onClick = {
            navController.navigate("signup")
        }) {
            Text("Don't have an account? Sign up")
        }
    }
}
