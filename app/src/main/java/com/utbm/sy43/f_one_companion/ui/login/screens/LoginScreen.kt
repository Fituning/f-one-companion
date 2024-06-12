package com.utbm.sy43.f_one_companion.ui.login.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.utbm.sy43.f_one_companion.R
import com.utbm.sy43.f_one_companion.ui.home.HomeActivity
import com.utbm.sy43.f_one_companion.ui.login.LoginActivity
import com.utbm.sy43.f_one_companion.ui.theme.FOneCompanionTheme
import com.utbm.sy43.f_one_companion.ui.theme.customTextFieldColors
import com.utbm.sy43.f_one_companion.ui.theme.errorContainerDark
import com.utbm.sy43.f_one_companion.ui.theme.onErrorDark

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    FOneCompanionTheme {
        LoginScreen(rememberNavController())
    }
}

@Composable
fun LoginScreen(
        navController: NavHostController,
        context: Context = LocalContext.current,
    ) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val passwordError by remember { mutableStateOf(false) }
    val emailError by remember { mutableStateOf(false) }
    var help by remember { mutableStateOf("") }
    var auth = FirebaseAuth.getInstance()

    fun signIn() {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val mainIntent = Intent(context, HomeActivity::class.java)
                    context.startActivity(mainIntent)
                    (context as LoginActivity).finish() // Fermez LoginActivity
                } else {
                    help = task.exception?.message.toString()
                }
            }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.new_era_f1_logo),
            contentDescription = null,
            modifier = Modifier
                .size(215.dp),
            contentScale = ContentScale.Fit
        )

        Text(text = help, color = errorContainerDark)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            isError = emailError,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            colors = customTextFieldColors()
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions { signIn() },
            placeholder = { Text("Entrez votre mot de passe") },
            isError = passwordError,
            colors = customTextFieldColors()
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )
        Button(
            onClick = {
                signIn()
            }
        ) {
            Text("Login")
        }
        Spacer(
            modifier = Modifier.weight(1f)
        )
        TextButton(onClick = {
            navController.navigate("signup")
        }) {
            Text("Don't have an account? Sign up")
        }
    }
}