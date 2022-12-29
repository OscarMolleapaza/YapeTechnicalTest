package com.omolleapaza.yapetechnicaltest.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.omolleapaza.yapetechnicaltest.R
import com.omolleapaza.yapetechnicaltest.data.RecipeRepository
import com.omolleapaza.yapetechnicaltest.data.remote.RecipeRemoteDataSource
import com.omolleapaza.yapetechnicaltest.data.remote.RemoteApi
import com.omolleapaza.yapetechnicaltest.databinding.FragmentMapBinding
import com.omolleapaza.yapetechnicaltest.extensions.createFactory
import com.omolleapaza.yapetechnicaltest.viewmodel.MainViewModel
import kotlinx.serialization.json.Json

class UbicationFragment : Fragment(), OnMapReadyCallback {

    lateinit var _binding: FragmentMapBinding

    private lateinit var map:GoogleMap

    private val args: UbicationFragmentArgs by navArgs()


    private val recipeRepository: RecipeRepository by lazy {
        RecipeRemoteDataSource(RemoteApi(), Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
        })
    }


    private val viewModel by viewModels<MainViewModel> {
        MainViewModel(recipeRepository).createFactory()
    }



    private fun handleMoveCamera(value: LatLng, zoomLevel: Float) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(value, zoomLevel))
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(inflater, container, false)

        _binding.viewModelMap = viewModel
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding.lifecycleOwner = this.viewLifecycleOwner
        //viewModel.setupData(args.recipeUI)
        _binding.executePendingBindings()

        handleFragmentOfMaps()
    }


    private fun handleFragmentOfMaps(){

        val mapFragment = childFragmentManager.findFragmentById(R.id.fragmentContainerMap) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }
    @SuppressLint("PotentialBehaviorOverride")
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.mapType = GoogleMap.MAP_TYPE_NORMAL
        map.uiSettings.isMapToolbarEnabled = false
        args.recipeUI.let {
            val latLng = LatLng(-15.837061, -70.020526)

            handleMoveCamera(latLng, 12f)

            map.addMarker(MarkerOptions().apply {
                this.position(latLng)


            })

            map.setOnMarkerClickListener {
                Toast.makeText(requireContext(), latLng.toString(),Toast.LENGTH_LONG).show()
                true

            }
        }
    }


}
