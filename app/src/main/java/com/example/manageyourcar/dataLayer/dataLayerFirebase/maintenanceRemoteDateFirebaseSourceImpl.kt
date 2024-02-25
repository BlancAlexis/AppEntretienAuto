package com.example.manageyourcar.dataLayer.dataLayerFirebase

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource

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
            firestoreInstance.collection(MAINTENANCES_COLLECTION).add(entretien)
            Ressource.Success(Unit)
        } catch (e: java.lang.Exception) {
            Ressource.Error(e)
        }
    }
    override fun deleteServicing(idServicing: Int): Ressource<Unit> {
        return try {
            firestoreInstance.collection(MAINTENANCES_COLLECTION).document(idServicing.toString()).delete()
            Ressource.Success(Unit)
        } catch (e: java.lang.Exception) {
            Ressource.Error(e)
        }
    }
    override fun getAllUserMaintenance(): Flow<Ressource<List<Entretien>>> = callbackFlow {
        trySend(Ressource.Loading())

        val listenerRegistration = firestoreInstance.collection(MAINTENANCES_COLLECTION)
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

    companion object{
        const val MAINTENANCES_COLLECTION = "maintenances"
    }
}