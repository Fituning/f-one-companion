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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.utbm.sy43.f_one_companion.ui.theme.FOneCompanionTheme
import com.utbm.sy43.f_one_companion.ui.theme.backgroundDark


@Composable
fun ImageComponent(
    imageId: Int = R.drawable.charles_leclerc_ferrari_1024x683,
    title : String = ""
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .height(IntrinsicSize.Max)

    ){
        var imageHeightPx by remember { mutableStateOf(0) }
        val imageHeightDp: Dp
        val density = LocalDensity.current

        // Convert image height in pixels to dp
        imageHeightDp = with(density) { imageHeightPx.toDp()}

        var padding = 50.dp
        Image(painter = painterResource(id = imageId ),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = padding)
                .onGloballyPositioned { coordinates ->
                    imageHeightPx = coordinates.size.height /3
                }
        )

        Text(
            text = title,
            style = MaterialTheme.typography.displayMedium.copy(shadow = Shadow(
                color = backgroundDark, offset = Offset(4f,4f), blurRadius = 3f
            )),
            modifier = Modifier.padding(top = 16.dp)
                .align(Alignment.TopCenter)
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
            .padding(top = imageHeightDp)
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