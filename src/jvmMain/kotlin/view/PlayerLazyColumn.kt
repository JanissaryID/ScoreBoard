package view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.ComponentPlayer
import `object`.ModelPlayer

@Composable
fun PlayerLazyColumn(
    playerModel: List<ModelPlayer>,
    modifier: Modifier,
    aligment: Boolean = true
) {
    if (aligment){
        LazyColumn(
            modifier = Modifier.padding(horizontal = 8.dp).width(180.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.End
        ){
            itemsIndexed(items = playerModel) { _, player ->
                ComponentPlayer(
                    modifier = modifier,
                    name = player.Name,
                    number = player.Number,
                    image = player.EventPlayer,
                    team = player.Team,
                    description = player.EventPlayerDescription
                )
            }
        }
    }
    else{
        LazyColumn(
            modifier = Modifier.padding(horizontal = 8.dp).width(180.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ){
            itemsIndexed(items = playerModel) { _, player ->
                ComponentPlayer(
                    modifier = modifier,
                    name = player.Name,
                    number = player.Number,
                    image = player.EventPlayer,
                    team = player.Team,
                    description = player.EventPlayerDescription
                )
            }
        }
    }
}