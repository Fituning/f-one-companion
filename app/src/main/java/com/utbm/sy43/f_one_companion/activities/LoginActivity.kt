package com.utbm.sy43.f_one_companion.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.utbm.sy43.f_one_companion.ui.components.ImageComponent
import com.utbm.sy43.f_one_companion.ui.theme.FOneCompanionTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FOneCompanionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Login()
                }
            }
        }
    }
}

@Composable
fun Login() {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            ImageComponent()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    FOneCompanionTheme {
        Login()
    }
}