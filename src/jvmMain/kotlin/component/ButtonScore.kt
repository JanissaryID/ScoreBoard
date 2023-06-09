package component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonScore(
    onUpClick: () -> Unit,
    onDownClick: () -> Unit
) {

    Column(
        modifier = Modifier.wrapContentSize()
    ) {
        ImageClickable(
            image = "up.svg",
            description = "Up Score",
            size = 32
        ){
            onUpClick()
        }

        Spacer(modifier = Modifier.height(8.dp))

        ImageClickable(
            image = "down.svg",
            description = "Down Score",
            size = 32
        ){
            onDownClick()
        }
    }
}