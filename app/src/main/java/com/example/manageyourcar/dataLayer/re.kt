package com.example.manageyourcar.dataLayer

import android.app.Activity
import com.example.manageyourcar.dataLayer.dataLayerRetrofit.util.Ressource
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow

interface AuthRepository {
    fun createUser(email: String, password: String): Flow<Ressource<FirebaseUser>>
    fun loginUser(email: String, password: String): Flow<Ressource<FirebaseUser>>
    fun logoutUser(): Ressource<Unit>
    fun autoLogUser(): Ressource<FirebaseUser>
}

class AuthRepositoryImpl(private val dataSource: AuthDataSource) : AuthRepository {
    override fun createUser(email: String, password: String): Flow<Ressource<FirebaseUser>> {
        return dataSource.createUser(email, password)
    }

    override fun loginUser(email: String, password: String): Flow<Ressource<FirebaseUser>> {
        return dataSource.loginUser(email, password)
    }

    override fun logoutUser(): Ressource<Unit> {
        return dataSource.logoutUser()
    }

    override fun autoLogUser(): Ressource<FirebaseUser> {
        return dataSource.autoLogUser()
    }

}

interface AuthDataSource {
    fun createUser(email: String, password: String): Flow<Ressource<FirebaseUser>>
    fun loginUser(email: String, password: String): Flow<Ressource<FirebaseUser>>
    fun logoutUser(): Ressource<Unit>
    fun autoLogUser(): Ressource<FirebaseUser>

}

class AuthDataSourceImpl(
    private val auth: FirebaseAuth
) : AuthDataSource {
    override fun createUser(email: String, password: String): Flow<Ressource<FirebaseUser>> {
        return callbackFlow {
            val listener = object : OnCompleteListener<AuthResult> {
                override fun onComplete(task: Task<AuthResult>) {
                    if (task.isSuccessful) {
                        trySend(Ressource.Success(auth.currentUser))
                    } else {
                        trySend(Ressource.Error(task.exception!!))
                    }
                    cancel()
                }
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(listener)

            awaitClose {
                auth.removeAuthStateListener{listener}
            }
        }
    }


    override fun loginUser(email: String, password: String): Flow<Ressource<FirebaseUser>> {
        return callbackFlow {
            val listener = object : OnCompleteListener<AuthResult> {
                override fun onComplete(task: Task<AuthResult>) {
                    if (task.isSuccessful) {
                        val user = auth.currentUser!!
                        trySend(Ressource.Success(user))
                    } else {
                        trySend(Ressource.Error(task.exception!!))
                    }
                    cancel()
                }
            }
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(listener)
            awaitClose {
                auth.removeAuthStateListener { listener }
            }
        }
    }

    override fun logoutUser(): Ressource<Unit> {
        return try {
            auth.signOut()
            Ressource.Success(Unit)
        } catch (e: Exception) {
            Ressource.Error(e)

        }
    }

    override fun autoLogUser(): Ressource<FirebaseUser> {
        auth.currentUser?.let {
            return  Ressource.Success(it) }
        return Ressource.Error(message = "user not in cache")
        }
    }