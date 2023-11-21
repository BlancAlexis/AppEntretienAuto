package com.example.manageyourcar.dataLayer.dataLayerRoom.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun addNewUser(userEntity: UserEntity)

    @Query("SELECT * FROM users WHERE id=:idUser")
    suspend fun getUser(idUser: Int): UserEntity

    @Query("SELECT * FROM users WHERE password=:password AND login=:login ")
    suspend fun logUser(login: String, password: String): UserEntity

    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<UserEntity>

    @Update
    suspend fun updateUser(userEntity: UserEntity)

    @Query("DELETE FROM users WHERE id = :idUser")
    suspend fun deleteUser(idUser: Int)
}