package com.example.challenge_chapter5.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.challenge_chapter5.data.user.DataUserManager
import kotlinx.coroutines.launch

class UpdateViewModel(private val pref: DataUserManager): ViewModel() {

    fun saveUser(username: String, name: String, email: String, password: String){
        viewModelScope.launch {
            pref.setUsername(username)
            pref.setName(name)
            pref.setPassword(password)
            pref.setEmail(email)
        }
    }

    fun getUser(): LiveData<String> {
        return pref.getUsername().asLiveData()
    }

    fun getName(): LiveData<String> {
        return pref.getName().asLiveData()
    }

    fun getEmail(): LiveData<String> {
        return pref.getEmail().asLiveData()
    }

    fun getPassword(): LiveData<String> {
        return pref.getPassword().asLiveData()
    }

    fun setIsLogin(isLogin:Boolean){
        viewModelScope.launch {
            pref.setIsLogin(isLogin)
        }
    }

}
