package com.example.manageyourcar.dataRoom.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.manageyourcar.dataRoom.dao.CarDao
import com.example.manageyourcar.dataRoom.dao.UserDao
import com.example.manageyourcar.dataRoom.entities.CarEntity
import com.example.manageyourcar.dataRoom.entities.UserEntity

@Database(entities =[CarEntity::class, UserEntity::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun getCarDAO(): CarDao
    abstract fun getUserDAO(): UserDao
}