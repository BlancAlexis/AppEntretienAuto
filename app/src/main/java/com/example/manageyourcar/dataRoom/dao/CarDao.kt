package com.example.manageyourcar.dataRoom.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.manageyourcar.dataRoom.entities.CarEntity

@Dao
interface CarDao {
    @Insert
    fun addNewCar(carEntity: CarEntity)

    @Query("SELECT * FROM cars")
    fun getCars() : List<CarEntity>

    @Query("SELECT * FROM cars WHERE id = :idCar")
    fun getCar(idCar: Int) : CarEntity

    @Update
    fun updateCar(carEntity: CarEntity)

    @Query("DELETE FROM cars WHERE id = :idUser")
    fun deleteCar(idUser: Int)
}