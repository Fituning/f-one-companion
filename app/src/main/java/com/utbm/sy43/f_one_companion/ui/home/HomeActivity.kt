package com.utbm.sy43.f_one_companion.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.utbm.sy43.f_one_companion.HomeNavGraph
import com.utbm.sy43.f_one_companion.ui.theme.FOneCompanionTheme
import com.utbm.sy43.f_one_companion.ui.components.DrawerContent
import com.utbm.sy43.f_one_companion.ui.components.TopAppBar
import kotlinx.coroutines.launch

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val homeViewModel : HomeViewModel = viewModel()

            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()

            FOneCompanionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            ModalDrawerSheet {
                                DrawerContent(
                                    navController = navController,
                                    closeDrawer = {
                                        scope.launch {
                                            drawerState.apply {
                                                if (isClosed) open() else close()
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    ) {
                        //todo put scaffold
                        Scaffold(
                            topBar = {
                                TopAppBar(navController = navController, homeViewModel = homeViewModel, drawerState = drawerState)
                            }
                        ) { innerPadding ->
                            Column(
                                modifier = Modifier
                                    .padding(innerPadding),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                            ) {
                                HomeNavGraph(navController,homeViewModel)
                            }
                        }
                    }




                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun HomePreview() {
    FOneCompanionTheme {

    }
}