package com.example.challenge_chapter5.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    var username : String,
    var name : String,
    var email : String,
    var password : String
) : Parcelable
