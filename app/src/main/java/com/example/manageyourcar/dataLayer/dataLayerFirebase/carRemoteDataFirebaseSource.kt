package com.example.manageyourcar.dataLayer.dataLayerFirebase

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.CarEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.coroutines.cancellation.CancellationException

class carRemoteDataFirebaseSourceImpl(private val firestoreInstance : FirebaseFirestore) : remoteDataFirebaseSource {
    override fun addNewCar(carEntity: CarEntity) : Ressource<Unit> {
        return try {
            firestoreInstance.collection("cars").add(carEntity)
            Ressource.Success(Unit)
        } catch (e: Exception) {
            Ressource.Error(e)

        }
    }


    override fun getCars(idUser: Int) :  Flow<Ressource<List<CarEntity>>> = callbackFlow {
        trySend(Ressource.Loading())

        val listenerRegistration = firestoreInstance.collection("cars")
            .whereEqualTo("owner_id", idUser)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Ressource.Error(error))
                    cancel(CancellationException("Firestore error", error))
                } else {
                    val cars = snapshot?.toObjects(CarEntity::class.java) ?: emptyList()
                    trySend(Ressource.Success(cars))
                }
            }

        awaitClose { listenerRegistration.remove() }
    }

    override fun getCar(idCar: Int) :  Flow<Ressource<CarEntity>> = callbackFlow {
        trySend(Ressource.Loading())

        val listenerRegistration = firestoreInstance.collection("cars").document(idCar.toString())
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Ressource.Error(error))
                    cancel(CancellationException("Firestore error", error))
                } else {
                    val car = snapshot?.toObject(CarEntity::class.java)
                    if (car != null) {
                        trySend(Ressource.Success(car))
                    } else {
                        trySend(Ressource.Error(Exception("Car not found")))
                    }
                }
            }

        awaitClose { listenerRegistration.remove() }
    }

    override fun updateCar(carEntity: CarEntity): Flow<Ressource<Unit>> = callbackFlow {
        trySend(Ressource.Loading())

        firestoreInstance.collection("cars").document(carEntity.carID.toString())
            .set(carEntity)
            .addOnSuccessListener {
                trySend(Ressource.Success(Unit))
            }
            .addOnFailureListener { exception ->
                trySend(Ressource.Error(exception))
                cancel(CancellationException("Firestore error", exception))
            }
    }

    override fun updateCarMileage(listMileages: List<Int>, idCar: Int): Flow<Ressource<Unit>> = callbackFlow {
        trySend(Ressource.Loading())

        firestoreInstance.collection("cars").document(idCar.toString())
            .update("mileage", listMileages)
            .addOnSuccessListener {
                trySend(Ressource.Success(Unit))
            }
            .addOnFailureListener { exception ->
                trySend(Ressource.Error(exception))
                cancel(CancellationException("Firestore error", exception))
            }
    }
    override fun deleteCar(car: CarEntity): Flow<Ressource<Unit>> = callbackFlow {
        trySend(Ressource.Loading())

        firestoreInstance.collection("cars").document(car.carID.toString())
            .delete()
            .addOnSuccessListener {
                trySend(Ressource.Success(Unit))
            }
            .addOnFailureListener { exception ->
                trySend(Ressource.Error(exception))
                cancel(CancellationException("Firestore error", exception))
            }
    }
}

interface remoteDataFirebaseSource {
    fun addNewCar(carEntity: CarEntity) : Ressource<Unit>

    fun getCars(idUser: Int): Flow<Ressource<List<CarEntity>>>

    fun getCar(idCar: Int): Flow<Ressource<CarEntity>>

    fun updateCar(carEntity: CarEntity) : Flow<Ressource<Unit>>

    fun updateCarMileage(listMileages: List<Int>, idCar: Int) : Flow<Ressource<Unit>>

    fun deleteCar(car: CarEntity) : Flow<Ressource<Unit>>
}