package com.utbm.sy43.f_one_companion.ui.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.utbm.sy43.f_one_companion.R
import com.utbm.sy43.f_one_companion.ui.home.HomeViewModel
import com.utbm.sy43.f_one_companion.ui.login.LoginActivity
import com.utbm.sy43.f_one_companion.ui.theme.FOneCompanionTheme
import kotlinx.coroutines.launch


@Composable
fun TopAppBar(
    navController: NavController,
    homeViewModel: HomeViewModel,
    context: Context = LocalContext.current,
    drawerState: DrawerState,
) {
    val  homeUiState by homeViewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.weight(1f),
            ) {
                IconButton(

                    onClick = {
                        //navController.navigate("user_info")
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                ) {
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = "",
                    )
                }
            }


            IconButton(
                modifier = Modifier
                    .weight(1f)
                    .height(64.dp),
                onClick = {
                    navController.navigate("home")
                },
            ) {
                Image(
                    painter = painterResource(id = R.drawable.f_one_companion_logo),
                    contentDescription = "",//todo add description
                    modifier = Modifier
                        .size(64.dp),
                )
            }

            if (homeUiState.user != null){
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = homeUiState.user!!.userName)
                    IconButton(
                        onClick = {
                                  homeViewModel.logOutUser()
                        },
                    ) {
                        Icon(
                            Icons.Default.Logout,
                            contentDescription = "",
                        )
                    }
                }

            }else{
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.weight(1f),
                ){
                    IconButton(
                        onClick = {
                            val intent = Intent(context, LoginActivity::class.java)
                            context.startActivity(intent)
                        }
                    ) {
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = "",
                        )
                    }
                }
            }


        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = MaterialTheme.colorScheme.outline)
        )
    }
}