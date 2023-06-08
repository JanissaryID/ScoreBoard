package component

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import controller.Timer

@Composable
fun Time(
    isVisible: Boolean,
    Time: Timer,
    modifier: Modifier
) {
    val sizeFontTime = MaterialTheme.typography.h4.fontSize

    val sizeTime: List<TextUnit> = listOf(
        108.sp,
        72.sp,
        72.sp,
        72.sp,
    )

    Column(modifier = modifier.width(intrinsicSize = IntrinsicSize.Min), horizontalAlignment = Alignment.CenterHorizontally) {
//        if(isVisible){
////            Text(
////                text = "+4 $formattedTime",
////                fontWeight = FontWeight.SemiBold,
////                fontSize = sizeFontTime,
////                color = MaterialTheme.colors.onPrimary,
////                modifier = Modifier.width(150.dp)
////            )
//            Text(
//                text = "$formattedTime",
//                fontWeight = FontWeight.SemiBold,
//                fontSize = sizeFontTime,
//                color = MaterialTheme.colors.onPrimary,
//                modifier = Modifier.width(90.dp)
//            )
//        }
//        else{
//            Text(
//                text = "$formattedTime",
//                fontWeight = FontWeight.SemiBold,
//                fontSize = sizeTime[sizeDisplay],
//                color = MaterialTheme.colors.onPrimary,
//                textAlign = TextAlign.Center,
//                modifier = Modifier.width(290.dp)
//            )
//        }
        if(isVisible){
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageClickable(
                    image = "stop.svg",
                    description = "Stop Timer",
                    size = 32
                ){
                    Time.Reset()
                }

                Text(
                    text = "${Time.formattedTime}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = sizeFontTime,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.width(90.dp)
                )

                ImageClickable(
                    image = if(Time.isActive) "pause.svg" else "play.svg",
                    description = "play/Pause Timer",
                    size = 32,
                    isEnable = Time.isDisable
                ){
                    if(!Time.isEndTime){
                        if(Time.isActive) Time.Pause() else Time.Start()
                    }
                }
            }
        }

//        println("Show Additional ${showAdditional}")
        if(Time.showAdditional){
            Text(
                text = "+${Time.ShowInjury()}",
                fontWeight = FontWeight.SemiBold,
                fontSize = sizeFontTime,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.width(90.dp)
            )
            if(Time.isEndTime){
                Text(
                    text = "${Time.formattedTimeAdditional}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = sizeFontTime,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.width(90.dp)
                )
            }
        }
    }
}