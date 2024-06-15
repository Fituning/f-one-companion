package com.utbm.sy43.f_one_companion.ui.components.standings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.utbm.sy43.f_one_companion.data.model.serializable_model.Driver
import com.utbm.sy43.f_one_companion.data.model.serializable_model.DriverStandings
import com.utbm.sy43.f_one_companion.ui.theme.FOneCompanionTheme

@Composable
fun DriverStandingsComponent(
    modifier : Modifier = Modifier,
    standingsUiState: StandingsUiState,
) {
    CardWithBorder {
        Text(
            text = "Driver Standings",
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

        Column (modifier = Modifier.padding( bottom = 4.dp )) {
            when (standingsUiState){
                is StandingsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
                is StandingsUiState.Success -> standingsUiState.driversStandings?.let {
                    DriverDisplay(
                        drivers = it, modifier = modifier.fillMaxWidth()
                    )
                }
                is StandingsUiState.Error -> ErrorScreen( modifier = modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun TeamStandingsComponent(
    modifier : Modifier = Modifier
) {
    CardWithBorder {
        Text(
            text = "Team Standings",
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

        Column (modifier = Modifier.padding( bottom = 4.dp )) {
            //todo replace by values
            TeamLineInfo(bgColor = MaterialTheme.colorScheme.surface)
            TeamLineInfo(bgColor = MaterialTheme.colorScheme.surfaceVariant)
            TeamLineInfo(bgColor = MaterialTheme.colorScheme.surface)
            TeamLineInfo(bgColor = MaterialTheme.colorScheme.surfaceVariant)
        }
    }
}





@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {

}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {

}


@Composable
fun DriverDisplay(
    modifier: Modifier = Modifier,
    drivers : List<DriverStandings>
) {
    LazyColumn {
        itemsIndexed(drivers) { index, driver ->
            val bgColor = if (index % 2 == 0) {
                MaterialTheme.colorScheme.surface
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
            DriverLineInfo(bgColor = bgColor, driverStanding = driver)
        }
    }
}



@Composable
fun CardWithBorder(
    content: @Composable ColumnScope.() -> Unit
){
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .height(IntrinsicSize.Min)//to add for fit the content size
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
            content()
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
    color: Color = MaterialTheme.colorScheme.primary
){
    Spacer(modifier = Modifier
        .padding(0.dp)
        .width(4.dp)
        .height(16.dp)
        .background(color = color)
    )
}

@Composable
fun TeamLineInfo(
    bgColor : Color = MaterialTheme.colorScheme.surface,
    modifier: Modifier = Modifier
){

    Box(modifier = Modifier.background(color = bgColor)
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 2.dp)

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 6.dp)
                    .height(IntrinsicSize.Min)
            ) {
                Text(
                    text = "1",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.width(24.dp)
                )

                //team color
                ColorBarr(Color(0xFF0600EF))
            }
            // position


            // nb wins
            Text(
                text = "30",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(24.dp)
            )

            // team Name
            Text(
                text = "redbull racing".uppercase(),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.width(200.dp)
            )

            // Teams points
            Text(
                text = "141",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
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
){

    Box(modifier = Modifier.background(color = bgColor)
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 2.dp)

        ) {
            //position
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 6.dp)
                    .height(IntrinsicSize.Min)
            ) {
                Text(
                    text = driverStanding.position.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.width(24.dp)
                )

                //team color
                ColorBarr(Color(0xFF0600EF))//todo set team color
            }


            // diriver number
            Text(
                text = driverStanding.driver.permanentNumber.toString(),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(24.dp)
            )

            // driver Name
            Text(
                text =  driverStanding.driver.givenName.first().toString().uppercase() + driverStanding.driver.familyName,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.width(200.dp)
            )

            // driver points
            Text(
                text = driverStanding.points.toString(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .width(48.dp)
                    .padding(end = 12.dp)
            )

        }
    }

}