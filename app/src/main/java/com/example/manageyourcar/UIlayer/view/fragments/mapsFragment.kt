package com.example.manageyourcar.UIlayer.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.manageyourcar.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.api.model

class mapsFragment : Fragment() {
    private lateinit var placesClient: PlacesClient

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
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
        Places.initialize(requireContext(), BuildConfig.MAPS_API_KEY)
           placesClient = Places.createClient(requireContext())
        fun getGaragesAround(latitude: Double, longitude: Double): List<Garage> {
            // Créer une requête aux services Google Maps
            val request = PlacesClient.textSearch(latitude, longitude, "garage")            // Exécuter la requête
            val response = request.await()

            // Récupérer la liste des garages
            val garages = mutableListOf<Garage>()
            for (place in response.places) {
                val garage = Garage(
                    place.latitude,
                    place.longitude,
                    place.name,
                    place.address
                )
                garages.add(garage)
            }

            return garages
        }

    companion object {
        fun newInstance(): mapsFragment {
            return mapsFragment()
        }
    }
}