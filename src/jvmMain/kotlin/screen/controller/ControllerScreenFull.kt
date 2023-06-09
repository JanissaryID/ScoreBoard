package screen

import DIALOG_INJURY
import DIALOG_TIMER
import FULL_SCREEN_CONTROLLER
import SELECTED_SCREEN
import SIZE_IMAGE_CENTER
import SIZE_IMAGE_CENTER_F
import SIZE_IMAGE_TEAM
import SIZE_IMAGE_TEAM_F
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
import androidx.compose.ui.unit.sp
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import component.*
import classes.Display
import classes.Team
import classes.Timer
import `object`.ModelPlayer
import `object`.Player
import view.PlayerLazyColumn
import java.io.File

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun ControllerScreenFull(
    Timer: List<Timer>,
    TeamA: List<Team>,
    TeamB: List<Team>,
    Display: Display,
    moreButton: List<String>,
    selectionHalfGame: List<String>,
    injuryTime: List<String>,
    modifier: Modifier = Modifier
) {
    val sizeFontScoreF = 144.sp
    val sizeFontTeamF = 56.sp

    var showFilePicker by remember { mutableStateOf(false) }
    var refreshImage by remember { mutableStateOf(false) }

    var selected_index_half by remember { mutableStateOf(Timer[SELECTED_SCREEN].HalfGame) }

    val on_click_index_half = { index: Int ->
        selected_index_half = index
        Timer[SELECTED_SCREEN].HalfGame = selected_index_half

        try{
            Timer[SELECTED_SCREEN].ChoseTime(index = selected_index_half)
            Timer[SELECTED_SCREEN].isEndTime = false
            Timer[SELECTED_SCREEN].showAdditional = false
            Timer[SELECTED_SCREEN].SetTimer()
            Timer[SELECTED_SCREEN].SetInjury(injuryTime = 0)
        }
        catch (e: Exception){
            println("Error : $e")
        }
    }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if(!Display.listDisplay.isNullOrEmpty()) {
            Column(
                modifier = Modifier.wrapContentSize().padding(16.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextClickable(
                        text = "${moreButton[0]}",
                        clicked = !Timer[SELECTED_SCREEN].isActive && !Timer[SELECTED_SCREEN].isActiveAdditional
                    ){
                        TeamA[SELECTED_SCREEN].Reset()
                        TeamB[SELECTED_SCREEN].Reset()
                        Timer[SELECTED_SCREEN].Reset()
                    }

                    Text(
                        text = "SSA DJARUM STADION",
                        fontWeight = FontWeight.Normal,
                        fontSize = MaterialTheme.typography.h6.fontSize,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = modifier.wrapContentWidth(),
                        textAlign = TextAlign.Center
                    )

                    TextClickable(
                        text = "${moreButton[2]}",
                        clicked = !Timer[SELECTED_SCREEN].isActive || !Timer[SELECTED_SCREEN].isActiveAdditional
                    ){
                        try {
//                                    println("Normal Screen")
                            FULL_SCREEN_CONTROLLER = false
                        }
                        catch (e: Exception){
                            println("Error : $e")
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                BoxWithConstraints(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.wrapContentSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.wrapContentWidth().wrapContentHeight(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.wrapContentHeight()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .pointerHoverIcon(
                                            icon = PointerIcon.Hand
                                        )
                                        .size(SIZE_IMAGE_TEAM_F.dp)
                                        .clickable {
                                            showFilePicker = true
                                            Display.stateImagePicker = 0
                                            TeamA[SELECTED_SCREEN].RefreshImage = true
                                        },
                                ){
                                    if(!TeamA[SELECTED_SCREEN].LogoTeam.isNullOrEmpty() && !TeamA[SELECTED_SCREEN].RefreshImage){
                                        AsyncImage(
                                            load = { loadImageBitmap(File("${TeamA[SELECTED_SCREEN].LogoTeam}")) },
                                            painterFor = { remember { BitmapPainter(it) } },
                                            contentDescription = "Image Team",
                                        )
                                    }
                                    else{
                                        Image(
                                            painter = painterResource("PSIS.png"),
                                            contentDescription = "Default Image",
                                            contentScale = ContentScale.Fit,
                                            modifier = Modifier.size(SIZE_IMAGE_TEAM_F.dp),
                                            alignment = Alignment.Center
                                        )
                                    }
                                }
                                Text(
                                    text = "${TeamA[SELECTED_SCREEN].NameTeam}",
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = sizeFontTeamF,
                                    color = MaterialTheme.colors.onPrimary,
                                    maxLines = 2,
                                    minLines = 2,
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
                                        fontSize = sizeFontScoreF,
                                        color = MaterialTheme.colors.onPrimary,
                                        modifier = modifier.width(220.dp),
                                        textAlign = TextAlign.Start
                                    )
                                }
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.wrapContentHeight()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .pointerHoverIcon(
                                            icon = PointerIcon.Hand
                                        )
                                        .size(SIZE_IMAGE_CENTER_F.dp)
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
                                            modifier = Modifier.size(SIZE_IMAGE_CENTER_F.dp),
                                            alignment = Alignment.Center
                                        )
                                    }
                                }
                                Time(
                                    isVisible = true,
                                    modifier = modifier,
                                    Time = Timer[SELECTED_SCREEN]
                                )
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.wrapContentHeight()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .pointerHoverIcon(
                                            icon = PointerIcon.Hand
                                        )
                                        .size(SIZE_IMAGE_TEAM_F.dp)
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
                                            modifier = Modifier.size(SIZE_IMAGE_TEAM_F.dp),
                                            alignment = Alignment.Center
                                        )
                                    }
                                }
                                Text(
                                    text = "${TeamB[SELECTED_SCREEN].NameTeam}",
                                    fontWeight = FontWeight.SemiBold,
                                    maxLines = 2,
                                    minLines = 2,
                                    fontSize = sizeFontTeamF,
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
                                        fontSize = sizeFontScoreF,
                                        color = MaterialTheme.colors.onPrimary,
                                        modifier = modifier.width(220.dp),
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
                            modifier = Modifier.wrapContentWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(32.dp)
                        ) {
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
                                        selected = if(selected_index_half == index) false else true,
                                        clicked = !Timer[SELECTED_SCREEN].isActive && !Timer[SELECTED_SCREEN].isActiveAdditional
                                    )
                                }
                            }
                            LazyRow(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.wrapContentWidth()
                            ){
                                itemsIndexed(items = injuryTime){index, menuExtra ->
                                    TextClickable(
                                        text = "$menuExtra",
                                        clicked = true
                                    ){
                                        if(index == 0){
                                            DIALOG_INJURY = true
                                        }
                                        else{
                                            Timer[SELECTED_SCREEN].showAdditional = !Timer[SELECTED_SCREEN].showAdditional
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}