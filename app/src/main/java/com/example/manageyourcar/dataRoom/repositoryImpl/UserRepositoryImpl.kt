package com.example.manageyourcar.dataRoom.repositoryImpl

import android.util.Log
import com.example.manageyourcar.dataRoom.dao.CarDao
import com.example.manageyourcar.dataRoom.model.User
import com.example.manageyourcar.dataRoom.repository.UserRepository
import org.koin.core.component.KoinComponent

class UserRepositoryImpl(private val carDao: CarDao) : UserRepository, KoinComponent {
    override fun addNewUser(user: User) {
        Log.i("", "")
    }

    override fun getUsers(): List<User> {
        return listOf()
    }

    override fun updateUser(user: User) {
        Log.i("", "")
    }

    override fun deleteUser(idUser: Long) {
        Log.i("","")    }
}