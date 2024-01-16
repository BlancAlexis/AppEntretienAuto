package com.example.manageyourcar.dataLayer.dataLayerRoom.converter

import androidx.room.TypeConverter

class CarConverter {
    @TypeConverter
    fun fromListIntToString(intList: List<Int>): String = intList.toString()

    @TypeConverter
    fun toListIntFromString(stringList: String): List<Int> {
        val result = ArrayList<Int>()
        val split = stringList.replace("[", "").replace("]", "").replace(" ", "").split(",")
        for (n in split) {
            result.add(n.toInt())
        }
        return result
    }
}