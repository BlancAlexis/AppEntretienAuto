package com.example.manageyourcar.dataLayer.model

sealed interface MaintenanceService{
    data class Vidange(
        val name: String = "Vidange",
        val category : String = "?",
        val reminder : Int =15000
        ) : MaintenanceService
    data class Freins (
        val name: String = "Freins",
        val category : String = "?",
        val reminder : Int =15000
    ): MaintenanceService
    data class Pneus (
        val name: String = "Pneus",
        val category : String = "?",
        val reminder : Int =15000
    ): MaintenanceService
}