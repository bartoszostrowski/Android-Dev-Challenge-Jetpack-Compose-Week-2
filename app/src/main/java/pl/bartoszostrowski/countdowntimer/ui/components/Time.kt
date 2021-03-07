package pl.bartoszostrowski.countdowntimer.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Time(
    modifier: Modifier = Modifier,
    timeInMiliSeconds: Long
) {
    val hours = timeInMiliSeconds / (60 * 60)
    val min = (timeInMiliSeconds / 60) % 60
    val sec = timeInMiliSeconds % 60
    Text(
        style = MaterialTheme.typography.h2,
        text = hours.toString().padStart(2, '0') + ":" + min.toString()
            .padStart(2, '0') + ":" + sec.toString().padStart(2, '0')
    )
}
