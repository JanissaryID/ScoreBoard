// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import controller.Display
import controller.Team
import controller.Timer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import screen.ControllerScreen
import screen.ScoreBoardScreen

@Composable
@Preview
fun DesktopController(
    TeamA: List<Team>,
    TeamB: List<Team>,
    Timer: List<Timer>,
    Display: Display
) {
    val PrimaryDark = Color(0xFF18122B)

    MaterialTheme(colors = lightColors(background = PrimaryDark)) {
        ControllerScreen(
            Timer = Timer,
            TeamA = TeamA,
            TeamB = TeamB,
            Display = Display,
            modifier = Modifier.background(MaterialTheme.colors.background)
        )
    }
}

@Composable
@Preview
fun DesktopView(
    TeamA: List<Team>,
    TeamB: List<Team>,
    Timer: List<Timer>,
    Display: Display
) {
    val PrimaryDark = Color(0xFF18122B)

    MaterialTheme(colors = lightColors(background = PrimaryDark)) {
//        Box(Modifier.fillMaxSize()){
////            Column(modifier = Modifier.fillMaxSize()) {
//////                Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
////////                    ScoreBoardScreen(
////////                        TeamA = TeamA[0],
////////                        TeamB = TeamB[0],
////////                        Timer = Timer[0],
////////                        Display = Display
////////                    )
//////                }
////            }
//        }
    }
}

fun main() = application {
    val stateScreen = rememberWindowState(
        placement = WindowPlacement.Maximized,
        position = WindowPosition(
            alignment = Alignment.CenterEnd
        )
    )
    val stateController = rememberWindowState(
        size = DpSize(1050.dp, 550.dp),
        isMinimized = false,
        position = WindowPosition(Alignment.Center)
    )
    val iconController = painterResource("icon_ball.png")

    val Display = remember { Display() }
//    Display.AddDisplay()

    println("On Create")

    Window(
        onCloseRequest = ::exitApplication,
        resizable = false,
        title = "SSA Djarum",
        focusable = true,
        state = stateController,
        icon = iconController
//        undecorated = true //frameless
    ) {
        DesktopController(
            TeamA = Display.listTeamA,
            TeamB = Display.listTeamB,
            Timer = Display.listTimer,
            Display = Display
        )
    }

    if(FULL_SCREEN){
        Window(
            onCloseRequest = ::exitApplication,
            resizable = false,
            title = "Display",
            focusable = false,
            state = stateScreen,
            icon = iconController,
            undecorated = true //frameless
        ) {
            DesktopView(
                TeamA = Display.listTeamA,
                TeamB = Display.listTeamB,
                Timer = Display.listTimer,
                Display = Display
            )
        }
    }
    else{
        Window(
            onCloseRequest = ::exitApplication,
            resizable = true,
            title = "Display",
            focusable = false,
            state = stateScreen,
            icon = iconController,
            undecorated = false //frameless
        ) {
            DesktopView(
                TeamA = Display.listTeamA,
                TeamB = Display.listTeamB,
                Timer = Display.listTimer,
                Display = Display
            )
        }
    }
}

@Composable
fun ChoseScreen(
    TeamA: List<Team>,
    TeamB: List<Team>,
    Timer: List<Timer>,
    Display: Display
){
    when(Display.listDisplay.size){
        1 -> {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                    ScoreBoardScreen(
                        TeamA = TeamA[0],
                        TeamB = TeamB[0],
                        Timer = Timer[0],
                        Display = Display
                    )
                }
            }
        }
        2 -> {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                    ScoreBoardScreen(
                        TeamA = TeamA[0],
                        TeamB = TeamB[0],
                        Timer = Timer[0],
                        Display = Display
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    ScoreBoardScreen(
                        TeamA = TeamA[1],
                        TeamB = TeamB[1],
                        Timer = Timer[1],
                        Display = Display
                    )
                }
            }
        }
        3 -> {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                    ScoreBoardScreen(
                        TeamA = TeamA[0],
                        TeamB = TeamB[0],
                        Timer = Timer[0],
                        Display = Display
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    ScoreBoardScreen(
                        TeamA = TeamA[1],
                        TeamB = TeamB[1],
                        Timer = Timer[1],
                        Display = Display
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                    ScoreBoardScreen(
                        TeamA = TeamA[2],
                        TeamB = TeamB[2],
                        Timer = Timer[2],
                        Display = Display
                    )
                }
            }
        }
        4 -> {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                    ScoreBoardScreen(
                        TeamA = TeamA[0],
                        TeamB = TeamB[0],
                        Timer = Timer[0],
                        Display = Display
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    ScoreBoardScreen(
                        TeamA = TeamA[1],
                        TeamB = TeamB[1],
                        Timer = Timer[1],
                        Display = Display
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
                    ScoreBoardScreen(
                        TeamA = TeamA[2],
                        TeamB = TeamB[2],
                        Timer = Timer[2],
                        Display = Display
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    ScoreBoardScreen(
                        TeamA = TeamA[3],
                        TeamB = TeamB[3],
                        Timer = Timer[3],
                        Display = Display
                    )
                }
            }
        }
        else -> ""
    }
}

