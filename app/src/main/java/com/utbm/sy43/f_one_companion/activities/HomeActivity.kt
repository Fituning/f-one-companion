package com.utbm.sy43.f_one_companion.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.utbm.sy43.f_one_companion.ui.components.ImageComponent
import com.utbm.sy43.f_one_companion.ui.components.StandingsComponent
import com.utbm.sy43.f_one_companion.ui.theme.FOneCompanionTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FOneCompanionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                        ImageComponent()
                        StandingsComponent()
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Connexion")
        }
    }
    
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FOneCompanionTheme {
        Greeting("Android")
    }
}