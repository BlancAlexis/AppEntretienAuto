package com.example.manageyourcar.dataLayer.dataLayerFirebase

import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.CarEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class carRemoteDataFirebaseSourceImpl(private val firestoreInstance : FirebaseFirestore) : remoteDataFirebaseSource {
    override fun addNewCar(carEntity: CarEntity) {
        firestoreInstance.collection("cars").add(carEntity)
    }

    override fun getCars(idUser: Int): Flow<List<CarEntity>> {
        firestoreInstance.collection("cars").whereEqualTo("owner_id", idUser).get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    return flowOf(result.toObjects(CarEntity::class.java))
                }
            }
            .addOnFailureListener { exception ->
                // Erreur lors de la récupération des documents
            }
    }
    override fun getCars(): List<CarEntity> {
        firestoreInstance.collection("cars").get()
            .addOnSuccessListener { result ->
                return@addOnSuccessListener result.toObjects(CarEntity::class.java)
            }
            .addOnFailureListener { exception ->
                // Erreur lors de la récupération des documents
            }
    }

    override fun getCar(idCar: Int): CarEntity {
        firestoreInstance.collection("cars").document(idCar.toString()).get()
            .addOnSuccessListener { result ->
                return@addOnSuccessListener result.toObject(CarEntity::class.java)
            }
            .addOnFailureListener { exception ->
                // Erreur lors de la récupération des documents
            }
    }

    override fun updateCar(carEntity: CarEntity) {
        firestoreInstance.collection("cars").document(carEntity.carID.toString()).set(carEntity)
    }

    override fun updateCarMileage(listMileages: List<Int>, idCar: Int) {
        firestoreInstance.collection("cars").document(idCar.toString()).update("mileage", listMileages)
    }

    override fun deleteCar(car: CarEntity) {
        firestoreInstance.collection("cars").document(car.carID.toString()).delete()
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