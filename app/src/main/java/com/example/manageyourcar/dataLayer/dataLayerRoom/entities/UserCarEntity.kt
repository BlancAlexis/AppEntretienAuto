package com.example.manageyourcar.dataLayer.dataLayerRoom.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    primaryKeys = ["userID", "carID"],
    foreignKeys = arrayOf(
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = arrayOf("userID"),
            childColumns = arrayOf("foreignUserID"),
            onDelete = ForeignKey.CASCADE
        )
    , ForeignKey(
        entity = CarEntity::class,
        parentColumns = arrayOf("carID"),
        childColumns = arrayOf("foreignCarID"),
        onDelete = ForeignKey.CASCADE
    )
) )
data class UserCarEntity(
    @PrimaryKey(autoGenerate = true)
    val foreignUserID: Int,
    val foreignCarID: Int
)