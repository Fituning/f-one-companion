package com.utbm.sy43.f_one_companion.ui.home.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.utbm.sy43.f_one_companion.ui.theme.FOneCompanionTheme
import com.utbm.sy43.f_one_companion.R
import com.utbm.sy43.f_one_companion.ui.components.ColorBarr
import dev.carlsen.flagkit.FlagIcons
import dev.carlsen.flagkit.flagicons.DK
import dev.carlsen.flagkit.flagicons.FR
import dev.carlsen.flagkit.flagicons.GB_ENG
import dev.carlsen.flagkit.flagicons.IT

@Composable
fun DriverListScreen(
    //homeViewModel: HomeViewModel = viewModel(),
    // navController: NavController? = null
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(4){
            DriverCard()
        }
    }
}

@Composable
fun DriverCard() {

    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.leclerc),
                    contentDescription = "Leclerc",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .scale(1.3f)
                        .padding(top = 20.dp),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter
                )
                Text(
                    text = "LEC",
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 12.dp, start = 12.dp)
                )
                Text(
                    text = "16",
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 12.dp, end = 12.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(4.dp)
                        .size(32.dp)
                        .wrapContentSize()
                        .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                ) {
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorite",
                            modifier = Modifier
                                .padding(2.dp)
                                .fillMaxSize()
                        )
                    }
                }

            }
        }

        Card(
            shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            /*
            "driverId": "leclerc",
            "permanentNumber": "16",
            "code": "LEC",
            "url": "http://en.wikipedia.org/wiki/Charles_Leclerc",
            "givenName": "Charles",
            "familyName": "Leclerc",
            "dateOfBirth": "1997-10-16",
            "nationality": "Monegasque"
            */
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    ColorBarr("ferrari", height = 32.dp, with = 1.dp)
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Charles",
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 12.sp,
                        )
                        Text(
                            text = "Leclerc",
                            style = MaterialTheme.typography.labelMedium,
                            fontSize = 14.sp,
                        )
                    }
                }

                Icon(
                    imageVector = FlagIcons.GB_ENG,
                    contentDescription = "Driver flag",
                )
            }

        }

    }
}


@Preview
@Composable
fun CardPreview(){
    FOneCompanionTheme {
        DriverListScreen()
    }
}