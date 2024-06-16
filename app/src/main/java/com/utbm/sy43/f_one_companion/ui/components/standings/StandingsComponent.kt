package com.utbm.sy43.f_one_companion.ui.components.standings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.utbm.sy43.f_one_companion.data.model.serializable_model.ConstructorStandings
import com.utbm.sy43.f_one_companion.data.model.serializable_model.DriverStandings
import com.utbm.sy43.f_one_companion.ui.components.CardWithBorder
import com.utbm.sy43.f_one_companion.ui.components.ColorBarr

@Composable
fun DriverStandingsComponent(
    modifier: Modifier = Modifier,
    standingsUiState: StandingsUiState,
    listSize: Int = 5
) {
    CardWithBorder(title = "Driver Standings") {
        when (standingsUiState) {
            is StandingsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
            is StandingsUiState.Success -> standingsUiState.driversStandings?.let {
                DriverDisplay(
                    drivers = it,
                    listSize= listSize
                )
            }
            is StandingsUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
        }
    }
}


@Composable
fun TeamStandingsComponent(
    modifier: Modifier = Modifier,
    standingsUiState: StandingsUiState,
    listSize: Int = 5
) {
    CardWithBorder(title = "Team Standings") {
        when (standingsUiState) {
            is StandingsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
            is StandingsUiState.Success -> standingsUiState.constructors?.let {
                TeamDisplay(
                    constructors = it,
                    listSize = listSize
                )
            }
            is StandingsUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
        }
    }
}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    // Add your loading screen implementation here
    Box(modifier = Modifier.fillMaxSize()){
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    // Add your error screen implementation here
}

@Composable
fun DriverDisplay(
    drivers: List<DriverStandings>,
    listSize: Int = 5
) {
    Column {
        for (index in 0..listSize-1){
            val bgColor = if (index % 2 == 0) {
                MaterialTheme.colorScheme.surface
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
            DriverLineInfo(bgColor = bgColor, driverStanding = drivers.get(index))
        }
    }
}


@Composable
fun TeamDisplay(
    constructors: List<ConstructorStandings>,
    listSize: Int = 5
) {
    Column {
        for (index in 0..listSize-1){
            val bgColor = if (index % 2 == 0) {
                MaterialTheme.colorScheme.surface
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
            TeamLineInfo(bgColor = bgColor, constructorStandings = constructors.get(index))
        }
    }
}


@Composable
fun TeamLineInfo(
    bgColor: Color = MaterialTheme.colorScheme.surface,
    constructorStandings: ConstructorStandings,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.background(color = bgColor)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 4.dp, horizontal = 6.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 6.dp)
                    .height(IntrinsicSize.Min)
            ) {
                //team color
                ColorBarr(constructorStandings.constructor.constructorId)

                Text(
                    text = constructorStandings.position.toString(),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.width(24.dp)
                )
            }
            // position

            // nb wins
            Text(
                text = constructorStandings.wins.toString(),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(24.dp)
            )

            // team Name
            Text(
                text = constructorStandings.constructor.name.toUpperCase(),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.width(200.dp)
            )

            // Teams points
            Text(
                text = constructorStandings.points.toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .width(48.dp)
                    .padding(end = 12.dp)
            )
        }
    }
}

@Composable
fun DriverLineInfo(
    bgColor: Color = MaterialTheme.colorScheme.surface,
    modifier: Modifier = Modifier,
    driverStanding: DriverStandings,
) {
    Box(
        modifier = modifier.background(color = bgColor)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 4.dp, horizontal = 6.dp)
        ) {
            //position
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .height(IntrinsicSize.Min)// todo check modif
            ) {
                //team color
                ColorBarr(teamId = driverStanding.constructors.get(0).constructorId)

                Text(
                    text = driverStanding.position.toString(),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.width(24.dp)
                )

            }

            // diriver number
            Text(
                text =  driverStanding.driver.permanentNumber.toString(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(42.dp)
            )

            // driver Name
            Text(
                text = driverStanding.driver.givenName.first().toString().uppercase() +"."+ driverStanding.driver.familyName,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.width(200.dp)
            )

            // driver points
            Text(
                text = driverStanding.points.toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .width(48.dp)
                    .padding(end = 12.dp)
            )
        }
    }
}