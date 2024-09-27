package com.example.messenger.models.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HeroDao {
    @Query("SELECT * FROM hero")
    fun getAll(): List<Hero>

    @Query("SELECT * FROM Hero WHERE number = :number")
    fun findByNumber(number: Int): Flow<List<Hero>>

    @Query("SELECT COUNT(*) FROM hero WHERE number = :number")
    fun checkValue(number: Int): Int

    @Insert
    fun insertAll(hero: List<Hero>)
}