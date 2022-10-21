package com.example.challenge_chapter5.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val username : String,
    val name : String,
    val email : String,
    val password : String
) : Parcelable
