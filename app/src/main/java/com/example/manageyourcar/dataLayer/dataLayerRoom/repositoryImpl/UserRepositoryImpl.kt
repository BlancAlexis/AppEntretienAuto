package com.example.manageyourcar.dataLayer.dataLayerRoom.repositoryImpl

import com.example.manageyourcar.dataLayer.dataLayerRoom.dao.UserDao
import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.UserEntity
import com.example.manageyourcar.domainLayer.repository.room.UserRepository
import com.example.manageyourcar.dataLayer.model.User
import com.example.manageyourcar.domainLayer.mappers.UserMappers.toUser
import com.example.manageyourcar.domainLayer.mappers.UserMappers.toUserEntity
import org.koin.core.component.KoinComponent

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository, KoinComponent {
    override suspend fun logUser(email: String, password: String): User {
        return userDao.logUser(email, password).toUser()
    }

    override suspend fun addNewUser(user: User) {
        return userDao.addNewUser(user.toUserEntity())
    }

    override suspend fun getUser(idUser: Int): User {
        return userDao.getUser(idUser).toUser()
    }

    override suspend fun getUsers(): List<User> {
        val brutResult = userDao.getUsers()
        val resultUser: MutableList<User> = arrayListOf()

        for (element in brutResult) {
            resultUser.add(
                User(
                    0,
                    element.login,
                    element.password,
                    element.firstname,
                    element.lastname
                )
            )
        }
        return resultUser
    }

    override suspend fun updateUser(user: User) {
       return userDao.updateUser(user.toUserEntity())
    }

    override suspend fun deleteUser(idUser: Int) {
        userDao.deleteUser(idUser)
    }
}