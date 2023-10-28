package com.example.manageyourcar.domainLayer.repository.room

import com.example.manageyourcar.dataLayer.model.User

interface UserRepository {
    fun addNewUser(user: User)
    fun getUser(idUser: Int): User
    fun getUsers(): List<User>
    fun updateUser(user: User)
    fun deleteUser(idUser: Int)
}