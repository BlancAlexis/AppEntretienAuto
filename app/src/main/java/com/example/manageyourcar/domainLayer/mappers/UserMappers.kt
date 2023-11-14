package com.example.manageyourcar.domainLayer.mappers

import com.example.manageyourcar.dataLayer.dataLayerRoom.entities.UserEntity
import com.example.manageyourcar.dataLayer.model.User

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
            id = this.id,
            login = this.login,
            password = this.password,
            firstname = this.firstname,
            lastname = this.lastname
        )
    }
}