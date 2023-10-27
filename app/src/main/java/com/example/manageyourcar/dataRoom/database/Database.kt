package com.example.manageyourcar.dataRoom.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.manageyourcar.dataRoom.dao.CarDao
import com.example.manageyourcar.dataRoom.dao.EntretienDao
import com.example.manageyourcar.dataRoom.dao.UserDao
import com.example.manageyourcar.dataRoom.entities.CarEntity
import com.example.manageyourcar.dataRoom.entities.EntretienEntity
import com.example.manageyourcar.dataRoom.entities.UserEntity

@Database(entities =[CarEntity::class, UserEntity::class, EntretienEntity::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun getCarDAO(): CarDao
    abstract fun getUserDAO(): UserDao
    abstract fun getEntretienDAO(): EntretienDao
}