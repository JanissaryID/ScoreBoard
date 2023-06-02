package screen

import SIZE_IMAGE_CENTER
import SIZE_IMAGE_TEAM
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import component.*
import controller.Display
import controller.Team
import controller.Timer
import `object`.ModelPlayer
import `object`.Player
import view.PlayerLazyColumn
import java.io.File

@Composable
@Preview
fun ScoreBoardScreen(
    Timer: Timer,
    TeamA: Team,
    TeamB: Team,
    Display: Display,
    sizeDisplay: Int,
    modifier: Modifier = Modifier
) {
    val sizeFontScore: List<TextUnit> = listOf(
        144.sp,
        126.sp,
        72.sp,
        72.sp,
    )
    val sizeFontTeam: List<TextUnit> = listOf(
        72.sp,
        72.sp,
        72.sp,
        72.sp,
    )

    var PlayerA: ArrayList<ModelPlayer> = arrayListOf()
    PlayerA.addAll(Player.listData.filter { data -> data.Team })

    var PlayerB: ArrayList<ModelPlayer> = arrayListOf()
    PlayerB.addAll(Player.listData.filter { data -> !data.Team })

    var refreshImage by remember { mutableStateOf(false) }

    Row(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxHeight()
        ) {
            Box(modifier = Modifier.size(SIZE_IMAGE_TEAM.dp),) {
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
                        modifier = Modifier.size(SIZE_IMAGE_TEAM.dp),
                        alignment = Alignment.Center
                    )
                }
            }
//            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${TeamA.NameTeam}",
                fontWeight = FontWeight.SemiBold,
                fontSize = sizeFontTeam[sizeDisplay],
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Center,
            )
//            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${TeamA.ScoreTeam}",
                fontWeight = FontWeight.SemiBold,
                fontSize = sizeFontScore[sizeDisplay],
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.width(120.dp),
                textAlign = TextAlign.Center
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxHeight()
        ) {
            Box(modifier = Modifier.size(SIZE_IMAGE_CENTER.dp),) {
                if (!Display.imageCenter.isNullOrEmpty() && !refreshImage) {
                    AsyncImage(
                        load = { loadImageBitmap(File("${Display.imageCenter}")) },
                        painterFor = { remember { BitmapPainter(it) } },
                        contentDescription = "Image Center",
                    )
                } else {
                    Image(
                        painter = painterResource("premier.png"),
                        contentDescription = "Default Image",
                        contentScale = ContentScale.Fit,
                        modifier = modifier.size(SIZE_IMAGE_CENTER.dp),
                        alignment = Alignment.Center
                    )
                }
            }
            Time(
                formattedTime = Timer.formattedTime,
                onStartClick = Timer::Start,
                onPauseClick = Timer::Pause,
                onResetClick = Timer::Reset,
                isActive = Timer.isActive,
                isEndTime = Timer.isEndTime,
                isVisible = false,
                isEnable = Timer.isDisable,
                modifier = Modifier,
                sizeDisplay = sizeDisplay
            )
//                Row(
//                    modifier = Modifier.padding(horizontal = 16.dp)
//                        .wrapContentSize(),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    PlayerLazyColumn(
//                        modifier = Modifier,
//                        playerModel = PlayerA,
//                        aligment = true
//                    )
//                    Divider(
//                        color = Color.White,
//                        modifier = Modifier
//                            .height(100.dp)  //fill the max height
//                            .width(2.dp)
//                    )
//                    PlayerLazyColumn(
//                        modifier = Modifier,
//                        playerModel = PlayerB,
//                        aligment = false
//                    )
//                }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxHeight()
        ) {
            Box(modifier = Modifier.size(SIZE_IMAGE_TEAM.dp),) {
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
                        modifier = modifier.size(SIZE_IMAGE_TEAM.dp),
                        alignment = Alignment.Center
                    )
                }
            }
//            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "${TeamB.NameTeam}",
                fontWeight = FontWeight.SemiBold,
                fontSize = sizeFontTeam[sizeDisplay],
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Center,
            )
//            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "${TeamB.ScoreTeam}",
                fontWeight = FontWeight.SemiBold,
                fontSize = sizeFontScore[sizeDisplay],
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.width(120.dp).wrapContentHeight(),
                textAlign = TextAlign.Center
            )
        }
    }
}