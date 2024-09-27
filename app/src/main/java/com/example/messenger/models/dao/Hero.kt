package com.example.messenger.models.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Hero(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val number: Int,
    val name: String?,
    val culture: String?,
    val born: String?,
    val titles: String?,
    val aliases: String?,
    val playedBy: String?
)