package com.utbm.sy43.f_one_companion.ui.login.screens

import android.content.ContentValues.TAG
import android.graphics.Color
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import com.utbm.sy43.f_one_companion.R
import com.utbm.sy43.f_one_companion.data.model.UserProfile
import com.utbm.sy43.f_one_companion.ui.theme.FOneCompanionTheme
import com.utbm.sy43.f_one_companion.ui.theme.customTextFieldColors
import com.utbm.sy43.f_one_companion.ui.theme.*

//private lateinit var auth: FirebaseAuth

@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {

    var passwordCheck by remember { mutableStateOf("") }
    FOneCompanionTheme {
        SignupScreen(navController = rememberNavController())
//        Surface(
//        ) {
//            TextField(
//                value = passwordCheck,
//                onValueChange = { passwordCheck = it },
//                label = { Text("Confirm password") },
//                visualTransformation = PasswordVisualTransformation(),
//                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,imeAction = ImeAction.Done),
//                keyboardActions = KeyboardActions(onDone = { }),
//                placeholder = { Text("confirmer votre mot de passe") },
//                //isError = passwordCheck.length < 6,
//                colors = TextFieldDefaults.colors(
//                    errorLabelColor = errorDark,
//                    errorCursorColor = errorDark,
//                    errorIndicatorColor = errorDark,
//                    errorTextColor = errorDark,
//                    unfocusedLabelColor = outlineDark,
//                    unfocusedIndicatorColor = outlineDark,
//                    unfocusedTextColor = outlineDark,
//                    focusedLabelColor = onPrimaryDark,
//                    focusedTextColor = onPrimaryDark,
//                    focusedIndicatorColor = onPrimaryDark,
//                )
//            )
//        }
    }
}

@Composable
fun SignupScreen(navController: NavHostController) {
//    var firstname by remember { mutableStateOf("") }
//    var lastname by remember { mutableStateOf("") }
//    var username by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var passwordCheck by remember { mutableStateOf("") }
    var firstname = "Valentin"
    var lastname = "AZANCOTH"
    var username = "Valaz"
    var email = "testpush@gmail.com"
    var password = "123456"
    var passwordCheck = "123456"

    var help by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    fun handleSignupError(exception: Exception?): String {
        when (exception) {
            is FirebaseAuthWeakPasswordException -> {
                passwordError = true
                return ("The password is too weak.")
            }

            is FirebaseAuthInvalidCredentialsException -> {
                emailError = true
                return ("The email address is badly formatted.")
            }

            is FirebaseAuthUserCollisionException -> {
                emailError = true
                return ("The email address is already in use by another account.")
            }

            else -> {
                return ("Authentication failed: ")
            }
        }
    }

    fun pushInformations(id: String) {
        val infos = hashSetOf(
            "firstName" to firstname,
            "lastName" to lastname,
            "userName" to username,
        )
        db.collection("users").document(id).set(infos)
    }

    fun signUp() {
        passwordError = false
        emailError = false
        if (password == passwordCheck) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val currentUserUID = auth.currentUser?.uid
                        if (currentUserUID != null) {
                            pushInformations(currentUserUID)
                        }
                        navController.navigate("home")//todo attention comprendre comment "home" renvoie vers la page d'accueil
                    } else {
                        help = handleSignupError(task.exception)
                    }
                }
        } else {
            help = "Veuillez saisir le même mot de passe"
            passwordError = true
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
            painter = painterResource(id = R.drawable.new_era_f1_logo),//TODO : remplace by MaterialTheme.colorScheme
            contentDescription = null,
            modifier = Modifier
                .height(125.dp),
            contentScale = ContentScale.Fit
        )

        Text(text = help, color = errorContainerDark)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )

        TextField(
            value = firstname,
            onValueChange = { firstname = it },
            label = { Text("Firstname") },
            placeholder = { Text("Entrez votre prénom") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            colors = customTextFieldColors()
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )
        TextField(
            value = lastname,
            onValueChange = { lastname = it },
            label = { Text("Lastname") },
            placeholder = { Text("Entrez votre nom") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            colors = customTextFieldColors()
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            placeholder = { Text("Entrez votre pseudo") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            colors = customTextFieldColors()
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            placeholder = { Text("Entrez votre mail") },
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
                imeAction = ImeAction.Next
            ),
            placeholder = { Text("Entrez votre mot de passe") },
            isError = passwordError,
            colors = customTextFieldColors()
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )
        TextField(
            value = passwordCheck,
            onValueChange = { passwordCheck = it },
            label = { Text("Confirm password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { signUp() }),
            placeholder = { Text("Confirmez votre mot de passe") },
            isError = passwordError,
            colors = customTextFieldColors()
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )
        Button(onClick = {
            signUp()
        }) {
            Text("Sign Up")
        }

        Spacer(
            modifier = Modifier.weight(1f)
        )

        TextButton(onClick = {
            navController.navigate("login")
        }) {
            Text("Already have an account? Login")
        }
    }
}