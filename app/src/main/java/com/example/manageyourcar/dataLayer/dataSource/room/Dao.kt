package com.example.manageyourcar.dataLayer.dataSource.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    fun addNewContact(contactEntity: UserEntity)

    @Query("SELECT * FROM users")
    fun getContacts() : Flow<List<UserEntity>>

    @Update
    fun updateContact(contactEntity: UserEntity)

    @Query("DELETE FROM users WHERE id = :idUser")
    fun deleteContact(idUser: Long)
}

@Dao
interface CarDao {
    @Insert
    fun addNewCar(contactEntity: CarEntity)

    @Query("SELECT * FROM cars")
    fun getCar() : Flow<List<CarEntity>>

    @Update
    fun updateCar(carEntity: CarEntity)

    @Query("DELETE FROM cars WHERE id = :idUser")
    fun deleteCar(idUser: Long)
}