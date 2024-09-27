package com.example.messenger.models.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Hero::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract val heroDao: HeroDao
}

private lateinit var INSTANCE: AppDB

fun getDatabase(context: Context): AppDB {
    synchronized(AppDB::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDB::class.java,
                "heroes.db"
            ).build()
        }
    }
    return INSTANCE
}