package com.example.manageyourcar.dataRoom.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.manageyourcar.dataRoom.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    fun addNewUser(userEntity: UserEntity)

    @Query("SELECT * FROM users")
    fun getUsers() : List<UserEntity>

    @Update
    fun updateUser(userEntity: UserEntity)

    @Query("DELETE FROM users WHERE id = :idUser")
    fun deleteUser(idUser: Long)
}