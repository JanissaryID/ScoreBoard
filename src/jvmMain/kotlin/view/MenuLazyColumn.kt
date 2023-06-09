package view

import SELECTED_SCREEN
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.ComponentMenu
import component.ComponentPlayer
import `object`.ModelPlayer

@Composable
fun MenuLazyColumn(
    menu: List<String>,
) {
    var selected_index by remember { mutableStateOf(SELECTED_SCREEN) }
    val on_click_index = { index: Int ->
        selected_index = index
        SELECTED_SCREEN = selected_index
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).width(180.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.End
    ){
        itemsIndexed(items = menu) { index, menuWindows ->
            ComponentMenu(
                text = menuWindows,
                onClick = on_click_index,
                index = if(selected_index != index){
                    index
                }  else index,
                selected = if(SELECTED_SCREEN == index) false else true
            )
        }
    }
}