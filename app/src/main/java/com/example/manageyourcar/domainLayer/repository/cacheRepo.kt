package com.example.manageyourcar.domainLayer.repository

interface cacheRepo {
    fun getUserID() : Int
    fun putUserID()
}