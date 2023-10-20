package com.example.manageyourcar.dataRoom.repository

import com.example.manageyourcar.dataRoom.model.User
interface UserRepository {
    fun addNewUser(user: User)
    fun getUsers(): List<User>
    fun updateUser(user: User)
    fun deleteUser(idUser: Int)
}