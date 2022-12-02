package com.example.coroutinetutorial

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.coroutinetutorial.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = MainViewModel()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        mainViewModel.timerValue.set("starting..")
        //mainViewModel.timer()
        //sharedflow example
        GlobalScope.launch(Dispatchers.Main) {
            val producer1 = producer()
            producer1.collect {
                Log.i("SharedFlow", "collector 1 $it")
            }
        }
        GlobalScope.launch(Dispatchers.Main) {
            val producer2 = producer()
            delay(2000)
            producer2.collect {
                Log.i("SharedFlow", "collector 2 $it")
            }
        }
    }

    private fun producer(): Flow<Int> {
        val mutableSharedFlow = MutableSharedFlow<Int>()
        GlobalScope.launch {
            val list = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8)
            list.forEach {
                mutableSharedFlow.emit(it)
                delay(1000)
            }
        }
        return mutableSharedFlow
    }
}
