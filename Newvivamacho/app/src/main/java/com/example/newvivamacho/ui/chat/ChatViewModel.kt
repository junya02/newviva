package com.example.newvivamacho.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChatViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "準備中です"
    }
    val text: LiveData<String> = _text
}