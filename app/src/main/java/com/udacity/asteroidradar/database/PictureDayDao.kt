package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.udacity.asteroidradar.PictureOfDay

@Dao
interface PictureDayDao {
    @Query("SELECT * FROM pictureofday")
    fun get(): LiveData<PictureOfDay>

    @Transaction
    fun updateData(pic: PictureOfDay): Long {
        deleteAll()
        return insert(pic)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pic: PictureOfDay): Long

    @Query("DELETE FROM pictureofday")
    fun deleteAll()
}