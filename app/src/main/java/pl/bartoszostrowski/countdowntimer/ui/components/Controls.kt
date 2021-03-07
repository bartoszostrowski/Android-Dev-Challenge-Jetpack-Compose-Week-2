package pl.bartoszostrowski.countdowntimer.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Timer10
import androidx.compose.material.icons.filled.TimerOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@ExperimentalAnimationApi
@Composable
fun Controls(
    modifier: Modifier = Modifier,
    color: Color,
    timeInSeconds: Long,
    isRunning: Boolean,
    onStart: () -> Unit,
    onPause: () -> Unit,
    onReset: () -> Unit,
    onIncrease: () -> Unit,
) {
    Row(Modifier.height(80.dp)) {
        Button(
            modifier = Modifier
                .size(54.dp),
            shape = CircleShape,
            onClick = onIncrease,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = color,
                contentColor = Color.White
            )
        ) {
            Icon(imageVector = Icons.Filled.Timer10, contentDescription = "Add 10 seconds")
        }

        Spacer(modifier = Modifier.width(16.dp))

        androidx.compose.animation.AnimatedVisibility(timeInSeconds != 0L) {
            Button(
                modifier = Modifier.size(64.dp),
                shape = CircleShape,
                onClick = if (isRunning) onPause else onStart,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = color,
                    contentColor = Color.White
                )
            ) {
                if (isRunning) {
                    Icon(imageVector = Icons.Filled.Pause, contentDescription = "Stop")
                } else {
                    Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = "Start")
                }
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Button(
            modifier = Modifier
                .size(54.dp),
            shape = CircleShape,
            onClick = onReset,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = color,
                contentColor = Color.White
            )
        ) {
            Icon(imageVector = Icons.Filled.TimerOff, contentDescription = "Reset timer")
        }
    }
}
