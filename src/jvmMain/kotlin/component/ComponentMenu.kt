package component

import FULL_SCREEN_CONTROLLER
import SELECTED_SCREEN
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ComponentMenu(
    text: String,
    fontColor: Color = Color.White,
    index: Int,
    selected: Boolean = false,
    onClick: (Int) -> Unit
){
    val sizeFontFooter = MaterialTheme.typography.subtitle2.fontSize

    Surface(
        color = if (!selected) Color.White.copy(alpha = 0.2f) else Color.Transparent,
        modifier = Modifier.fillMaxWidth().height(38.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxSize()
                .pointerHoverIcon(
                    icon = PointerIcon.Hand
                )
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
//                            println("Single Press")
                            onClick.invoke(index)
                        },
                        onDoubleTap = { tapOffset ->
//                            println("Double Click")
                            if(SELECTED_SCREEN == index){
                                FULL_SCREEN_CONTROLLER = true
                            }
                        }
                    )
                }

        ) {
            if(!selected){
                Divider(
                    color = Color.White,
                    modifier = Modifier
                        .height(38.dp)  //fill the max height
                        .width(4.dp)
                )
            }
            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = sizeFontFooter,
                color = if(!selected) fontColor else fontColor.copy(alpha = 0.4f),
                textAlign = TextAlign.Center,
                modifier = Modifier.wrapContentHeight().fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 8.dp),
            )
        }
    }
}