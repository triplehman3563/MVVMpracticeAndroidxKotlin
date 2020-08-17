package com.example.mvvmpracticeandroidxkotlin

import android.os.CountDownTimer
import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private lateinit var timer: CountDownTimer
    var timerValue = MutableLiveData<Long>()
    private var _finished = MutableLiveData<Boolean>()
    val finished: LiveData<Boolean>
        get() = _finished
    private val _seconds = MutableLiveData<Double>()
    fun seconds(): LiveData<Double> {
        return _seconds
    }

    fun init() {
        _finished.value = true
        _seconds.value = 0.0


    }

    fun startTimer() {
        if (_finished.value!!) {
            timer = object : CountDownTimer(timerValue.value!!.toLong(), 10) {
                override fun onFinish() {
                    var zero = 0
                    _seconds.value = zero.toDouble();
                    _finished.value = true
                }

                override fun onTick(p0: Long) {
                    val timeLeft = p0.toDouble() / 1000
                    _seconds.value = timeLeft
                }
            }.start()
            _finished.value = false
        }

    }

    fun stopTimer() {
        timer.cancel()
        _finished.value = true
    }
}