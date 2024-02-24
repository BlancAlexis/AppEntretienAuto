package com.example.manageyourcar.dataLayer.dataLayerFirebase

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.dataLayerRoom.dao.MaintenanceWithCarEntity
import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.CarEntity
import com.example.manageyourcar.dataLayer.model.Entretien
import com.example.manageyourcar.domainLayer.repository.room.ServicingRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.coroutines.cancellation.CancellationException


class MaintenanceRemoteDateFirebaseSourceImpl(private val firestoreInstance: FirebaseFirestore) :
    ServicingRepository {
    override fun addNewServicing(entretien: Entretien): Ressource<Unit> {
        return try {
            firestoreInstance.collection("maintenance").add(entretien)
            Ressource.Success(Unit)
        } catch (e: java.lang.Exception) {
            Ressource.Error(e)
        }
    }

    override fun getAllServicing(): Flow<Ressource<List<Entretien>>> = callbackFlow {
        trySend(Ressource.Loading())

        val listenerRegistration = firestoreInstance.collection("maintenance").get()
            .addOnSuccessListener { result ->
                trySend(Ressource.Success(result.toObjects(Entretien::class.java)))
            }
            .addOnFailureListener { exception ->
                trySend(Ressource.Error(exception))
                cancel(CancellationException("Firestore error", exception))
            } as ListenerRegistration

        awaitClose { listenerRegistration.remove() } // chai pas
    }

    override fun getAllUserMaintenance(): Flow<Ressource<List<Entretien>>> = callbackFlow {
        trySend(Ressource.Loading())

        val listenerRegistration = firestoreInstance.collection("maintenance")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Ressource.Error(error))
                    cancel(CancellationException("Firestore error", error))
                } else {
                    val user = snapshot?.toObjects(Entretien::class.java)
                    if (user != null) {
                        trySend(Ressource.Success(user))
                    } else {
                        trySend(Ressource.Error(Exception("User not found")))
                    }
                }
            }

        awaitClose { listenerRegistration.remove() }
    }

    override fun getServicing(idServicing: Int): Flow<Ressource<Entretien>> {
        TODO("Not yet implemented")
    }

    override fun updateServicing(entretien: Entretien): Ressource<Unit> {
        TODO("Not yet implemented")
    }


    /*
        override fun updateServicing(entretien: Entretien) : Ressource<Unit> {
           return try {
            firestoreInstance.collection("maintenance").document(entretien.id.toString()).set(entretien)
           Ressource.Success(Unit)}
            catch (e : java.lang.Exception){
                Ressource.Error(e)
            }
        } pas utilisée
    */

    override fun deleteServicing(idServicing: Int): Ressource<Unit> {
        return try {
            firestoreInstance.collection("maintenance").document(idServicing.toString()).delete()
            Ressource.Success(Unit)
        } catch (e: java.lang.Exception) {
            Ressource.Error(e)
        }
    }

    override fun getMaintenceActWithCar(userId: Int): Flow<Ressource<List<MaintenanceWithCarEntity>>> {
        TODO("Not yet implemented")
    }


    /*    override fun getMaintenceActWithCar(userId: Int): Flow<Ressource<List<MaintenanceWithCarEntity>>> =
            callbackFlow {
                trySend(Ressource.Loading())

                val listenerRegistration = firestoreInstance.collection("maintenance")
                    .whereEqualTo("user_id", userId)
                    .addSnapshotListener { snapshot, error ->
                        if (error != null) {
                            // Handle error
                        } else {
                            val maintenanceWithCarEntities = snapshot?.documents?.mapNotNull { doc ->
                                val maintenance = doc.toObject(Entretien::class.java)
                                val car = firestoreInstance.collection("cars")
                                    .document(maintenance.car_id.toString()).get()
                                    .addOnSuccessListener { result ->
                                        val carEntity = result.toObject(CarEntity::class.java)
                                        MaintenanceWithCarEntity(maintenance, carEntity)
                                    }
                                    .result // Assuming extension function to await result
                                car
                            }
                            trySend(Ressource.Success(maintenanceWithCarEntities))
                        }
                    }

                awaitClose { listenerRegistration.remove() }
            }*/
}