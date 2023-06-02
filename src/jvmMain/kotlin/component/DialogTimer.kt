package component

import DIALOG_TIMER
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberDialogState
import controller.Display
import controller.Team
import controller.Timer

@Composable
fun DialogTimer(Timer: List<Timer>){
    val stateDialog = rememberDialogState(size = DpSize(350.dp, 280.dp), position = WindowPosition(Alignment.Center))

    val size = Timer.size

    Dialog(
        onCloseRequest = { DIALOG_TIMER = false },
        visible = DIALOG_TIMER,
        undecorated = true,
        state = stateDialog,
        transparent = true
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            var timeGame by remember { mutableStateOf("") }
            var extraGame by remember { mutableStateOf("") }

            Surface(
                modifier = Modifier.padding(16.dp).fillMaxSize(),
                shape = RoundedCornerShape(20.dp)
            ){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedTextField(
                        value = timeGame,
                        maxLines = 1,
                        label = { Text(text = "Half Time") },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = MaterialTheme.colors.background,
                            cursorColor = MaterialTheme.colors.background,
                            placeholderColor = MaterialTheme.colors.background,
                            backgroundColor = Color.White,
                            focusedLabelColor = MaterialTheme.colors.background,
                            focusedIndicatorColor = MaterialTheme.colors.background
                        ),
                        placeholder = {Text(text = "In Minute") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            timeGame = it.filter { it.isDigit() }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = extraGame,
                        maxLines = 1,
                        label = { Text(text = "Extra Time") },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = MaterialTheme.colors.background,
                            cursorColor = MaterialTheme.colors.background,
                            placeholderColor = MaterialTheme.colors.background,
                            backgroundColor = Color.White,
                            focusedLabelColor = MaterialTheme.colors.background,
                            focusedIndicatorColor = MaterialTheme.colors.background
                        ),
                        placeholder = {Text(text = "In Minute") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            extraGame = it.filter { it.isDigit() }
                        }
                    )
                    Row(modifier = Modifier.padding(16.dp)) {
                        TextClickable(
                            text = "Back",
                            width = 100,
                            height = 36,
                            colorBackground = MaterialTheme.colors.background,
                            fontColor = Color.White
                        ){
                            DIALOG_TIMER = false
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        TextClickable(
                            text = "Confirm",
                            width = 100,
                            height = 36,
                            colorBackground = MaterialTheme.colors.background,
                            fontColor = Color.White
                        ){
                            if(!timeGame.isNullOrEmpty() && !timeGame.isNullOrEmpty()){
                                Timer.forEachIndexed { index, timer ->
                                    Timer[index].SetTimer(
                                        halfTime = timeGame.toInt(),
                                        extraTime = extraGame.toInt()
                                    )
                                }
//                                HALF_TIME = timeGame.toInt()
//                                EXTRA_TIME = extraGame.toInt()
                            }
                            DIALOG_TIMER = false
                        }
                    }
                }
            }
        }
    }
}