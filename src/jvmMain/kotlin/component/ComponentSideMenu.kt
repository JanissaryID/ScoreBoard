package component

import DIALOG_TIMER
import FULL_SCREEN
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import controller.Display
import controller.Timer
import view.MenuLazyColumn

@Composable
fun ComponentSideMenu(
    Display: Display,
){
    Column(
        modifier = Modifier.fillMaxHeight()
            .width(200.dp).padding(vertical = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        println("List Display = ${Display.listDisplay}")

        MenuLazyColumn(
            menu = Display.listDisplay
        )

        Column(
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextClickable(text = "Set Time"){
                DIALOG_TIMER = true
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextClickable(text = "Add Display"){
                Display.AddDisplay()
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextClickable(text = "Full Screen"){
                FULL_SCREEN = !FULL_SCREEN
            }
        }
    }
}