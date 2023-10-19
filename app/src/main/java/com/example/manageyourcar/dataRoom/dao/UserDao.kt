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
    fun addNewContact(contactEntity: UserEntity)

    @Query("SELECT * FROM users")
    fun getContacts() : Flow<List<UserEntity>>

    @Update
    fun updateContact(contactEntity: UserEntity)

    @Query("DELETE FROM users WHERE id = :idUser")
    fun deleteContact(idUser: Long)
}