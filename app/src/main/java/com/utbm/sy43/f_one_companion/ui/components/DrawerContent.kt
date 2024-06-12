package com.utbm.sy43.f_one_companion.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun DrawerContent(
    navController: NavHostController,
    closeDrawer: () -> Unit
) {
    Column(modifier = Modifier
        .wrapContentSize()
        .padding(16.dp)) {
        Text(text = "Home", modifier = Modifier
            .clickable {
                navController.navigate("home")
                closeDrawer()
            }
            .padding(8.dp))
        Text(text = "Drivers", modifier = Modifier
            .clickable {
                navController.navigate("driver_list")
                closeDrawer()
            }
            .padding(8.dp))
        Text(text = "User", modifier = Modifier // todo set if user is connected
            .clickable {
                navController.navigate("user_info")
                closeDrawer()
            }
            .padding(8.dp))
    }
}
