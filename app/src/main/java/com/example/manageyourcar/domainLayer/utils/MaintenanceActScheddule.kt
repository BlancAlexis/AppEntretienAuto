package com.example.manageyourcar.domainLayer.utils

enum class MaintenanceActScheddule( val km: Int){
    PNEUS(3000),
    FREINS(30000),
    VIDANGE(30000),
    FILTRE_AIR(30000),
    FILTRE_HABITACLE(30000),
    FILTRE_GAZOLE(30000),
    COURROIE_DISTRIBUTION(30000),
    BOUGIES_ALLUMAGE(30000),
    BOUGIES_PRECHAUFFAGE(30000),
    LIQUIDE_REFROIDISSEMENT(30000),
    LIQUIDE_FREIN(30000),
    LIQUIDE_DIRECTION_ASSISTEE(30000),
    HUILE_MOTEUR(30000),
    HUILE_BOITE_VITESSE(30000),
    HUILE_PONT(30000),
}