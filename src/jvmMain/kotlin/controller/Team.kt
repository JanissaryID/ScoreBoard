package controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.darkrockstudios.libraries.mpfilepicker.FilePicker

class Team {

    var LogoTeam by mutableStateOf("")
    var NameTeam by mutableStateOf("My team")
    var ScoreTeam by mutableStateOf(0)
    var RefreshImage by mutableStateOf(false)

    fun UpScore(){
        this@Team.ScoreTeam++
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