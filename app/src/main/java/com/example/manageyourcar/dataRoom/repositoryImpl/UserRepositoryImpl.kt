package com.example.manageyourcar.dataRoom.repositoryImpl

import android.util.Log
import com.example.manageyourcar.dataRoom.dao.CarDao
import com.example.manageyourcar.dataRoom.dao.UserDao
import com.example.manageyourcar.dataRoom.entities.CarEntity
import com.example.manageyourcar.dataRoom.entities.UserEntity
import com.example.manageyourcar.dataRoom.model.Car
import com.example.manageyourcar.dataRoom.model.User
import com.example.manageyourcar.dataRoom.repository.UserRepository
import org.koin.core.component.KoinComponent

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository, KoinComponent {
    override fun addNewUser(user: User) {
        val userEntity = UserEntity(
            login = user.login,
            password = user.password,
            firstname = user.firstname,
            lastname = user.lastname
        )
        userDao.addNewUser(userEntity)
    }

    override fun getUser(idUser: Int): User {
        val brutResult = userDao.getUser(idUser);

        return User(
            id = brutResult.id,
            login = brutResult.login,
            password = brutResult.password,
            firstname = brutResult.firstname,
            lastname= brutResult.lastname
        );
    }

    override fun getUsers(): List<User> {
        val brutResult = userDao.getUsers();
        val resultUser: MutableList<User> = arrayListOf();

        for(element in brutResult){
            resultUser.add(User(0, element.login, element.password, element.firstname, element.lastname))
        }

        return resultUser;
    }

    override fun updateUser(user: User) {
        val userEntity = UserEntity(
            id = user.id,
            login = user.login,
            password = user.password,
            firstname = user.firstname,
            lastname = user.lastname
        )
        userDao.updateUser(userEntity)
    }

    override fun deleteUser(idUser: Int) {
        userDao.deleteUser(idUser)
    }
}