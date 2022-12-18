package com.example.challenge_chapter5.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challenge_chapter5.data.user.DataUserManager
import com.example.challenge_chapter5.ui.viewmodel.AccountViewModel
import com.example.challenge_chapter5.ui.viewmodel.LoginViewModel
import com.example.challenge_chapter5.ui.viewmodel.RegisterViewModel
import com.example.challenge_chapter5.ui.viewmodel.UpdateViewModel

class ViewModelFactory(private val pref: DataUserManager) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(pref) as T
        }
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)){
            return RegisterViewModel(pref) as T
        }
        if (modelClass.isAssignableFrom(UpdateViewModel::class.java)){
            return UpdateViewModel(pref) as T
        }
        if (modelClass.isAssignableFrom(AccountViewModel::class.java)){
            return AccountViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
    }
}