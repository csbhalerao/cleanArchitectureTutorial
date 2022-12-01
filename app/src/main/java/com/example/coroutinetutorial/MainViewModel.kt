package com.example.coroutinetutorial

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
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
            timerFlow.collect {
                timerValue.set(it.toString())
            }
        }
    }
}
