package com.example.manageyourcar.dataLayer.dataLayerFirebase

import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.CarEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow

class remoteDataFirebaseSourceImpl(private val firestoreInstance : FirebaseFirestore) : remoteDataFirebaseSource {
    override fun addNewCar(carEntity: CarEntity) {
        firestoreInstance.collection(
            
        )
    }

    override fun getCars(idUser: Int): Flow<List<CarEntity>> {
        TODO("Not yet implemented")
    }

    override fun getCars(): List<CarEntity> {
        TODO("Not yet implemented")
    }

    override fun getCar(idCar: Int): CarEntity {
        TODO("Not yet implemented")
    }

    override fun updateCar(carEntity: CarEntity) {
        TODO("Not yet implemented")
    }

    override fun updateCarMileage(listMileages: List<Int>, idCar: Int) {
        TODO("Not yet implemented")
    }

    override fun deleteCar(car: CarEntity) {
        TODO("Not yet implemented")
    }
}

interface remoteDataFirebaseSource {
    fun addNewCar(carEntity: CarEntity)

    fun getCars(idUser: Int): Flow<List<CarEntity>>

    fun getCars(): List<CarEntity>

    fun getCar(idCar: Int): CarEntity

    fun updateCar(carEntity: CarEntity)

    fun updateCarMileage(listMileages: List<Int>, idCar: Int)

    fun deleteCar(car: CarEntity)
}