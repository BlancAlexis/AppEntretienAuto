package com.example.manageyourcar.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.manageyourcar.domain.model.CarEntity

@Database(entities =[CarEntity::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun getCarDAO(): CarDao
}