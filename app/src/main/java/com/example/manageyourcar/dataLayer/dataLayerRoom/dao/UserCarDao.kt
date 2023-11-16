package com.example.manageyourcar.dataLayer.dataLayerRoom.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface UserCarDao {
    @Insert
    fun addUserCar()

    @Delete
    fun deleteUserCar()

    @Update
    fun updateUserCar()
}