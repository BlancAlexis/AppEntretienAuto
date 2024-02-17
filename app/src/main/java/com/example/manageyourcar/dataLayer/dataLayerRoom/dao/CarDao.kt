package com.example.manageyourcar.dataLayer.dataLayerRoom.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.CarEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {
    @Insert
    fun addNewCar(carEntity: CarEntity)

    @Query("SELECT * FROM cars WHERE owner_id=:idUser")
    fun getCars(idUser: Int): Flow<List<CarEntity>>

    @Query("SELECT * FROM cars")
    fun getCars(): List<CarEntity>

    @Query("SELECT * FROM cars WHERE carID = :idCar")
    fun getCar(idCar: Int): CarEntity

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCar(carEntity: CarEntity)

    @Query("UPDATE cars SET mileage = :listMileages WHERE carID = :idCar")
    fun updateCarMileage(listMileages: List<Int>, idCar: Int)

    @Delete
    fun deleteCar(car: CarEntity)
}