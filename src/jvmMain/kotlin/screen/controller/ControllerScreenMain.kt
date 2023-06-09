package screen

import DIALOG_INJURY
import DIALOG_TIMER
import FULL_SCREEN_CONTROLLER
import SELECTED_SCREEN
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import component.*
import classes.Display
import classes.Team
import classes.Timer
import `object`.ModelPlayer
import `object`.Player
import screen.controller.ControllerScreenWindowed

@Composable
@Preview
fun ControllerScreenMain(
    Timer: List<Timer>,
    TeamA: List<Team>,
    TeamB: List<Team>,
    Display: Display,
    modifier: Modifier = Modifier
) {
//    val sizeFontScoreF = 144.sp
//    val sizeFontTeamF = 56.sp

    val PlayerA: ArrayList<ModelPlayer> = arrayListOf()
    PlayerA.addAll(Player.listData.filter { data -> data.Team })

    val PlayerB: ArrayList<ModelPlayer> = arrayListOf()
    PlayerB.addAll(Player.listData.filter { data -> !data.Team })

    var showFilePicker by remember { mutableStateOf(false) }
    var refreshImage by remember { mutableStateOf(false) }
    val fileType = listOf("png")

    val selectionHalfGame = listOf("First Half", "Second Half", "Extra 1", "Extra 2")
    val moreButton = listOf("Reset All", "Delete", "Normal Screen")
    val injuryTime = listOf("Set Additional", "Show Additional")

    Row(modifier = modifier.fillMaxSize()) {

        if(FULL_SCREEN_CONTROLLER){
            ControllerScreenFull(
                Timer, TeamA, TeamB, Display, moreButton, selectionHalfGame, injuryTime
            )
        }
        else{
            ComponentSideMenu(Display = Display)

            ControllerScreenWindowed(
                Timer, TeamA, TeamB, Display, moreButton, selectionHalfGame, injuryTime
            )
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

    if(DIALOG_INJURY){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            DialogInjury(Timer = Timer)
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