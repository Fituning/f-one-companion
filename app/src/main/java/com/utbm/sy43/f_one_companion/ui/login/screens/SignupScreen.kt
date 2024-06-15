package com.utbm.sy43.f_one_companion.ui.login.screens

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.*
import com.google.firebase.firestore.FirebaseFirestore
import com.utbm.sy43.f_one_companion.R
import com.utbm.sy43.f_one_companion.data.model.UserProfile
import com.utbm.sy43.f_one_companion.ui.home.HomeActivity
import com.utbm.sy43.f_one_companion.ui.login.LoginActivity
import com.utbm.sy43.f_one_companion.ui.theme.FOneCompanionTheme
import com.utbm.sy43.f_one_companion.ui.theme.customTextFieldColors
import com.utbm.sy43.f_one_companion.ui.theme.*
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

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
fun SignupScreen(
    navController: NavHostController,
    context: Context = LocalContext.current
) {
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordCheck by remember { mutableStateOf("") }

    var help: Int? by remember { mutableStateOf(null) }
    var passwordError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    
    
    fun handleSignupError(exception: Exception?): Int {
        when (exception) {
            is FirebaseAuthWeakPasswordException -> {
                passwordError = true
                password = ""
                passwordCheck = ""
                return (R.string.weak_password)
            }

            is FirebaseAuthInvalidCredentialsException -> {
                emailError = true
                return (R.string.wrong_email_format)
            }

            is FirebaseAuthUserCollisionException -> {
                emailError = true
                return (R.string.email_already_used)
            }
            else -> {
                return (R.string.error_account_creation)
            }
        }
    }

    fun signUp() {
        passwordError = false
        emailError = false

        if( email == "" ){
            help = R.string.error_email_input
            emailError = true
        }else if( password == "" ){
            help = R.string.error_password_input
            passwordError = true
        }else if( passwordCheck == ""){
            help = R.string.error_retype_password_input
            passwordError = true
            passwordCheck = ""
        }else if (password != passwordCheck){
            help = R.string.not_same_password
            passwordError = true
        }else{
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userId = auth.currentUser?.uid
                        Log.d("singUP", (userId ?: "pas d'id"))
                        val user = UserProfile(firstname,lastname,username)

                        if (userId != null) {
                            db.collection("users").document(userId).set(user)
                                .addOnSuccessListener {
                                    val mainIntent = Intent(context, HomeActivity::class.java)
                                    context.startActivity(mainIntent)
                                    (context as LoginActivity).finish() // Fermez LoginActivity
                                }
                                .addOnFailureListener { e ->
                                    Log.e("authtag", e.toString())
                                }
                        }
                    } else {
                        Log.e("singUP", task.exception.toString())
                        help = handleSignupError(task.exception)
                    }
                }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .padding(WindowInsets.ime.asPaddingValues()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.f_one_companion_logo),//TODO : remplace by MaterialTheme.colorScheme
            contentDescription = null,
            modifier = Modifier
                .height(125.dp),
            contentScale = ContentScale.Fit
        )

        if (help != null){
            Text(
                text = stringResource(id = help!!),
                color = errorContainerDark,
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
            )
        }


        TextField(
            value = firstname ,
            onValueChange = { firstname = it },
            label = { Text(stringResource(id = R.string.label_firstname)) },
            placeholder = { Text(stringResource(id = R.string.pholder_firstname)) },
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
            label = { Text(stringResource(id = R.string.label_lastname)) },
            placeholder = { Text(stringResource(id = R.string.pholder_lastname)) },
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
            label = { Text(stringResource(id = R.string.label_username)) },
            placeholder = { Text(stringResource(id = R.string.pholder_username)) },
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
            label = { Text(stringResource(id = R.string.label_email)) },
            placeholder = { Text(stringResource(id = R.string.pholder_email)) },
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
            label = { Text(stringResource(id = R.string.label_password)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            placeholder = { Text(stringResource(id = R.string.pholder_password)) },
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
            label = { Text(stringResource(id = R.string.label_password)) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { signUp() }),
            placeholder = { Text(stringResource(id = R.string.pholder_retype_password)) },
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
            Text(stringResource(id = R.string.signup))
        }

        Spacer(
            modifier = Modifier.weight(1f)
        )

        TextButton(onClick = {
            navController.navigate("login")
        }) {
            Text(stringResource(id = R.string.signin_catchphrase))
        }
    }
}