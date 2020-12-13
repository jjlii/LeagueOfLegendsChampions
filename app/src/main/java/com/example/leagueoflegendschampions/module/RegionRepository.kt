package com.example.leagueoflegendschampions.module

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class RegionRepository(application: Application) {
    companion object{
        private const val DEFAULT_REGION = "US"
    }

    private val locationDataSource: LocationDataSource = PlayServicesLocationDataSource(application)
    private val coarsePermissionChecker = PermissionChecker(application, Manifest.permission.ACCESS_COARSE_LOCATION)
    private val geocoder = Geocoder(application)

    suspend fun getRegionLanguage(): String = findLastLocation().toRegion()

    private suspend fun findLastLocation(): Location?{
        val success = coarsePermissionChecker.check()
        return if (success) locationDataSource.findLastLocation() else null
    }

    private fun Location?.toRegion(): String{
        val addresses = this?.let{
            geocoder.getFromLocation(latitude, longitude, 1)
        }
        return addresses?.firstOrNull()?.countryCode ?: DEFAULT_REGION
    }



}