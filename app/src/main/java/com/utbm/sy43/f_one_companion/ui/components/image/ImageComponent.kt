package com.utbm.sy43.f_one_companion.ui.components.image


import com.utbm.sy43.f_one_companion.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.utbm.sy43.f_one_companion.ui.theme.FOneCompanionTheme


@Composable
fun ImageComponent() {
    Box(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().height(IntrinsicSize.Max)

    ){
        val padding = 50.dp
        Image(painter = painterResource(id = R.drawable.charles_leclerc_ferrari_1024x683 ),
            contentDescription = "test",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = padding)
        )

        val colorStops = arrayOf(
            0.0f to Color(0x0015151D),
            0.15f to Color(0x4015151D),
            0.30f to Color(0x8015151D),
            0.55f to Color(0xBF15151D),
            0.80f to Color(0xFF15151D),
            1.0f to Color(0xFF15151D),
        )

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(top = padding)
            .background(Brush.verticalGradient(colorStops = colorStops))
        ) {

        }
    }

}
@Preview
@Composable
fun ImageComponentPreview(){
    FOneCompanionTheme {
        ImageComponent()
    }
}