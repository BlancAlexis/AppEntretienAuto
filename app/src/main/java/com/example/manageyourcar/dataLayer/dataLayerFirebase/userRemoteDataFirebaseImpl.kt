package com.example.manageyourcar.dataLayer.dataLayerFirebase

import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.example.manageyourcar.dataLayer.model.CarLocal
import com.example.manageyourcar.dataLayer.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.coroutines.cancellation.CancellationException

class userRemoteDataFirebaseImpl(private val firestoreInstance: FirebaseFirestore) :
    UserDataSource {
    override suspend fun logUser(email: String, password: String): Flow<Ressource<User>> = callbackFlow{
     //TODO à adapter firebase auth
        /*


     trySend(Ressource.Loading())
        println("jpasse")
        val listenerRegistration = firestoreInstance.collection(CarFirestoreDataSourceImpl.CARS_COLLECTION)
            .whereEqualTo("ownerID", email)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Ressource.Error(error))
                    cancel(CancellationException("Firestore error", error))
                } else {
                    val user = snapshot?.toObjects(User::class.java) ?: emptyList()
                    trySend(Ressource.Success(user))
                }
            }

        awaitClose { listenerRegistration.remove() }*/
    }

    override suspend fun addNewUser(user: User): Ressource<Unit> {
        return try {
            firestoreInstance.collection(USERS_COLLECTION).document(user.uuid).set(user)
            Ressource.Success(Unit)
        } catch (e: Exception) {
            Ressource.Error(e)
        }
    }

    override suspend fun getUser(uuidUser: String): Flow<Ressource<User>> = callbackFlow {
        trySend(Ressource.Loading())

        val listenerRegistration = firestoreInstance.collection(USERS_COLLECTION).document(uuidUser)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    trySend(Ressource.Error(error))
                    cancel(CancellationException("Firestore error", error))
                } else {
                    val user = snapshot?.toObject(User::class.java)
                    if (user != null) {
                        trySend(Ressource.Success(user))
                    } else {
                        trySend(Ressource.Error(Exception("Car not found")))
                    }
                }
            }

        awaitClose { listenerRegistration.remove() }
    }

    override suspend fun updateUser(user: User): Ressource<Unit> = try {
        firestoreInstance.collection(USERS_COLLECTION).document(user.uuid).update(
            hashMapOf<String, Any>(
                "uuid" to user.uuid,
                "login" to user.login,
                "firstname" to user.firstname,
                "lastname" to user.lastname
            )
        )
        Ressource.Success(Unit)
    } catch (e: Exception) {
        Ressource.Error(e)
    }

    override suspend fun deleteUser(uuid : String): Ressource<Unit> = try {
        firestoreInstance.collection("users").document(uuid).delete()
        Ressource.Success(Unit)
    } catch (e: Exception) {
        Ressource.Error(e)
    }

    companion object {
        const val USERS_COLLECTION = "users"
    }
}
interface UserRepository {
    suspend fun logUser(email: String, password: String): Flow<Ressource<User>>
    suspend fun addNewUser(user: User) : Ressource<Unit>
    suspend fun getUser(uuid : String): Flow<Ressource<User>>
    suspend fun updateUser(user: User) : Ressource<Unit>
    suspend fun deleteUser(uuid : String) : Ressource<Unit>
}

class UserRepositoryImpl( private val dataSource: UserDataSource) : UserRepository{
    override suspend fun logUser(email: String, password: String): Flow<Ressource<User>> {
                return dataSource.logUser(email, password)    }

    override suspend fun addNewUser(user: User): Ressource<Unit> {
return addNewUser(user)   }

    override suspend fun getUser(uuid : String): Flow<Ressource<User>> {
return dataSource.getUser(uuid)    }

    override suspend fun updateUser(user: User): Ressource<Unit> {
        return dataSource.updateUser(user)
    }

    override suspend fun deleteUser(uuid : String): Ressource<Unit> {
        return dataSource.deleteUser(uuid)
    }

}
interface UserDataSource {
    suspend fun logUser(email: String, password: String): Flow<Ressource<User>>
    suspend fun addNewUser(user: User) : Ressource<Unit>
    suspend fun getUser(uuid : String): Flow<Ressource<User>>
    suspend fun updateUser(user: User) : Ressource<Unit>
    suspend fun deleteUser(uuid : String) : Ressource<Unit>
}

