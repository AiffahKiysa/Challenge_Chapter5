package com.example.challenge_chapter5.data.user

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataUserManager(private val context: Context){

    companion object {
        private const val DATASTORE_NAME = "login_preferences"
        private val USERNAME = stringPreferencesKey("username_key")
        private val NAME = stringPreferencesKey("name_key")
        private val PASSWORD = stringPreferencesKey("password_key")
        private val EMAIL = stringPreferencesKey("email_key")
        private val IS_LOGIN = booleanPreferencesKey("is_login_key")

        private val Context.dataUser by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }

    suspend fun setUser(name: String, username: String, email: String, password: String) {
        context.dataUser.edit { preferences ->
            preferences[NAME] = name
            preferences[USERNAME] = username
            preferences[PASSWORD] = password
            preferences[EMAIL] = email
        }
    }


    suspend fun setIsLogin(isLogin: Boolean) {
        context.dataUser.edit { preferences ->
            preferences[IS_LOGIN] = isLogin
        }
    }

    fun getPassword(): Flow<String>{
        return context.dataUser.data.map { preferences ->
            preferences[PASSWORD] ?: ""
        }
    }

    fun getIsLogin(): Flow<Boolean>{
        return context.dataUser.data.map { preferences ->
            preferences[IS_LOGIN] ?: false
        }
    }

    fun getName(): Flow<String> {
        return context.dataUser.data.map { preferences ->
            preferences[NAME] ?: ""
        }
    }

    fun getUsername(): Flow<String> {
        return context.dataUser.data.map { preferences ->
            preferences[USERNAME] ?: ""
        }
    }

    fun getEmail(): Flow<String> {
        return context.dataUser.data.map { preferences ->
            preferences[EMAIL] ?: ""
        }
    }
}