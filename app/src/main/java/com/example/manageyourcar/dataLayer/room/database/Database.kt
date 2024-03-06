package com.example.manageyourcar.dataLayer.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.manageyourcar.dataLayer.room.converter.CarConverter
import com.example.manageyourcar.dataLayer.room.converter.DateConverter
import com.example.manageyourcar.dataLayer.room.converter.MaintenanceServiceConverter
import com.example.manageyourcar.dataLayer.room.dao.CarDao
import com.example.manageyourcar.dataLayer.room.dao.ServicingDao
import com.example.manageyourcar.dataLayer.room.dao.UserDao
import com.example.manageyourcar.dataLayer.room.entities.CarEntity
import com.example.manageyourcar.dataLayer.room.entities.MaintenanceEntity
import com.example.manageyourcar.dataLayer.room.entities.UserEntity

@Database(
    entities = [CarEntity::class, UserEntity::class, MaintenanceEntity::class],
    version = 7,
    exportSchema = false,
)
@TypeConverters(DateConverter::class, MaintenanceServiceConverter::class, CarConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun getCarDAO(): CarDao
    abstract fun getUserDAO(): UserDao
    abstract fun getServicingDAO(): ServicingDao
}