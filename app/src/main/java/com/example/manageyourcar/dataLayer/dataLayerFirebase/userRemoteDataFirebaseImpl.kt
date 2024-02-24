package com.example.manageyourcar.dataLayer.dataLayerFirebase

import com.example.manageyourcar.dataLayer.model.User
import com.example.manageyourcar.domainLayer.repository.room.UserRepository
import com.google.firebase.firestore.FirebaseFirestore

class userRemoteDataFirebaseImpl(private val firestoreInstance : FirebaseFirestore) : UserRepository {
    override suspend fun logUser(email: String, password: String): User {
        return User(0, "test    ", "test", "test", "test")
        /*    firestoreInstance.collection("users").whereEqualTo("email", email).whereEqualTo("password", password).get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        return@addOnSuccessListener document.toObject(User::class.java)
                    }
                }
                .addOnFailureListener { exception ->
                    // Erreur lors de la récupération des documents
                }*/
     }

    override suspend fun addNewUser(user: User) {
        firestoreInstance.collection("users").add(user)
    }

    override suspend fun getUser(idUser: Int): User {
        return User(0, "test   ", "test", "test", "test")
/*        firestoreInstance.collection("users").document(idUser.toString()).get()
            .addOnSuccessListener { result ->
                return@addOnSuccessListener result.toObject(User::class.java)
            }
            .addOnFailureListener { exception ->
                // Erreur lors de la récupération des documents
            }*/
    }

    override suspend fun getUsers(): List<User> {
        return listOf(User(0, "test   ", "test", "test", "test"))
//        firestoreInstance.collection("users").get()
//            .addOnSuccessListener { result ->
//                return@addOnSuccessListener result.toObjects(User::class.java)
//            }
//            .addOnFailureListener { exception ->
//                // Erreur lors de la récupération des documents
//            }
    }

    override suspend fun updateUser(user: User) {
        /*firestoreInstance.collection("users").document(user.userID.toString()).set(user)*/
    }

    override suspend fun deleteUser(idUser: Int) {
        firestoreInstance.collection("users").document(idUser.toString()).delete()
    }
}