package com.test.testflow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _stateFlow = MutableStateFlow("HelloWorld")
    val stateFlow = _stateFlow.asStateFlow()

    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    private val _liveData = MutableLiveData<String>()
    val liveData: LiveData<String> = _liveData

    private val _channel = Channel<String>()
    val channel = _channel.receiveAsFlow()

    fun changeChannel() {
        viewModelScope.launch {
            _channel.send("Channel")
        }
    }

    fun changeStateFlow() {
        _stateFlow.value = "StateFlow"
    }

    fun changeSharedFlow() {
        viewModelScope.launch {
            _sharedFlow.emit("SharedFlow")
        }
    }

    fun changeLiveData() {
        _liveData.value = "LiveData"
    }

    fun changeFlow() : Flow<String> {
        return flow {
            repeat(5) {
                delay(1000L)
                emit("Flow $it")
            }
        }
    }

}