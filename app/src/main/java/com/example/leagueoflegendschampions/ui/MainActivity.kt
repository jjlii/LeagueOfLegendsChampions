package com.example.leagueoflegendschampions.ui

import android.Manifest
import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.View
import com.example.leagueoflegendschampions.databinding.ActivityMainBinding
import com.example.leagueoflegendschampions.module.LolDb
import com.example.leagueoflegendschampions.startActivity
import com.example.leagueoflegendschampions.toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.BasePermissionListener
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class MainActivity : CoroutineScopeActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient


    private val adapter = ChampionAdapter {
        startActivity<DetailActivity> {
            putExtra(DetailActivity.CHAMPION, it)
        }
    }

    private val languages = arrayListOf("en_US","cs_CZ","de_DE","el_GR","en_AU","en_GB",
            "en_PH","en_SG","es_AR","es_ES","es_MX","fr_FR","hu_HU","id_ID","it_IT","ja_JP","ko_KR",
            "pl_PL","pt_BR","ro_RO","ru_RU","th_TH","tr_TR","vn_VN","zh_CN","zh_MY","zh_TW")
  
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        launch {
            val location = getLocation()
            val region = getRegionFromLocation(location)
            binding.progress.visibility = View.VISIBLE
            val championList = LolDb.service.listChampionsAsync(getLanguage(region))
            adapter.championList = championList.data.values.toList()
            binding.progress.visibility = View.GONE
        }
        binding.championListView.adapter = adapter
    }

    private suspend fun getLocation(): Location?{
        val success = requestCoarseLocationPermission()
        return if (success) findLastLocation() else null
    }

    @SuppressLint("MissingPermission")
    private suspend fun findLastLocation(): Location? =
            suspendCancellableCoroutine { continuation ->
                fusedLocationClient.lastLocation
                        .addOnCompleteListener {
                            continuation.resume(it.result)
                        }
            }

    private suspend fun  requestCoarseLocationPermission():Boolean =
            suspendCancellableCoroutine {
                continuation->
                Dexter
                        .withActivity(this@MainActivity)
                        .withPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                        .withListener(object : BasePermissionListener() {
                            override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                                continuation.resume(true)
                            }

                            override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                                continuation.resume(false)
                            }
                        }
                        ).check()
            }

    private fun getRegionFromLocation(location: Location?): String{
        val geocoder = Geocoder(this@MainActivity)
        val fromLocation = location?.let {
            geocoder.getFromLocation(
                    location.latitude,
                    location.longitude,
                    1
            )
        }
        return fromLocation?.firstOrNull()?.countryCode ?: "US"
    }

    private fun getLanguage(region : String): String{
        val language = languages.firstOrNull{
            it.contains(region)
        } ?: "en_US"
        toast(language)
        return language
    }
}
