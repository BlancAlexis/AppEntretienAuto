package com.example.manageyourcar.dataLayer.model

import com.example.manageyourcar.R

enum class MaintenanceServiceType(val image: Int)    {

    VIDANGE(R.drawable.vidange),
    FREINS(R.drawable.vidange),
    PNEUS(R.drawable.vidange);

    fun getImageEnum(): Int {
        return image
    }
}