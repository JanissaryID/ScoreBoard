package component

import DIALOG_INJURY
import SELECTED_SCREEN
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberDialogState
import controller.Timer

@Composable
fun DialogInjury(Timer: List<Timer>){
    val stateDialog = rememberDialogState(size = DpSize(350.dp, 280.dp), position = WindowPosition(Alignment.Center))


    Dialog(
        onCloseRequest = { DIALOG_INJURY = false },
        visible = DIALOG_INJURY,
        undecorated = true,
        state = stateDialog,
        transparent = true
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            var timeInjury by remember { mutableStateOf("") }

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
                        value = timeInjury,
                        maxLines = 1,
                        label = { Text(text = "Injury Time") },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = MaterialTheme.colors.background,
                            cursorColor = MaterialTheme.colors.background,
                            placeholderColor = MaterialTheme.colors.background,
                            backgroundColor = Color.White,
                            focusedLabelColor = MaterialTheme.colors.background,
                            focusedIndicatorColor = MaterialTheme.colors.background
                        ),
                        placeholder = { Text(text = "In Minute") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            timeInjury = it.filter { it.isDigit() }
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
                            DIALOG_INJURY = false
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        TextClickable(
                            text = "Confirm",
                            width = 100,
                            height = 36,
                            colorBackground = MaterialTheme.colors.background,
                            fontColor = Color.White
                        ){
                            if(!timeInjury.isNullOrEmpty()){
                                Timer[SELECTED_SCREEN].SetInjury(
                                    injuryTime = timeInjury.toInt()
                                )
                            }
                            DIALOG_INJURY = false
                        }
                    }
                }
            }
        }
    }
}