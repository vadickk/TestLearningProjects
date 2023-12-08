package com.test.testsavedstatemodule

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _liveData = savedStateHandle.getLiveData("liveData", "Default")
    var liveData: LiveData<String> = _liveData

    fun changeText(text: String) {
        _liveData.value = text
    }

}