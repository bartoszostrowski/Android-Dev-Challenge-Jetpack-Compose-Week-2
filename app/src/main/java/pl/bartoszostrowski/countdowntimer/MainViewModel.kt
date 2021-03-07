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

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private lateinit var countdownTimer: CountDownTimer

    private val _isRunning = MutableLiveData<Boolean>()
    val isRunning: LiveData<Boolean> get() = _isRunning

    private val _timeInSeconds = MutableLiveData<Long>()
    val timeInSeconds: LiveData<Long> get() = _timeInSeconds

    init {
        _timeInSeconds.value = 30L
    }

    fun onStart(time: Long) {
        Log.d(TAG, "onStart()")

        countdownTimer = object : CountDownTimer(time * 1000L, 1000) {
            override fun onFinish() {
                Log.d(TAG, "onStart() : Finished")

                _isRunning.value = false
            }

            override fun onTick(p0: Long) {
                Log.d(TAG, "onStart() : Tick = $p0")

                _timeInSeconds.value = p0 / 1000
            }
        }
        countdownTimer.start()

        _isRunning.value = true
    }

    fun onPause() {
        Log.d(TAG, "onPause()")

        countdownTimer.cancel()
        _isRunning.value = false
    }

    fun onReset() {
        Log.d(TAG, "onReset()")

        _timeInSeconds.value = 30L

        if (_isRunning.value!!) {
            onPause()
            onStart(_timeInSeconds.value!!)
        }
    }

    fun onIncrease() {
        Log.d(TAG, "onIncrease()")

        _timeInSeconds.value = timeInSeconds.value?.plus(10)

        if (_isRunning.value == true) {
            onPause()
            _timeInSeconds.value?.let { onStart(it) }
        }
    }

    companion object {
        private const val TAG: String = "MainViewModel"
    }
}
