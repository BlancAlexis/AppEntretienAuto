package com.example.manageyourcar.dataLayer.dataLayerFirebase

import com.example.manageyourcar.dataLayer.dataLayerRoom.dao.MaintenanceWithCarEntity
import com.example.manageyourcar.dataLayer.model.Entretien
import com.example.manageyourcar.domainLayer.repository.room.ServicingRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow

class maintenanceRemoteDateFirebaseSourceImpl(private val firestoreInstance : FirebaseFirestore) : ServicingRepository {
    override fun addNewServicing(entretien: Entretien) {
        firestoreInstance.collection("maintenance").add(entretien)
    }

    override fun getAllServicing(): List<Entretien> {
        firestoreInstance.collection("maintenance").get()
            .addOnSuccessListener { result ->
                return@addOnSuccessListener result.toObjects(Entretien::class.java)
            }
            .addOnFailureListener { exception ->
                // Erreur lors de la récupération des documents
            }
    }

    override fun getAllUserMaintenance(): Flow<List<Entretien>> {
        firestoreInstance.collection("maintenance").get()
            .addOnSuccessListener { result ->
                return@addOnSuccessListener result.toObjects(Entretien::class.java)
            }
            .addOnFailureListener { exception ->
                // Erreur lors de la récupération des documents
            }
    }

    override fun getServicing(idServicing: Int): Entretien {
        firestoreInstance.collection("maintenance").document(idServicing.toString()).get()
            .addOnSuccessListener { result ->
                return@addOnSuccessListener result.toObject(Entretien::class.java)
            }
            .addOnFailureListener { exception ->
                // Erreur lors de la récupération des documents
            }
    }

    override fun updateServicing(entretien: Entretien) {
        firestoreInstance.collection("maintenance").document(entretien.id.toString()).set(entretien)
    }

    override fun deleteServicing(idServicing: Int) {
        firestoreInstance.collection("maintenance").document(idServicing.toString()).delete()
    }

    override fun getMaintenceActWithCar(userId: Int): Flow<List<MaintenanceWithCarEntity>> {
        firestoreInstance.collection("maintenance").whereEqualTo("user_id", userId).get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val maintenance = document.toObject(Entretien::class.java)
                    val car = firestoreInstance.collection("cars").document(maintenance.car_id.toString()).get()
                        .addOnSuccessListener { result ->
                            val carEntity = result.toObject(CarEntity::class.java)
                            return@addOnSuccessListener MaintenanceWithCarEntity(maintenance, carEntity)
                        }
                }
            }
    }
}