package com.example.challenge_chapter5.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.challenge_chapter5.data.user.DataUserManager
import kotlinx.coroutines.launch

class LoginViewModel(private val pref: DataUserManager): ViewModel() {

    fun getUser(): LiveData<String>{
        return pref.getUsername().asLiveData()
    }

    fun getPassword(): LiveData<String>{
        return pref.getPassword().asLiveData()
    }

    fun setIsLogin(isLogin:Boolean){
        viewModelScope.launch {
            pref.setIsLogin(isLogin)
        }
    }

    fun getIsLogin(): LiveData<Boolean> {
        return pref.getIsLogin().asLiveData()
    }

}
