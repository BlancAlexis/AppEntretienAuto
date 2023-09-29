package com.example.manageyourcar.dataLayer.dataSource.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class,CarEntity::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun getUserDAO(): UserDao
    abstract fun getCarDAO(): CarDao
}