package com.utbm.sy43.f_one_companion.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.utbm.sy43.f_one_companion.ui.theme.*

@Composable
fun CardWithBorder(
    title : String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .height(IntrinsicSize.Min) // Ensure height is intrinsic
            .padding(start = 14.dp, end = 12.dp, top = 14.dp, bottom = 12.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(color = MaterialTheme.colorScheme.surface)
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 2.dp, top = 2.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(topStart = 12.dp)
                )
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(topStart = 12.dp, bottomEnd = 6.dp),
                )
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier
                    .padding(start = 12.dp, top = 12.dp, end = 12.dp, bottom = 6.dp)
                    .fillMaxWidth()
            )
            Spacer(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.outline)
                    .fillMaxWidth()
                    .height(1.dp)
            )

            Column(
                modifier = Modifier.padding(bottom = 4.dp)
            ) {
                content()
            }

        }



        Box(
            modifier = Modifier
                .height(4.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(0.dp)
        ) {
        }
        Box(
            modifier = Modifier
                .width(4.dp)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.background)
                .padding(0.dp)
        ) {
        }
    }
}

@Composable
fun ColorBarr(
    teamId: String ="",
    height: Dp = 16.dp,
    with: Dp = 3.dp
) {
    val color = TeamColor(teamId)
    Spacer(
        modifier = Modifier
            .padding(end = 8.dp)
            .width(with)
            .height(height)
            .background(color = color)
    )
}

fun TeamColor(teamId : String): Color
{
    return when(teamId){
        "red_bull" -> RedBullColor
        "ferrari" -> FerrariColor
        "mclaren" -> McLarenColor
        "mercedes" -> MercedesColor
        "aston_martin" -> AstonMartinColor
        "rb" -> RBColor
        "haas" -> HaasColor
        "alpine" -> AlpineColor
        "williams" -> WilliamsColor
        "sauber" -> KickSauberColor
        else -> Color(0x00000000)
    }
}