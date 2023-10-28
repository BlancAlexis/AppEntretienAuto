package com.example.manageyourcar.dataLayer.dataLayerRetrofit.model

import com.google.android.gms.maps.model.LatLng


data class Place(
    val id: String,
    val name: String?,
    val imageUrl: String?,
    val isOpen: Boolean?,
    val rating: Double?,
    val latLng: LatLng?
)
