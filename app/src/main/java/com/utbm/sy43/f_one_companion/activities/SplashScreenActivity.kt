package com.utbm.sy43.f_one_companion.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.utbm.sy43.f_one_companion.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.utbm.sy43.f_one_companion.ui.theme.*


class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FOneCompanionTheme {
                SplashScreen()
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            Intent(this, HomeActivity::class.java).also{
                startActivity(it)
            }
            finish()
        }, 3000)//time for loading the app

    }

    @Composable
    fun SplashScreen() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.f_one_companion_logo),//TODO : remplace by MaterialTheme.colorScheme
                    contentDescription = null,
                    modifier = Modifier
                        .size(215.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(20.dp))
                LinearProgressIndicator(
                    modifier = Modifier
                        .width(180.dp)
                        .height(2.dp)
                        .progressSemantics()
                        .clip(RoundedCornerShape(50)) // Adjust the corner radius as needed
                        //.background(color = Background_color)//TODO : remplace by MaterialTheme.colorScheme
                        .align(Alignment.CenterHorizontally),
                    //color = Primary_color//TODO : remplace by MaterialTheme.colorScheme
                )
            }
            Text(
                text = "From UTBM\nSY43",
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 48.dp),
                fontSize = 12.sp, // adjust size as necessary
            )
        }
    }
}