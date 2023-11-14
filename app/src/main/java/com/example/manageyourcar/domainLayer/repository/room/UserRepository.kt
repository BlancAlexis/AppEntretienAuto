package com.example.manageyourcar.domainLayer.repository.room

import com.example.manageyourcar.dataLayer.model.User

interface UserRepository {
    suspend fun logUser(email: String, password: String): User
    suspend fun addNewUser(user: User)
    suspend fun getUser(idUser: Int): User
    suspend fun getUsers(): List<User>
    suspend fun updateUser(user: User)
    suspend fun deleteUser(idUser: Int)
}