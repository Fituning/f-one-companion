package com.utbm.sy43.f_one_companion.ui.home.screens

import android.renderscript.ScriptGroup.Input
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.utbm.sy43.f_one_companion.R
import com.utbm.sy43.f_one_companion.ui.components.TopAppBar
import com.utbm.sy43.f_one_companion.ui.home.HomeViewModel
import com.utbm.sy43.f_one_companion.ui.theme.customTextFieldColors
import com.utbm.sy43.f_one_companion.ui.theme.errorContainerDark

@Composable
fun UserInfoScreen( homeViewModel: HomeViewModel, navController: NavController) {
    val homeUiState by homeViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 16.dp, bottom = 4.dp),
            text = stringResource(id = R.string.user_page),
            style = MaterialTheme.typography.displayMedium,
        )



        Box {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp,),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(2) {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                items(1) {
                    Text(text = "First Name : ",
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 12.sp,)
                }
                items(1) {
                    Text(text = (homeUiState.user?.firstName ?: "first name undefined"),
                        style = MaterialTheme.typography.labelMedium,
                        fontSize = 14.sp,)
                }

                items(2) {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(1) {
                    Text(text = "Last Name :",
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp,)
                }
                items(1) {
                    Text(text = (homeUiState.user?.lastName ?: ""),
                        style = MaterialTheme.typography.labelMedium,
                        fontSize = 14.sp,)
                }

                items(2) {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(1) {
                    Text(text = "User Name :",
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 12.sp,)
                }
                items(1) {
                    Text(text = (homeUiState.user?.userName ?: ""),
                        style = MaterialTheme.typography.labelMedium,
                        fontSize = 14.sp,)
                }

                items(2) {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(1, span = { GridItemSpan(2) }){
                    Button(onClick = {navController.navigate("update_user_info")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)){
                        Text(text = "Update User Informations",
                            style = MaterialTheme.typography.labelMedium,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(vertical = 4.dp)
                            )
                    }
                }
            }
        }
    }
}


@Composable
fun UpdateUserInfoScreen(homeViewModel: HomeViewModel, navController: NavController) {
    val homeUiState by homeViewModel.uiState.collectAsState()
    var firstName by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .padding(WindowInsets.ime.asPaddingValues()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 16.dp, bottom = 4.dp),
            text = "Update User Informations",
            style = MaterialTheme.typography.displayMedium,
        )
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(WindowInsets.ime.asPaddingValues()),
        ){
            Text(
                text = "New First Name : ",
                textAlign = TextAlign.Center
            )
            TextField(
                value = firstName,
                onValueChange = {newFirstName : String -> firstName = newFirstName},
                placeholder = { Text("New First Name") },
                modifier = Modifier.fillMaxWidth()
            )
        }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
            )
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(WindowInsets.ime.asPaddingValues()),
        ){
            Text(
                text = "New Last Name : ",
                textAlign = TextAlign.Center
            )
            TextField(
                value = lastname,
                onValueChange = {newLastName : String -> lastname = newLastName},
                placeholder = { Text("New Last Name") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(WindowInsets.ime.asPaddingValues()),
        ){
            Text(
                text = "New User Name : ",
                textAlign = TextAlign.Center
            )
            TextField(
                value = username,
                onValueChange = {newUserName : String -> username = newUserName},
                placeholder = { Text("New User Name") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )
        Button(onClick = {homeViewModel.updateUser(firstName, lastname, username)
            navController.navigate("user_info")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)){
            Text(text = "Validate New Informations",
                style = MaterialTheme.typography.labelMedium,
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}
