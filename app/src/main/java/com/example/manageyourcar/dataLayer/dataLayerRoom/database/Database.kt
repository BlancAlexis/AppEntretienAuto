package com.example.manageyourcar.dataLayer.dataLayerRoom.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.manageyourcar.dataLayer.dataLayerRoom.dao.CarDao
import com.example.manageyourcar.dataLayer.dataLayerRoom.dao.ServicingDao
import com.example.manageyourcar.dataLayer.dataLayerRoom.dao.UserDao
import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.CarEntity
import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.ServicingEntity
import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.UserEntity

@Database(entities = [CarEntity::class, UserEntity::class, ServicingEntity::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun getCarDAO(): CarDao
    abstract fun getUserDAO(): UserDao
    abstract fun getServicingDAO(): ServicingDao
}