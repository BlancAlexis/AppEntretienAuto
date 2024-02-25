package com.example.manageyourcar.UIlayer.view.fragments.Maps

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.manageyourcar.R
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
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var onUserLocationChanged: LocationCallback

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


        onUserLocationChanged = object : LocationCallback() {
            override fun onLocationResult(location: LocationResult) {
                location

                if (location.locations.isNotEmpty()) {
                    Log.i(
                        "TAG",
                        "onLocationResult: ${location.locations[0].latitude} ${location.locations[0].longitude}"
                    )
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
                .setFastestInterval(500), onUserLocationChanged, Looper.getMainLooper()
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
        if (::onUserLocationChanged.isInitialized) {
            fusedLocationClient.removeLocationUpdates(onUserLocationChanged)
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


        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (!allPermissionsGranted(permissions.toMutableMap())) {
                // Handle denied permissions
                Toast.makeText(
                    requireContext(),
                    "Location permissions required for maps",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // Proceed with map functionality
                // ...
            }
        }

        val locationPermissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        if (!hasPermissions(requireContext(), locationPermissions)) {
            permissionLauncher.launch(locationPermissions)
        } else {
            // Proceed with map functionality if permissions are already granted
            // ...
        }
    }

    private fun hasPermissions(context: Context, permissions: Array<String>): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    private fun allPermissionsGranted(permissions: MutableMap<String, Boolean>): Boolean {
        return permissions.values.all { it }
    }

    companion object {
        fun newInstance(): MapsFragment {
            return MapsFragment()
        }
    }
}