package screen

import SIZE_IMAGE_CENTER
import SIZE_IMAGE_TEAM
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
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
import androidx.compose.ui.unit.dp
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
    modifier: Modifier = Modifier
) {
    val sizeFontScore = MaterialTheme.typography.h1.fontSize
    val sizeFontTeam = MaterialTheme.typography.h5.fontSize

    var PlayerA: ArrayList<ModelPlayer> = arrayListOf()
    PlayerA.addAll(Player.listData.filter { data -> data.Team })

    var PlayerB: ArrayList<ModelPlayer> = arrayListOf()
    PlayerB.addAll(Player.listData.filter { data -> !data.Team })

    var refreshImage by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().wrapContentHeight().weight(10f),
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
                Text(
                    text = "${TeamA.NameTeam}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = sizeFontTeam,
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Center,
                )
                Row(
                    modifier = Modifier.wrapContentSize().padding(start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "${TeamA.ScoreTeam}",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = sizeFontScore,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = modifier.width(120.dp),
                        textAlign = TextAlign.Start
                    )
                }
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
                            modifier = Modifier.size(SIZE_IMAGE_CENTER.dp),
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
                    isVisible = false
                )
                Row(
                    modifier = modifier.padding(horizontal = 16.dp)
                        .wrapContentSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    PlayerLazyColumn(
                        modifier = modifier,
                        playerModel = PlayerA,
                        aligment = true
                    )
                    Divider(
                        color = Color.White,
                        modifier = modifier
                            .height(100.dp)  //fill the max height
                            .width(2.dp)
                    )
                    PlayerLazyColumn(
                        modifier = modifier,
                        playerModel = PlayerB,
                        aligment = false
                    )
                }
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
                            modifier = Modifier.size(SIZE_IMAGE_TEAM.dp),
                            alignment = Alignment.Center
                        )
                    }
                }
                Text(
                    text = "${TeamB.NameTeam}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = sizeFontTeam,
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Center,
                )
                Row(
                    modifier = Modifier.wrapContentSize().padding(end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "${TeamB.ScoreTeam}",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = sizeFontScore,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = modifier.width(120.dp),
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}