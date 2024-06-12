package com.utbm.sy43.f_one_companion.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.utbm.sy43.f_one_companion.ui.components.ImageComponent
import com.utbm.sy43.f_one_companion.ui.components.StandingsComponent
import com.utbm.sy43.f_one_companion.ui.theme.FOneCompanionTheme
import com.utbm.sy43.f_one_companion.R
import com.utbm.sy43.f_one_companion.ui.login.LoginActivity
import com.utbm.sy43.f_one_companion.ui.splash_screen.SplashScreenActivity

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            FOneCompanionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun MainApp() {
    Scaffold(
        topBar = {
            TopAppBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            ImageComponent()
            StandingsComponent()
        }
    }
}

@Composable
fun TopAppBar(
    context: Context = LocalContext.current
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            IconButton(
                onClick = {
                    val intent = Intent(context, SplashScreenActivity::class.java)
                    context.startActivity(intent)
                },
                //modifier = Modifier . weight (1f)
            ) {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = "",
                )
            }

            Image(
                painter = painterResource(id = R.drawable.f_one_companion_logo),
                contentDescription = "",//todo add description
                modifier = Modifier
                    .size(64.dp)
            )

            UserDisplay()
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = MaterialTheme.colorScheme.outline)
        )
    }
}


@Composable
fun UserDisplay(
    context: Context = LocalContext.current
){
    IconButton(
        onClick = {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        },
        //modifier = Modifier . weight (1f)
    ) {
        Icon(
            Icons.Default.AccountCircle,
            contentDescription = "",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    FOneCompanionTheme {
        MainApp()
    }
}