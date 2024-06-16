package com.utbm.sy43.f_one_companion.ui.home.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.draw.clip
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
import com.utbm.sy43.f_one_companion.data.model.serializable_model.DriverStandings
import com.utbm.sy43.f_one_companion.ui.components.ColorBarr
import com.utbm.sy43.f_one_companion.ui.components.standings.ErrorScreen
import com.utbm.sy43.f_one_companion.ui.components.standings.LoadingScreen
import com.utbm.sy43.f_one_companion.ui.components.standings.StandingsUiState

@Composable
fun DriverListScreen(
    standingsUiState: StandingsUiState,
    //homeViewModel: HomeViewModel = viewModel(),
    // navController: NavController? = null
) {

    when (standingsUiState) {
        is StandingsUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is StandingsUiState.Success -> standingsUiState.driversStandings?.let {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(standingsUiState.driversStandings){driverStandings ->
                    DriverCard(driverStanding = driverStandings)
                }
            }
        }
        is StandingsUiState.Error -> ErrorScreen(modifier = Modifier.fillMaxSize())
    }

}

@Composable
fun DriverCard(
    driverStanding : DriverStandings,
) {

    Column {
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {
            Box {
                Image(
                    painter = painterResource(id = pilotNameToImageResId(driverStanding.driver.familyName)),
                    contentDescription = " driver image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .scale(1.3f)
                        .padding(top = 20.dp),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter
                )
                Text(
                    text = driverStanding.driver.code,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 12.dp, start = 12.dp)
                )
                Text(
                    text = driverStanding.driver.permanentNumber.toString(),
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
                    ColorBarr(driverStanding.constructors.get(0).constructorId, height = 32.dp, with = 1.dp)
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = driverStanding.driver.givenName,
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 12.sp,
                        )
                        Text(
                            text = driverStanding.driver.familyName,
                            style = MaterialTheme.typography.labelMedium,
                            fontSize = 14.sp,
                        )
                    }
                }
                Image(
                    painter = painterResource(id = nationalityToFlagResId(driverStanding.driver.nationality)),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp)
                        .clip(RoundedCornerShape(4.dp)),
                )


            }

        }

    }
}

fun nationalityToFlagResId(nationality: String): Int {
    return when (nationality) {
        "American" -> R.drawable.flag_us
        "Australian" -> R.drawable.flag_au
        "Austrian" -> R.drawable.flag_at
        "Belgian" -> R.drawable.flag_be
        "Brazilian" -> R.drawable.flag_br
        "British" -> R.drawable.flag_gb
        "Canadian" -> R.drawable.flag_ca
        "Chinese" -> R.drawable.flag_cn
        "Dutch" -> R.drawable.flag_nl
        "Finnish" -> R.drawable.flag_fi
        "French" -> R.drawable.flag_fr
        "German" -> R.drawable.flag_de
        "Italian" -> R.drawable.flag_it
        "Japanese" -> R.drawable.flag_jp
        "Mexican" -> R.drawable.flag_mx
        "Monegasque" -> R.drawable.flag_mc
        "Russian" -> R.drawable.flag_ru
        "Spanish" -> R.drawable.flag_es
        "Swedish" -> R.drawable.flag_se
        "Swiss" -> R.drawable.flag_ch
        "Thai" -> R.drawable.flag_th
        else ->  R.drawable.flag_mc
    }
}

fun pilotNameToImageResId(lastName: String): Int {
    return when (lastName) {
        "Alonso" -> R.drawable.alonso
        "Albon" -> R.drawable.albon
        "Bottas" -> R.drawable.bottas
        "Gasly" -> R.drawable.gasly
        "Hamilton" -> R.drawable.hamilton
        "Hulkenberg" -> R.drawable.hulkenberg
        "Leclerc" -> R.drawable.leclerc
        "Magnussen" -> R.drawable.magnussen
        "Hülkenberg" -> R.drawable.hulkenberg
        "Norris" -> R.drawable.norris
        "Ocon" -> R.drawable.ocon
        "Pérez" -> R.drawable.perez
        "Piastri" -> R.drawable.piastri
        "Russell" -> R.drawable.russell
        "Sainz" -> R.drawable.sainz
        "Sargeant" -> R.drawable.sargeant
        "Stroll" -> R.drawable.stroll
        "Tsunoda" -> R.drawable.tsunoda
        "Verstappen" -> R.drawable.verstappen
        "Zhou" -> R.drawable.zhou
        "Ricciardo" -> R.drawable.ricciardo
        else -> R.drawable.leclerc
    }
}





@Preview
@Composable
fun CardPreview(){
    FOneCompanionTheme {
        //DriverListScreen()
    }
}