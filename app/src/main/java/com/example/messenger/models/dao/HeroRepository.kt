package com.example.messenger.models.dao

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class HeroRepository(private val heroDao: HeroDao) {
    suspend fun getHeroes(): List<Hero> {
        return withContext(Dispatchers.IO) {
            heroDao.getAll()
        }
    }

    suspend fun getHeroesByNumber(number: Int): Flow<List<Hero>> {
        return withContext(Dispatchers.IO) {
            heroDao.findByNumber(number)
        }
    }

    suspend fun checkHeroInDatabase(number: Int): Boolean {
        return withContext(Dispatchers.IO) {
            heroDao.checkValue(number) > 0
        }
    }

    suspend fun insertHeroes(heroes: List<Hero>) {
        withContext(Dispatchers.IO) {
            heroDao.insertAll(heroes)
        }
    }
}