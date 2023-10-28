package com.example.manageyourcar.dataLayer.dataLayerRoom.repository

import com.example.manageyourcar.dataLayer.model.User

interface UserRepository {
    fun addNewUser(user: User)
    fun getUser(idUser: Int): User
    fun getUsers(): List<User>
    fun updateUser(user: User)
    fun deleteUser(idUser: Int)
}