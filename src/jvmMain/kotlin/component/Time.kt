package component

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Time(
    isVisible: Boolean,
    formattedTime: String,
    isActive: Boolean,
    isEndTime: Boolean,
    isEnable: Boolean,
    onStartClick: () -> Unit,
    onPauseClick: () -> Unit,
    onResetClick: () -> Unit,
    sizeDisplay: Int,
    modifier: Modifier
) {
    val sizeFontTime = MaterialTheme.typography.h4.fontSize

    val sizeTime: List<TextUnit> = listOf(
        108.sp,
        72.sp,
        72.sp,
        72.sp,
    )

//    val modifier = Modifier

//    println("Ent Time : ${isEndTime}")

    Column(modifier = modifier.width(intrinsicSize = IntrinsicSize.Min)) {
        if(isVisible){
            Text(
                text = "$formattedTime",
                fontWeight = FontWeight.SemiBold,
                fontSize = sizeFontTime,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.width(90.dp)
            )
        }
        else{
            Text(
                text = "$formattedTime",
                fontWeight = FontWeight.SemiBold,
                fontSize = sizeTime[sizeDisplay],
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(290.dp)
            )
        }
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
                    size = 32,
                    isEnable = isEnable
                ){
                    if(!isEndTime){
                        if(isActive) onPauseClick() else onStartClick()
                    }
                }
            }
        }
    }
}