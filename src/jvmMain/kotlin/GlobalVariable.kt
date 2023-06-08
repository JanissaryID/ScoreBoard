import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

val SIZE_IMAGE_TEAM = 120
val SIZE_IMAGE_CENTER = 160

val SIZE_IMAGE_TEAM_F = 280
val SIZE_IMAGE_CENTER_F = 240

var FULL_SCREEN by mutableStateOf(false)
var FULL_SCREEN_CONTROLLER by mutableStateOf(false)

var SELECTED_SCREEN by mutableStateOf(0)

var HALF_TIME by mutableStateOf(0)
var EXTRA_TIME by mutableStateOf(0)

var DIALOG_TIMER by mutableStateOf(false)
var DIALOG_INJURY by mutableStateOf(false)

var DATE_NOW by mutableStateOf("")