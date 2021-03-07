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
