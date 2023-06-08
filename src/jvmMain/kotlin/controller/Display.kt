package controller

import androidx.compose.runtime.*

class Display {
    var listDisplay = mutableStateListOf<String>()
    var listTeamA = mutableStateListOf<Team>()
    var listTeamB = mutableStateListOf<Team>()
    var listTimer = mutableStateListOf<Timer>()

    var imageCenter by mutableStateOf("")
    var dialogTeamName: Boolean by mutableStateOf(false)
    var stateImagePicker: Int by mutableStateOf(0)
    var stateTeamClick by mutableStateOf(false)
    var HalfGame by mutableStateOf(-1)

    init {
        AddDisplay()
    }

    fun AddDisplay(){
        if(listDisplay.size < 4) {
            var countDisplay = listDisplay.size
            countDisplay++
            listTeamA.add(Team())
            listTeamB.add(Team())
            listTimer.add(Timer())
            listDisplay.add("${listTeamA[countDisplay - 1].NameTeam}")
//            println("add")
        }
    }

    fun ChangeNameDisplay(){

    }
}