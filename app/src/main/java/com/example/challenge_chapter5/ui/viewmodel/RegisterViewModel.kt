package com.example.challenge_chapter5.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge_chapter5.data.user.DataUserManager
import kotlinx.coroutines.launch

class RegisterViewModel(private val pref: DataUserManager): ViewModel() {
    fun saveUser(username: String, name: String, email: String, password: String){
        viewModelScope.launch {
            pref.setUsername(username)
            pref.setName(name)
            pref.setPassword(password)
            pref.setEmail(email)
        }
    }

}
