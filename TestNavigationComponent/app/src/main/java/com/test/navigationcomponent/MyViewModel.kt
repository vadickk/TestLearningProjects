package com.test.navigationcomponent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    val textForFirstFragment: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val textForSecondFragment: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val color: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val colorName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}