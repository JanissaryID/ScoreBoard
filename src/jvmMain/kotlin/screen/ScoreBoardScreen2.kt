package screen

import DATE_NOW
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FixedScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import component.AsyncImage
import component.loadImageBitmap
import controller.Display
import controller.Team
import controller.Timer
import java.io.File

@Composable
@Preview
fun ScoreBoardScreen2(
    Timer: Timer,
    TeamA: Team,
    TeamB: Team,
    Display: Display,
    sizeDisplay: Int,
    modifier: Modifier = Modifier
) {

    val sizeImage: List<Int> = listOf(
        240,
        72,
        64,
        64,
    )

    val sizeFontTeam: List<TextUnit> = listOf(
        42.sp,
        26.sp,
        26.sp,
        26.sp,
    )

    val sizeFontScore: List<TextUnit> = listOf(
        180.sp,
        140.sp,
        140.sp,
        140.sp,
    )

    var refreshImage by remember { mutableStateOf(false) }

//    BoxWithConstraints(
//        modifier = modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//
//    }

    Column(
        modifier = modifier.fillMaxSize(),
//        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "SSA DJARUM STADION",
            fontWeight = FontWeight.Normal,
            fontSize = MaterialTheme.typography.h6.fontSize,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            textAlign = TextAlign.Center
        )

        BoxWithConstraints(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            val boxWithConstrains = this

            // Background Logo Team

            BoxWithConstraints(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize().weight(1f).background(Color.Transparent),
                        contentAlignment = Alignment.Center
                    ) {
                        if (!TeamA.LogoTeam.isNullOrEmpty() && !TeamA.RefreshImage) {
                            AsyncImage(
                                load = { loadImageBitmap(File("${TeamA.LogoTeam}")) },
                                painterFor = { remember { BitmapPainter(it) } },
                                contentDescription = "Image Team",
                                transparent = true
                            )
                        } else {
                            Image(
                                painter = painterResource("PSIS.png"),
                                contentDescription = "Default Image",
                                contentScale = ContentScale.Fit,
//                                modifier = Modifier.fillMaxSize(),
                                alignment = Alignment.Center,
                                alpha = 0.1f,
                            )
                        }
                    }

                    Box(
                        modifier = Modifier.fillMaxSize().weight(1f).background(Color.Transparent),
                        contentAlignment = Alignment.Center
                    ) {
                        if (!TeamB.LogoTeam.isNullOrEmpty() && !TeamB.RefreshImage) {
                            AsyncImage(
                                load = { loadImageBitmap(File("${TeamB.LogoTeam}")) },
                                painterFor = { remember { BitmapPainter(it) } },
                                contentDescription = "Image Team",
                                transparent = true
                            )
                        } else {
                            Image(
                                painter = painterResource("PSIS.png"),
                                contentDescription = "Default Image",
                                contentScale = ContentScale.Fit,
//                                modifier = Modifier.fillMaxSize(),
                                alignment = Alignment.Center,
                                alpha = 0.1f,
                            )
                        }
                    }
                }
            }
            ///////////////////////////////////////////////////////////////////////////////////////////////////////

            Column(
                modifier = Modifier.wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier.wrapContentWidth()
//                    .background(brush = Brush.horizontalGradient(
//                        0.0f to Color.Yellow,
//                        0.5f to Color.Cyan,
//                        1.0f to Color.Magenta,
//                        startX = 0f,
//                        endX = Float.POSITIVE_INFINITY
//                    )
//                )
                    ,
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.size(sizeImage[0].dp).background(Color.Transparent)) {
                        if (!TeamA.LogoTeam.isNullOrEmpty() && !TeamA.RefreshImage) {
                            AsyncImage(
                                load = { loadImageBitmap(File("${TeamA.LogoTeam}")) },
                                painterFor = { remember { BitmapPainter(it) } },
                                contentDescription = "Image Team",
                            )
                        } else {
                            Image(
                                painter = painterResource("PSIS.png"),
                                contentDescription = "Default Image",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.size(sizeImage[0].dp),
                                alignment = Alignment.Center
                            )
                        }
                    }

                    Text(
                        text = "${Timer.formattedTime}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 120.sp,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.wrapContentWidth(),
                        textAlign = TextAlign.Center
                    )

                    Box(modifier = Modifier.size(sizeImage[0].dp).background(Color.Transparent)) {
                        if (!TeamB.LogoTeam.isNullOrEmpty() && !TeamB.RefreshImage) {
                            AsyncImage(
                                load = { loadImageBitmap(File("${TeamB.LogoTeam}")) },
                                painterFor = { remember { BitmapPainter(it) } },
                                contentDescription = "Image Team",
                            )
                        } else {
                            Image(
                                painter = painterResource("PSIS.png"),
                                contentDescription = "Default Image",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.size(sizeImage[0].dp),
                                alignment = Alignment.Center
                            )
                        }
                    }
                }

                if(Timer.showAdditional){
                    Row(
                        modifier = Modifier.width(400.dp),
                        horizontalArrangement = Arrangement.spacedBy(32.dp)
                    ) {
                        Text(
                            text = "+${Timer.ShowInjury()}",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 72.sp,
                            textAlign = TextAlign.End,
                            color = MaterialTheme.colors.onPrimary,
                            modifier = Modifier.width(100.dp)
                        )
                        if(Timer.isEndTime){
                            Text(
                                text = "${Timer.formattedTimeAdditional}",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 72.sp,
                                textAlign = TextAlign.Start,
                                color = MaterialTheme.colors.onPrimary,
                                modifier = Modifier.width(200.dp)
                            )
                        }
                        else{
                            Text(
                                text = " ",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 72.sp,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colors.onPrimary,
                                modifier = Modifier.width(200.dp)
                            )
                        }
                    }
                }
                else{
                    Text(
                        text = " ",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 72.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.width(200.dp)
                    )
                }

                Row(
                    modifier = Modifier.wrapContentWidth(),
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                    verticalAlignment = Alignment.Top
                ) {

                    Column(
                        modifier = Modifier.wrapContentWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "${TeamA.ScoreTeam}",
                            fontWeight = FontWeight.Medium,
                            fontSize = sizeFontScore[sizeDisplay],
                            color = MaterialTheme.colors.onPrimary,
                            modifier = Modifier.width(120.dp),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "${TeamA.NameTeam}",
                            fontWeight = FontWeight.Medium,
                            fontSize = sizeFontTeam[sizeDisplay],
                            color = MaterialTheme.colors.onPrimary,
                            modifier = Modifier.width(350.dp),
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                            minLines = 2,
                        )
                    }

                    Column(
                        modifier = Modifier.wrapContentWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
//                        horizontalAlignment = Alignment.T
                    ){

                        Box(modifier = Modifier.size(160.dp).background(Color.Transparent)) {
                            if(!Display.imageCenter.isNullOrEmpty() && !refreshImage){
                                AsyncImage(
                                    load = { loadImageBitmap(File("${Display.imageCenter}")) },
                                    painterFor = { remember { BitmapPainter(it) } },
                                    contentDescription = "Image Center",
                                )
                            }
                            else{
                                Image(
                                    painter = painterResource("premier.png"),
                                    contentDescription = "Default Image",
                                    contentScale = ContentScale.Fit,
                                    modifier = androidx.compose.ui.Modifier.size(160.dp),
                                    alignment = Alignment.Center
                                )
                            }
                        }
                    }

                    Column(
                        modifier = Modifier.width(350.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "${TeamB.ScoreTeam}",
                            fontWeight = FontWeight.Medium,
                            fontSize = sizeFontScore[sizeDisplay],
                            color = MaterialTheme.colors.onPrimary,
                            modifier = Modifier.width(120.dp),
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "${TeamB.NameTeam}",
                            fontWeight = FontWeight.Medium,
                            fontSize = sizeFontTeam[sizeDisplay],
                            color = MaterialTheme.colors.onPrimary,
                            modifier = Modifier.wrapContentWidth(),
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                            minLines = 2,
                        )
                    }
                }
            }
        }
    }
}