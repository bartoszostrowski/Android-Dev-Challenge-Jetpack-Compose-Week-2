/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.bartoszostrowski.countdowntimer

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import pl.bartoszostrowski.countdowntimer.ui.components.Circle
import pl.bartoszostrowski.countdowntimer.ui.components.Controls
import pl.bartoszostrowski.countdowntimer.ui.components.Time
import pl.bartoszostrowski.countdowntimer.ui.theme.MyTheme
import pl.bartoszostrowski.countrdowntimer.R

class MainActivity : AppCompatActivity() {

    @ExperimentalAnimationApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun MyApp() {
    val viewModel: MainViewModel = viewModel()
    val isRunning by viewModel.isRunning.observeAsState(false)
    val timeInSeconds by viewModel.timeInSeconds.observeAsState(30L)

    val backgroundColor by animateColorAsState(if (isRunning) Color(0xFFFF5252) else Color(0xFF4CAF50))

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = backgroundColor,
                title = {
                    Text(text = stringResource(id = R.string.app_name), color = Color.White)
                }
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 64.dp),
                Alignment.Center,
            ) {
                AnimatedVisibility(timeInSeconds != 0L) {
                    Time(timeInMiliSeconds = timeInSeconds)
                }
                AnimatedVisibility(timeInSeconds == 0L) {
                    Text(
                        style = MaterialTheme.typography.h3,
                        text = "Time's up!",
                    )
                }
                Circle(color = backgroundColor, timeInSeconds = timeInSeconds)
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 32.dp),
                Alignment.BottomCenter,
            ) {
                Controls(
                    color = backgroundColor,
                    isRunning = isRunning,
                    timeInSeconds = timeInSeconds,
                    onStart = { viewModel.onStart(timeInSeconds) },
                    onPause = { viewModel.onPause() },
                    onReset = { viewModel.onReset() },
                    onIncrease = { viewModel.onIncrease() },
                )
            }
        }
    )
}
