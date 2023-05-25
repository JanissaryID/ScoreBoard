package component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ComponentPlayer(
    modifier: Modifier,
    team: Boolean = false,
    image: Int,
    description: Int,
    name: String,
    number: Int
) {

    val sizeFont = MaterialTheme.typography.caption.fontSize

    val selectionImage = listOf(
        "ball.svg",
        "ballPinalty.svg",
        "yellowcard.svg",
        "redcard.svg"
    )

    val selectionDescription = listOf(
        "Goal",
        "Goal Pinalty",
        "Yellow Card",
        "Red Card"
    )

    if (team){
        Row(
            modifier = modifier.width(180.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$name",
                fontWeight = FontWeight.SemiBold,
                fontSize = sizeFont,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.width(100.dp),
                textAlign = TextAlign.End
            )
            Text(
                text = "[ $number ]",
                fontWeight = FontWeight.SemiBold,
                fontSize = sizeFont,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Image(
                painter = painterResource(selectionImage[image]),
                contentDescription = selectionDescription[description],
                contentScale = ContentScale.Fit,
                modifier = Modifier.width(24.dp),
                alignment = Alignment.Center
            )
        }
    }
    else{
        Row(
            modifier = modifier.width(180.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(selectionImage[image]),
                contentDescription = selectionDescription[description],
                contentScale = ContentScale.Fit,
                modifier = Modifier.width(24.dp),
                alignment = Alignment.Center
            )
            Text(
                text = "[ $number ]",
                fontWeight = FontWeight.SemiBold,
                fontSize = sizeFont,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = "$name",
                fontWeight = FontWeight.SemiBold,
                fontSize = sizeFont,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.width(100.dp),
                textAlign = TextAlign.Start
            )
        }
    }
}