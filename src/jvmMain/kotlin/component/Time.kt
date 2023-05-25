package component

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun Time(
    isVisible: Boolean,
    formattedTime: String,
    isActive: Boolean,
    isEndTime: Boolean,
    onStartClick: () -> Unit,
    onPauseClick: () -> Unit,
    onResetClick: () -> Unit,
) {
    val sizeFontTime = MaterialTheme.typography.h4.fontSize

    val modifier = Modifier

//    println("Ent Time : ${isEndTime}")

    Column(modifier = modifier.width(intrinsicSize = IntrinsicSize.Min)) {
        Text(
            text = "$formattedTime",
            fontWeight = FontWeight.SemiBold,
            fontSize = sizeFontTime,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.width(90.dp)
        )
        if(isVisible){
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ImageClickable(
                    image = "stop.svg",
                    description = "Stop Timer",
                    size = 32
                ){
                    onResetClick()
                }

                ImageClickable(
                    image = if(isActive) "pause.svg" else "play.svg",
                    description = "play/Pause Timer",
                    size = 32
                ){
                    if(!isEndTime){
                        if(isActive) onPauseClick() else onStartClick()
                    }
                }
            }
        }
    }
}