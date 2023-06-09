package classes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Team {

    var LogoTeam by mutableStateOf("")
    var NameTeam by mutableStateOf("Team")
    var ScoreTeam by mutableStateOf(0)
    var RefreshImage by mutableStateOf(false)

    fun UpScore(){
        this@Team.ScoreTeam++
    }
    fun Reset(){
        this@Team.ScoreTeam = 0
//        this@Team.NameTeam = "Team"
    }

    fun DownScore(){
        if(this@Team.ScoreTeam <= 0){
            this@Team.ScoreTeam = 0
        }
        else{
            this@Team.ScoreTeam--
        }
    }
}