package com.test.testviewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ClassViewModel : ViewModel() {

    private val messageForActivity: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    private val messageForFragment1: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    private val messageForFragment2: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    fun setMessageForActivity(text: String) {
        messageForActivity.value = text
    }
    fun setMessageForFragment1(text: String) {
        messageForFragment1.value = text
    }
    fun setMessageForFragment2(text: String) {
        messageForFragment2.value = text
    }
    fun getMessageActivity() : LiveData<String> {
        return messageForActivity
    }
    fun getMessageFragment1() : LiveData<String>{
        return messageForFragment1
    }
    fun getMessageFragment2(): LiveData<String>{
        return messageForFragment2
    }


}