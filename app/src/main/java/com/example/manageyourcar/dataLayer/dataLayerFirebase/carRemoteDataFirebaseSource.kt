package com.example.manageyourcar.dataLayer.dataLayerFirebase

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.CarLocal
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.UUID
import kotlin.coroutines.cancellation.CancellationException

class carRemoteDataFirebaseSourceImpl(private val firestoreInstance : FirebaseFirestore) : remoteDataFirebaseSource {
    override fun addNewCar(carEntity: CarLocal) : Ressource<Unit> {
        return try {
            firestoreInstance.collection(CARS_COLLECTION).document(carEntity.carID).set(carEntity)
            Ressource.Success(Unit)
        } catch (e: Exception) {
            Ressource.Error(e)

        }
    }


    override fun getCars(idUser: Int) :  Flow<Ressource<List<CarLocal>>> = callbackFlow {
        trySend(Ressource.Loading())
        println("jpasse")
        val listenerRegistration = firestoreInstance.collection(CARS_COLLECTION)
            .whereEqualTo("ownerID", idUser)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Ressource.Error(error))
                    cancel(CancellationException("Firestore error", error))
                } else {
                    val cars = snapshot?.toObjects(CarLocal::class.java) ?: emptyList()
                    trySend(Ressource.Success(cars))
                }
            }

        awaitClose { listenerRegistration.remove() }
    }

    override fun getCar(idCar: Int) :  Flow<Ressource<CarLocal>> = callbackFlow {
        trySend(Ressource.Loading())

        val listenerRegistration = firestoreInstance.collection(CARS_COLLECTION).document(idCar.toString())
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Ressource.Error(error))
                    cancel(CancellationException("Firestore error", error))
                } else {
                    val car = snapshot?.toObject(CarLocal::class.java)
                    if (car != null) {
                        trySend(Ressource.Success(car))
                    } else {
                        trySend(Ressource.Error(Exception("Car not found")))
                    }
                }
            }

        awaitClose { listenerRegistration.remove() }
    }

    override fun updateCar(carEntity: CarLocal): Flow<Ressource<Unit>> = callbackFlow {
        trySend(Ressource.Loading())

        firestoreInstance.collection(CARS_COLLECTION).document(carEntity.carID).update("mileage", carEntity.mileage)
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

        firestoreInstance.collection(CARS_COLLECTION).document(idCar.toString())
            .update("mileage", listMileages)
            .addOnSuccessListener {
                trySend(Ressource.Success(Unit))
            }
            .addOnFailureListener { exception ->
                trySend(Ressource.Error(exception))
                cancel(CancellationException("Firestore error", exception))
            }
    }
    override fun deleteCar(car: CarLocal): Flow<Ressource<Unit>> = callbackFlow {
        trySend(Ressource.Loading())

        firestoreInstance.collection(CARS_COLLECTION).document(car.carID.toString())
            .delete()
            .addOnSuccessListener {
                trySend(Ressource.Success(Unit))
            }
            .addOnFailureListener { exception ->
                trySend(Ressource.Error(exception))
                cancel(CancellationException("Firestore error", exception))
            }
    }
    companion object{
        const val CARS_COLLECTION = "cars"
    }
}

interface remoteDataFirebaseSource {
    fun addNewCar(carEntity: CarLocal) : Ressource<Unit>

    fun getCars(idUser: Int): Flow<Ressource<List<CarLocal>>>

    fun getCar(idCar: Int): Flow<Ressource<CarLocal>>

    fun updateCar(carEntity: CarLocal) : Flow<Ressource<Unit>>

    fun updateCarMileage(listMileages: List<Int>, idCar: Int) : Flow<Ressource<Unit>>

    fun deleteCar(car: CarLocal) : Flow<Ressource<Unit>>
}