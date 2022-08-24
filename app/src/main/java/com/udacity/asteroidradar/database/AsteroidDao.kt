package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.udacity.asteroidradar.Asteroid

@Dao
interface AsteroidDao {

    @Query("SELECT * FROM Asteroid ORDER BY date(closeApproachDate) ASC")
    fun getAll(): LiveData<List<Asteroid>>


    @Query("SELECT * FROM Asteroid WHERE closeApproachDate <=:date ORDER BY date(closeApproachDate) ASC ")
    fun getTodayAsteroid(date: String): LiveData<List<Asteroid>>

    @Transaction
    fun updateData(users: List<Asteroid>): List<Long> {
        deleteAll()
        return insertAll(users)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(asteroids: List<Asteroid>): List<Long>

    @Query("DELETE FROM Asteroid")
    fun deleteAll()

}