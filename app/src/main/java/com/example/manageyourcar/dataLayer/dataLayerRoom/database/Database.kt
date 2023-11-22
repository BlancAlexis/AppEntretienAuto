package com.example.manageyourcar.dataLayer.dataLayerRoom.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.manageyourcar.dataLayer.dataLayerRoom.converter.DateConverter
import com.example.manageyourcar.dataLayer.dataLayerRoom.converter.MaintenanceServiceConverter
import com.example.manageyourcar.dataLayer.dataLayerRoom.dao.CarDao
import com.example.manageyourcar.dataLayer.dataLayerRoom.dao.ServicingDao
import com.example.manageyourcar.dataLayer.dataLayerRoom.dao.UserDao
import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.CarEntity
import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.MaintenanceEntity
import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.UserEntity

@Database(entities = [CarEntity::class, UserEntity::class, MaintenanceEntity::class], version = 1)
@TypeConverters(DateConverter::class, MaintenanceServiceConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun getCarDAO(): CarDao
    abstract fun getUserDAO(): UserDao
    abstract fun getServicingDAO(): ServicingDao
}