package screen

import DIALOG_TIMER
import SELECTED_SCREEN
import SIZE_IMAGE_CENTER
import SIZE_IMAGE_TEAM
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import component.*
import controller.Display
import controller.Team
import controller.Timer
import `object`.ModelPlayer
import `object`.Player
import view.PlayerLazyColumn
import java.io.File

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun ControllerScreen(
    Timer: List<Timer>,
    TeamA: List<Team>,
    TeamB: List<Team>,
    Display: Display,
    modifier: Modifier = Modifier
) {
    val sizeFontScore = MaterialTheme.typography.h1.fontSize
    val sizeFontTeam = MaterialTheme.typography.h5.fontSize

    var PlayerA: ArrayList<ModelPlayer> = arrayListOf()
    PlayerA.addAll(Player.listData.filter { data -> data.Team })

    var PlayerB: ArrayList<ModelPlayer> = arrayListOf()
    PlayerB.addAll(Player.listData.filter { data -> !data.Team })

    var showFilePicker by remember { mutableStateOf(false) }
    var refreshImage by remember { mutableStateOf(false) }
    val fileType = listOf("png")

    val selectionHalfGame = listOf("First Half", "Second Half")
    val extraGame = listOf("Extra 1", "Extra 2")

    var half_game: Int by mutableStateOf(0)
    var extra_game: Int by mutableStateOf(-1)

    var selected_index_extra by remember {mutableStateOf(extra_game)}
    val on_click_index_extra = { index: Int ->
        selected_index_extra = index
        extra_game = index
        if (selected_index_extra == 0) {
            Timer[SELECTED_SCREEN].FirstExtraGame()
            Timer[SELECTED_SCREEN].isHalfGame = false
        } else {
            Timer[SELECTED_SCREEN].SecondExtraGame()
            Timer[SELECTED_SCREEN].isHalfGame = false
        }
        Timer[SELECTED_SCREEN].isEndTime = false
    }

    var selected_index_half by remember {mutableStateOf(half_game)}
    val on_click_index_half = { index: Int ->
        selected_index_half = index
        half_game = index
        if (selected_index_half == 0) {
            Timer[SELECTED_SCREEN].FirstHalfGame()
            Timer[SELECTED_SCREEN].isHalfGame = false
            selected_index_extra = -1
        } else {
            Timer[SELECTED_SCREEN].SecondHalfGame()
            Timer[SELECTED_SCREEN].isHalfGame = true
        }
        Timer[SELECTED_SCREEN].isEndTime = false
    }

//    var listDisplay = mutableListOf<String>()
//    listDisplay.add("Display 1")

    Row(modifier = modifier.fillMaxSize()) {
//        println("List Display = ${Display.listDisplay}")

        ComponentSideMenu(Display = Display)

        Column(
            modifier = modifier.fillMaxHeight()
                .width(850.dp).padding(16.dp)
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
//                println("Refresh = ${TeamA.RefreshImage}")
                    Box(
                        modifier = Modifier
                            .pointerHoverIcon(
                                icon = PointerIcon.Hand
                            )
                            .size(SIZE_IMAGE_TEAM.dp)
                            .clickable {
//                            STATE_IMAGE_PICKER = true
                                showFilePicker = true
                                Display.stateImagePicker = 0
                                TeamA[SELECTED_SCREEN].RefreshImage = true
                            },
                    ){
//                    println("${TeamA.LogoTeam}")
                        if(!TeamA[SELECTED_SCREEN].LogoTeam.isNullOrEmpty() && !TeamA[SELECTED_SCREEN].RefreshImage){
                            AsyncImage(
                                load = { loadImageBitmap(File("${TeamA[SELECTED_SCREEN].LogoTeam}")) },
                                painterFor = { remember { BitmapPainter(it) } },
                                contentDescription = "Image Team",
//                            Team = TeamA
                            )
                        }
                        else{
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
                        text = "${TeamA[SELECTED_SCREEN].NameTeam}",
                        fontWeight = FontWeight.SemiBold,
//                    maxLines = 1,
                        fontSize = sizeFontTeam,
                        color = MaterialTheme.colors.onPrimary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .pointerHoverIcon(
                                icon = PointerIcon.Hand
                            )
                            .clickable {
                                Display.dialogTeamName = true
                                Display.stateTeamClick = false
                            }
                    )
//                Spacer(modifier = modifier.height(2.dp))
                    Row(
                        modifier = Modifier.wrapContentSize().padding(start = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        ButtonScore(
                            onUpClick = TeamA[SELECTED_SCREEN]::UpScore,
                            onDownClick = TeamA[SELECTED_SCREEN]::DownScore
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "${TeamA[SELECTED_SCREEN].ScoreTeam}",
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
                    Box(
                        modifier = Modifier
                            .pointerHoverIcon(
                                icon = PointerIcon.Hand
                            )
                            .size(SIZE_IMAGE_CENTER.dp)
                            .clickable {
                                showFilePicker = true
                                Display.stateImagePicker = 2
                                refreshImage = true
                            },
                    ){
//                    println("image Center = $IMAGE_CENTER")
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
                                modifier = Modifier.size(SIZE_IMAGE_CENTER.dp),
                                alignment = Alignment.Center
                            )
                        }
                    }
                    Time(
                        formattedTime = Timer[SELECTED_SCREEN].formattedTime,
                        onStartClick = Timer[SELECTED_SCREEN]::Start,
                        onPauseClick = Timer[SELECTED_SCREEN]::Pause,
                        onResetClick = Timer[SELECTED_SCREEN]::Reset,
                        isActive = Timer[SELECTED_SCREEN].isActive,
                        isEndTime = Timer[SELECTED_SCREEN].isEndTime,
                        isVisible = true,
                        modifier = modifier,
                        isEnable = Timer[SELECTED_SCREEN].isDisable,
                        sizeDisplay = Timer.size
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
                    Box(
                        modifier = Modifier
                            .pointerHoverIcon(
                                icon = PointerIcon.Hand
                            )
                            .size(SIZE_IMAGE_TEAM.dp)
                            .clickable {
//                            STATE_IMAGE_PICKER = true
                                showFilePicker = true
                                Display.stateImagePicker = 1
                                TeamB[SELECTED_SCREEN].RefreshImage = true
                            },
                    ){
//                    println("${TeamB.LogoTeam}")
                        if(!TeamB[SELECTED_SCREEN].LogoTeam.isNullOrEmpty() && !TeamB[SELECTED_SCREEN].RefreshImage){
                            AsyncImage(
                                load = { loadImageBitmap(File("${TeamB[SELECTED_SCREEN].LogoTeam}")) },
                                painterFor = { remember { BitmapPainter(it) } },
                                contentDescription = "Image Team",
                            )
                        }
                        else{
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
                        text = "${TeamB[SELECTED_SCREEN].NameTeam}",
                        fontWeight = FontWeight.SemiBold,
//                    maxLines = 1,
                        fontSize = sizeFontTeam,
                        color = MaterialTheme.colors.onPrimary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .pointerHoverIcon(
                                icon = PointerIcon.Hand
                            )
                            .clickable {
                                Display.stateTeamClick = true
                                Display.dialogTeamName = true
                            }
                    )
                    Row(
                        modifier = Modifier.wrapContentSize().padding(end = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = "${TeamB[SELECTED_SCREEN].ScoreTeam}",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = sizeFontScore,
                            color = MaterialTheme.colors.onPrimary,
                            modifier = modifier.width(120.dp),
                            textAlign = TextAlign.End
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        ButtonScore(
                            onUpClick = TeamB[SELECTED_SCREEN]::UpScore,
                            onDownClick = TeamB[SELECTED_SCREEN]::DownScore
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.wrapContentWidth()
                ){
                    itemsIndexed(items = selectionHalfGame){index, menuHalfGame ->
                        ComponentSelectedText(
                            titleMenu = menuHalfGame,
                            index = if(selected_index_half != index){
                                index
                            }  else index,
                            onClick = on_click_index_half,
                            Timer = Timer[SELECTED_SCREEN],
                            selected = if(selected_index_half == index) false else true,
                            clicked = if((Timer[SELECTED_SCREEN].halfStat == index) && !Timer[SELECTED_SCREEN].isActive) true else false
                        )
                    }
                }

//            println("${Timer.isHalfGame} -- ${Timer.isEndTime} -- ${Timer.extraStat}")
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.wrapContentWidth()
                ){
                    itemsIndexed(items = extraGame){index, menuExtra ->
                        ComponentSelectedText(
                            titleMenu = menuExtra,
                            index = if(selected_index_extra != index){
                                index
                            }  else index,
                            onClick = on_click_index_extra,
                            Timer = Timer[SELECTED_SCREEN],
                            clicked = if((Timer[SELECTED_SCREEN].extraStat == index) && !Timer[SELECTED_SCREEN].isActive && Timer[SELECTED_SCREEN].isEndTime && (Timer[SELECTED_SCREEN].statTimeNow == 1)) true else false,
                            selected = if(selected_index_extra == index) false else true
                        )
                    }
                }

//                TextClickable(text = "Show Clock"){
//
//                }

            }
        }
    }

    if(Display.dialogTeamName){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            DialogWindow1(
                Team = if(!Display.stateTeamClick) TeamA[SELECTED_SCREEN] else TeamB[SELECTED_SCREEN],
                Display = Display
            )
        }
    }

    if(DIALOG_TIMER){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            DialogTimer(Timer = Timer)
        }
    }

    FilePicker(showFilePicker, fileExtensions = fileType) { path ->
        showFilePicker = false

        try {
            if(Display.stateImagePicker == 0){
//                println("Path = ${path!!.platformFile}")
                TeamA[SELECTED_SCREEN].LogoTeam = path!!.platformFile.toString()
            }
            else if(Display.stateImagePicker == 1){
//                println("Path = ${path!!.platformFile}")
                TeamB[SELECTED_SCREEN].LogoTeam = path!!.platformFile.toString()
            }
            else if(Display.stateImagePicker == 2){
                Display.imageCenter = path!!.platformFile.toString()
//                println("Path = ${path!!.platformFile}")
            }
        }
        catch (e: Exception){
            println("Error = $e")
        }

        TeamA[SELECTED_SCREEN].RefreshImage = false
        TeamB[SELECTED_SCREEN].RefreshImage = false
        refreshImage = false
    }
}