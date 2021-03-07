package pl.bartoszostrowski.countdowntimer.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Circle(
    modifier: Modifier = Modifier,
    color: Color,
    timeInSeconds: Long
) {
    val hours = timeInSeconds / (60 * 60)
    val min = (timeInSeconds / 60) % 60
    val sec = timeInSeconds % 60

    val animatedProgressSec by animateFloatAsState(
        targetValue = sec * 1 / 60f,
        animationSpec = tween(1000)
    )

    val animatedProgressMin by animateFloatAsState(
        targetValue = min * 1 / 60f,
        animationSpec = tween(1000)
    )

    val animatedProgressHour by animateFloatAsState(
        targetValue = hours * 1 / 60f,
        animationSpec = tween(1000)
    )

    CircularProgressIndicator(
        modifier = Modifier
            .size(300.dp),
        progress = animatedProgressHour,
        strokeWidth = 10.dp,
        color = color.copy(alpha = 1 - hours * 1 / 60f)
    )
    CircularProgressIndicator(
        modifier = Modifier
            .size(280.dp),
        progress = animatedProgressMin,
        strokeWidth = 10.dp,
        color = color.copy(alpha = 1 - min * 1 / 60f)
    )
    CircularProgressIndicator(
        modifier = Modifier
            .size(260.dp),
        progress = animatedProgressSec,
        strokeWidth = 10.dp,
        color = color.copy(alpha = 1 - sec * 1 / 60f)
    )
}
