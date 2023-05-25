// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
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
        Box(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colors.background)
        ){
            ControllerScreen(
                Timer = Timer,
                TeamA = TeamA,
                TeamB = TeamB,
                Display = Display,
                modifier = Modifier.background(MaterialTheme.colors.background)
            )
        }
    }
}

@Composable
@Preview
fun DesktopView(
    TeamA: List<Team>,
    TeamB: List<Team>,
    Timer: List<Timer>,
    Display: Display,
    modifier: Modifier = Modifier
) {
    val PrimaryDark = Color(0xFF18122B)

    MaterialTheme(colors = lightColors(background = PrimaryDark)) {
        ChoseScreen(
            Timer = Timer,
            TeamA = TeamA,
            TeamB = TeamB,
            Display = Display,
//            modifier = modifier.background(MaterialTheme.colors.background)
        )

//        Row(modifier = modifier.fillMaxWidth()) {
//            ScoreBoardScreen(
//                TeamA = TeamA[0],
//                TeamB = TeamB[0],
//                Timer = Timer[0],
//                Display = Display,
//                modifier = modifier.background(MaterialTheme.colors.background).weight(1f)
//            )
//            Spacer(modifier = modifier.width(8.dp))
//            ScoreBoardScreen(
//                TeamA = TeamA[0],
//                TeamB = TeamB[0],
//                Timer = Timer[0],
//                Display = Display,
//                modifier = modifier.background(MaterialTheme.colors.background).weight(1f)
//            )
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
            alwaysOnTop = true,
            undecorated = true //frameless
        ) {
            var modifier = Modifier.background(MaterialTheme.colors.background)
            DesktopView(
                TeamA = Display.listTeamA,
                TeamB = Display.listTeamB,
                Timer = Display.listTimer,
                Display = Display,
                modifier = modifier
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
            alwaysOnTop = true,
            undecorated = false //frameless
        ) {
            var modifier = Modifier.background(MaterialTheme.colors.background)
            DesktopView(
                TeamA = Display.listTeamA,
                TeamB = Display.listTeamB,
                Timer = Display.listTimer,
                Display = Display,
                modifier = modifier
            )
        }
    }
}

@Composable
fun ChoseScreen(
    TeamA: List<Team>,
    TeamB: List<Team>,
    Timer: List<Timer>,
    Display: Display,
    modifier: Modifier = Modifier
){
    println("Size : ${Display.listDisplay.size}")
    when(Display.listDisplay.size){
        1 -> {
            Column(modifier = modifier.fillMaxSize()) {
                Row(modifier = modifier.fillMaxWidth().weight(1f)) {
                    ScoreBoardScreen(
                        TeamA = TeamA[0],
                        TeamB = TeamB[0],
                        Timer = Timer[0],
                        Display = Display,
                        modifier = modifier.background(MaterialTheme.colors.background).weight(1f)
                    )
                }
            }
            println("Size : 1 Here")
        }
        2 -> {
            Column(modifier = modifier.fillMaxSize()) {
                Row(modifier = modifier.fillMaxWidth().weight(1f)) {
                    ScoreBoardScreen(
                        TeamA = TeamA[0],
                        TeamB = TeamB[0],
                        Timer = Timer[0],
                        Display = Display,
                        modifier = modifier.background(MaterialTheme.colors.background).weight(1f)
                    )
                    Spacer(modifier = modifier.width(8.dp))
                    ScoreBoardScreen(
                        TeamA = TeamA[1],
                        TeamB = TeamB[1],
                        Timer = Timer[1],
                        Display = Display,
                        modifier = modifier.background(MaterialTheme.colors.background).weight(1f)
                    )
                }
            }
            println("Size : 2 Here")
        }
        3 -> {
            Column(modifier = modifier.fillMaxSize()) {
                Row(modifier = modifier.fillMaxWidth().weight(1f)) {
                    ScoreBoardScreen(
                        TeamA = TeamA[0],
                        TeamB = TeamB[0],
                        Timer = Timer[0],
                        Display = Display,
                        modifier = modifier.background(MaterialTheme.colors.background).weight(1f)
                    )
                    Spacer(modifier = modifier.width(8.dp))
                    ScoreBoardScreen(
                        TeamA = TeamA[1],
                        TeamB = TeamB[1],
                        Timer = Timer[1],
                        Display = Display,
                        modifier = modifier.background(MaterialTheme.colors.background).weight(1f)
                    )
                }
                Spacer(modifier = modifier.height(8.dp))
                Row(modifier = modifier.fillMaxWidth().weight(1f)) {
                    ScoreBoardScreen(
                        TeamA = TeamA[2],
                        TeamB = TeamB[2],
                        Timer = Timer[2],
                        Display = Display,
                        modifier = modifier.background(MaterialTheme.colors.background).weight(1f)
                    )
                }
            }
            println("Size : 3 Here")
        }
        4 -> {
            Column(modifier = modifier.fillMaxSize()) {
                Row(modifier = modifier.fillMaxWidth().weight(1f)) {
                    ScoreBoardScreen(
                        TeamA = TeamA[0],
                        TeamB = TeamB[0],
                        Timer = Timer[0],
                        Display = Display,
                        modifier = modifier.background(MaterialTheme.colors.background).weight(1f)
                    )
                    Spacer(modifier = modifier.width(8.dp))
                    ScoreBoardScreen(
                        TeamA = TeamA[1],
                        TeamB = TeamB[1],
                        Timer = Timer[1],
                        Display = Display,
                        modifier = modifier.background(MaterialTheme.colors.background).weight(1f)
                    )
                }
                Spacer(modifier = modifier.height(8.dp))
                Row(modifier = modifier.fillMaxWidth().weight(1f)) {
                    ScoreBoardScreen(
                        TeamA = TeamA[2],
                        TeamB = TeamB[2],
                        Timer = Timer[2],
                        Display = Display,
                        modifier = modifier.background(MaterialTheme.colors.background).weight(1f)
                    )
                    Spacer(modifier = modifier.width(8.dp))
                    ScoreBoardScreen(
                        TeamA = TeamA[3],
                        TeamB = TeamB[3],
                        Timer = Timer[3],
                        Display = Display,
                        modifier = modifier.background(MaterialTheme.colors.background).weight(1f)
                    )
                }
            }
            println("Size : 4 Here")
        }
        else -> ""
    }
}

