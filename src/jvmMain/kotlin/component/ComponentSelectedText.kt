package component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ComponentSelectedText(
    titleMenu: String,
    index: Int,
    selected: Boolean = false,
    clicked: Boolean = false,
    height: Int = 30,
    width: Int = 144,
    onClick: (Int) -> Unit
) {
    val sizeFontFooter = MaterialTheme.typography.subtitle2.fontSize

    Surface(
        shape = CircleShape,
        color = if(selected) MaterialTheme.colors.background else Color.White,
        border = BorderStroke(width = 1.dp, color = Color.White),
        modifier = Modifier.size(height = height.dp, width = width.dp)

    ) {
        if(clicked){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
                    .pointerHoverIcon(
                        icon = PointerIcon.Hand
                    )
                    .clickable {
                        onClick.invoke(index)
                    }
            ) {
                Text(
                    text = titleMenu,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = sizeFontFooter,
                    color = if(!selected) MaterialTheme.colors.background else Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.wrapContentHeight().fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 8.dp),
                )
            }
        }
        else{
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = titleMenu,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = sizeFontFooter,
                    color = if(!selected) MaterialTheme.colors.background else Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.wrapContentHeight().fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 8.dp),
                )
            }
        }
    }
}