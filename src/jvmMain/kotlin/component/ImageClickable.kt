package component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ImageClickable(
    image: String,
    description: String,
    size: Int,
    onClick: () -> Unit
) {

    val colorBackground = MaterialTheme.colors.onPrimary

    Surface(
        shape = CircleShape,
        color = colorBackground,
        modifier = Modifier.size(size = size.dp)
    ) {
        Surface(
            color = Color.Transparent,
            modifier = Modifier
                .pointerHoverIcon(
                    icon = PointerIcon.Hand
                )
                .clickable {
                onClick()
            },
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = description,
                contentScale = ContentScale.Fit,
                modifier = Modifier.width(24.dp),
                alignment = Alignment.Center
            )
        }
    }
}