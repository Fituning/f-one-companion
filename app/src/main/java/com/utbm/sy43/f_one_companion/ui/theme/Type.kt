package com.utbm.sy43.f_one_companion.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.utbm.sy43.f_one_companion.R

// Set of Material typography styles to start with
val Formula1 = FontFamily(
    Font(R.font.formula1_wide, FontWeight.ExtraBold),
    Font(R.font.formula1_regular),
    Font(R.font.formula1_bold, FontWeight.Bold),
)

val  Roboto = FontFamily(
    Font(R.font.roboto_regular),
    Font(R.font.roboto_bold, FontWeight.Bold)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = Formula1,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 36.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Formula1,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Formula1,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Formula1,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Formula1,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),


)