package com.example.manageyourcar.domainLayer.mappers

import com.example.manageyourcar.dataLayer.model.User
import com.example.manageyourcar.dataLayer.room.entities.UserEntity

object UserMappers {

    fun User.toUserEntity(): UserEntity {
        return UserEntity(
            login = this.login,
            password = this.password,
            firstname = this.firstname,
            lastname = this.lastname
        )
    }

    fun UserEntity.toUser(): User {
        return User(
            id = this.userID,
            login = this.login,
            password = this.password,
            firstname = this.firstname,
            lastname = this.lastname
        )
    }
}