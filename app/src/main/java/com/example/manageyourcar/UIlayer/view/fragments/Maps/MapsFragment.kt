package com.example.manageyourcar.UIlayer.view.fragments.Maps

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.manageyourcar.BuildConfig
import com.example.manageyourcar.R
import com.example.manageyourcar.UIlayer.viewmodel.MapsViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var onLocationChanged: LocationCallback

    private val key = BuildConfig.MAPS_API_KEY
    val mapsViewModel by viewModels<MapsViewModel>()
    private lateinit var googleMap: GoogleMap
    private val callback = OnMapReadyCallback { googleMap ->
        this.googleMap = googleMap
        getPosition()
        getLocationUpdates()
    }

    private fun getLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.e("MapsFragment", "getLocationUpdates: permission not granted")
            return
        }


        var fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())


         onLocationChanged = object : LocationCallback() {
            override fun onLocationResult(location: LocationResult) {
                location

                if (location.locations.isNotEmpty()) {
                    Log.i("TAG", "onLocationResult: ${location.locations[0].latitude} ${location.locations[0].longitude}")
                    val currentPosition =
                        LatLng(location.locations[0].latitude, location.locations[0].longitude)
                    googleMap.addMarker(
                                    MarkerOptions().position(currentPosition).title("Vous êtes ici!")
                                        //.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(requireActivity().resources, R.drawable.eu)))
                    //Marche pas avec svg
                    )
                }
            }
        }

        fusedLocationProviderClient.requestLocationUpdates(
            LocationRequest.create()
                .setPriority(Priority.PRIORITY_LOW_POWER) //Pas besoin d'une grosse précision pour ce que l'on fait
                .setInterval(60000)
                .setFastestInterval(500), onLocationChanged, Looper.getMainLooper()
        )

    }

    fun getPosition() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val currentPosition = LatLng(location.latitude, location.longitude)
                googleMap.addMarker(
                    MarkerOptions().position(currentPosition).title("Vous êtes ici!")
                )
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentPosition))
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if(::onLocationChanged.isInitialized) {
            fusedLocationClient.removeLocationUpdates(onLocationChanged)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    companion object {
        fun newInstance(): MapsFragment {
            return MapsFragment()
        }
    }

}