package component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberDialogState
import controller.Display
import controller.Team

@Composable
fun DialogWindow1(
    Team: Team,
    Display: Display
){
    val stateDialog = rememberDialogState(size = DpSize(350.dp, 220.dp), position = WindowPosition(Alignment.Center))

    Dialog(
        onCloseRequest = { Display.dialogTeamName = false },
        visible = Display.dialogTeamName,
        undecorated = true,
        state = stateDialog,
        transparent = true
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            var nameTeam by remember { mutableStateOf(TextFieldValue("")) }

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
                        value = nameTeam,
                        maxLines = 1,
                        label = { Text(text = "Name Team") },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = MaterialTheme.colors.background,
                            cursorColor = MaterialTheme.colors.background,
                            placeholderColor = MaterialTheme.colors.background,
                            backgroundColor = Color.White,
                            focusedLabelColor = MaterialTheme.colors.background,
                            focusedIndicatorColor = MaterialTheme.colors.background
                        ),
                        onValueChange = {
                            nameTeam = it
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
                            Display.dialogTeamName = false
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        TextClickable(
                            text = "Confirm",
                            width = 100,
                            height = 36,
                            colorBackground = MaterialTheme.colors.background,
                            fontColor = Color.White
                        ){
                            if(!nameTeam.text.isNullOrEmpty()){
                                Team.NameTeam = nameTeam.text
                                Display.dialogTeamName = false
                            }
                        }
                    }
                }
            }
        }
    }
}