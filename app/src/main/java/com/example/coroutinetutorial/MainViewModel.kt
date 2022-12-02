package com.example.coroutinetutorial

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val initialValue = 0
    val timerValue = ObservableField("")
    val timerFlow = flow<Int> {
        var currentValue = initialValue
        while (currentValue < 15) {
            delay(1000L)
            emit(currentValue)
            currentValue++
        }
    }

    fun timer() {
        GlobalScope.launch(Dispatchers.Main) {
            timerFlow.onStart {
                Log.i("timer", "timer start")
            }.onCompletion {
                Log.i("timer", "timer complete")
            }.onEach {
                Log.i("timer", "timer $it")
            }.collect {
                timerValue.set(it.toString())
            }
        }
    }
}
