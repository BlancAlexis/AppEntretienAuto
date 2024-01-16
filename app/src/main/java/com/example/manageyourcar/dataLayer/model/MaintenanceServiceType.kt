package com.example.manageyourcar.dataLayer.model

import com.example.manageyourcar.R

enum class MaintenanceServiceType(val image: Int) {

    VIDANGE(R.drawable.vidange),
    FREINS(R.drawable.quand_changer_ses_freins),
    PNEUS(R.drawable.changement_pneus_min);

    fun getImageEnum(): Int {
        return image
    }
}