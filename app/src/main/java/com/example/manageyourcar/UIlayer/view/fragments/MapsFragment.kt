package com.example.manageyourcar.UIlayer.view.fragments

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

            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.e("MapsFragment", "getLocationUpdates: permission not granted")
            return
        }


        var fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())



        val onLocationChanged: LocationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                p0 ?: return

                if (p0.locations.isNotEmpty()) {
                    val bitmap = BitmapFactory.decodeResource(requireActivity().resources, R.drawable.voiture_de_course)
                    Log.i("TAG", "onLocationResult: ${p0.locations[0].latitude} ${p0.locations[0].longitude}")
                    val currentPosition = LatLng(p0.locations[0].latitude, p0.locations[0].longitude)
                    googleMap.addMarker(MarkerOptions().position(currentPosition).title("Vous êtes ici!"))
                //.icon(BitmapDescriptorFactory.fromBitmap(BitmapUtils.decodeResource(requireActivity().resources,R.drawable.voiture_de_course))))

                }
            }
        }

        val locationManager = fusedLocationProviderClient.requestLocationUpdates(
            LocationRequest.create()
                .setPriority(Priority.PRIORITY_LOW_POWER)
                .setInterval(1000)
                .setFastestInterval(500), onLocationChanged, Looper.getMainLooper()
        )

    }

    fun getPosition(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val currentPosition = LatLng(location.latitude, location.longitude)
                googleMap.addMarker(MarkerOptions().position(currentPosition).title("Vous êtes ici!"))
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentPosition))
            }
        }
    }


    //start location updates
    override fun onStart() {
        super.onStart()

    }


    // stop location updates
    private fun stopLocationUpdates() {
        //fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    // stop receiving location update when activity not visible/foreground
    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    // start receiving location update when activity  visible/foreground
    override fun onResume() {
        super.onResume()
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

        // Obtenir le fournisseur de localisation GPS

    }

    companion object {
        fun newInstance(): MapsFragment {
            return MapsFragment()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}