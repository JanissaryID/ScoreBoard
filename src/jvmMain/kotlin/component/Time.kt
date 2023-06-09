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
import classes.Timer

@Composable
fun Time(
    isVisible: Boolean,
    Time: Timer,
    modifier: Modifier
) {
    val sizeFontTime = MaterialTheme.typography.h4.fontSize

//    val sizeTime: List<TextUnit> = listOf(
//        108.sp,
//        72.sp,
//        72.sp,
//        72.sp,
//    )

    Column(modifier = modifier.width(intrinsicSize = IntrinsicSize.Min), horizontalAlignment = Alignment.CenterHorizontally) {
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
                    if (Time.HalfGame > -1){
                        if(Time.GetMaxMilis() >= 60000L){
//                            println("${Time.GetHalfTime()} -- ${Time.GetMilis()}")
                            if(Time.isActive) Time.Pause() else Time.Start()
                        }
                    }
                }
            }
        }

        if(Time.isEndTime){
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if(Time.showAdditional){
                    Text(
                        text = "+${Time.ShowInjury()}",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.width(32.dp)
                    )
                }
                else{
                    Text(
                        text = "    ",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.width(32.dp)
                    )
                }
                Text(
                    text = "${Time.formattedTimeAdditional}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = sizeFontTime,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.width(90.dp)
                )

                ImageClickable(
                    image = if(Time.isActiveAdditional) "pause.svg" else "play.svg",
                    description = "play/Pause Timer",
                    size = 32,
                    isEnable = Time.isDisable
                ){
                    if(Time.isActiveAdditional) Time.PauseAdditional() else Time.StartAdditional()
                }
            }
        }
    }
}