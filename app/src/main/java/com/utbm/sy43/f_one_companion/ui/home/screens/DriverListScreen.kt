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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.utbm.sy43.f_one_companion.ui.theme.FOneCompanionTheme
import com.utbm.sy43.f_one_companion.R
import com.utbm.sy43.f_one_companion.data.model.serializable_model.DriverStandings
import com.utbm.sy43.f_one_companion.ui.components.ColorBarr
import com.utbm.sy43.f_one_companion.ui.components.standings.ErrorScreen
import com.utbm.sy43.f_one_companion.ui.components.standings.LoadingScreen
import com.utbm.sy43.f_one_companion.ui.home.ErgastUiState
import com.utbm.sy43.f_one_companion.ui.home.HomeUiState
import com.utbm.sy43.f_one_companion.ui.home.HomeViewModel

@Composable
fun DriverListScreen(
    ergastUiState: ErgastUiState,
    homeViewModel: HomeViewModel,
) {
    val  homeUiState by homeViewModel.uiState.collectAsState()
    when (ergastUiState) {
        is ErgastUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is ErgastUiState.Success -> ergastUiState.driversStandings?.let {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(top= 16.dp, bottom = 4.dp),
                    text = stringResource(id = R.string.season_divers),
                    style = MaterialTheme.typography.displayMedium,
                )

                Box {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp,),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(2){
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        items(ergastUiState.driversStandings){ driverStandings ->
                            DriverCard(driverStanding = driverStandings, homeViewModel = homeViewModel, homeUiState = homeUiState)
                        }
                    }

                    val colorStops2 = arrayOf(
                        0.0f to Color(0xFF15151D),
                        0.20f to Color(0xFF15151D),
                        0.45f to Color(0xBF15151D),
                        0.70f to Color(0x8015151D),
                        0.85f to Color(0x4015151D),
                        1.0f to Color(0x0015151D)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(32.dp)
                            .background(Brush.verticalGradient(colorStops = colorStops2))

                    )
                }



            }

        }
        is ErgastUiState.Error -> ErrorScreen(modifier = Modifier.fillMaxSize())
    }

}
@Preview
@Composable
fun CardPreview(){
    FOneCompanionTheme {
        //FavDriverListScreen()
    }
}

@Composable
fun FavDriverListScreen(
    ergastUiState: ErgastUiState,
    homeViewModel: HomeViewModel,
    extraData: Boolean,
) {
    val  homeUiState by homeViewModel.uiState.collectAsState()
    when (ergastUiState) {
        is ErgastUiState.Loading -> LoadingScreen(modifier = Modifier.fillMaxSize())
        is ErgastUiState.Success -> ergastUiState.driversStandings?.let {
            val favoriteDriverStandings = it.filter { driverStanding ->
                homeUiState.user?.favoriteDrivers?.contains(driverStanding.driver.driverId) == true
            }.sortedBy { driverStanding ->
                homeUiState.user?.favoriteDrivers?.indexOf(driverStanding.driver.driverId) ?: Int.MAX_VALUE
            }
            if(favoriteDriverStandings.isNotEmpty()){
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.my_favorite_drivers),
                        style = MaterialTheme.typography.displayMedium,
                    )

                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(1),
                        modifier = Modifier
                            //.padding(horizontal = 16.dp)
                            .heightIn(max = 320.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item(){
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        items(favoriteDriverStandings){ driverStandings ->
                            DriverCard(driverStanding = driverStandings, homeViewModel = homeViewModel, homeUiState = homeUiState, extraData)
                        }
                        item(){
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
            }
        }
        is ErgastUiState.Error -> ErrorScreen(modifier = Modifier.fillMaxSize())
    }

}



@Composable
fun DriverCard(
    driverStanding: DriverStandings,
    homeViewModel: HomeViewModel,
    homeUiState: HomeUiState,
    extraData : Boolean =false
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp/2
    Column(
        modifier = Modifier.widthIn(max = screenWidth -8.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
            ,
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
                    if(homeUiState.user?.favoriteDrivers?.contains(driverStanding.driver.driverId) == true){
                        IconButton(
                            onClick = {
                                homeViewModel.removeDriverFav(driverStanding.driver.driverId)
                                //homeViewModel.logOutUser()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Favorite,
                                contentDescription = "Favorite",
                                tint = Color.Red,
                                modifier = Modifier
                                    .padding(2.dp)
                                    .fillMaxSize()
                            )
                        }
                    }else{
                        IconButton(
                            onClick = { homeViewModel.addDriverFav(driverStanding.driver.driverId) }
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
        }

        Card(
            shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Column {
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
                if (extraData){
                    Spacer(
                        modifier = Modifier
                            .background(color = MaterialTheme.colorScheme.outline)
                            .fillMaxWidth()
                            .height(1.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "P " + driverStanding.position.toString(),
                            style = MaterialTheme.typography.displayMedium,
                            fontSize = 14.sp,
                        )
                        Row(
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = driverStanding.points.toString(),
                                style = MaterialTheme.typography.labelLarge,
                                fontSize = 14.sp,
                            )
                            Text(
                                text = " PTS",
                                style = MaterialTheme.typography.labelLarge,
                                fontSize = 12.sp,
                            )
                        }

                    }
                }
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





