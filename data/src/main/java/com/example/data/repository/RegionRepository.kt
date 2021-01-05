package com.example.data.repository

import com.example.data.PermissionChecker
import com.example.data.PermissionChecker.Permission.COARSE_LOCATION
import com.example.data.source.LocationDataSource

class RegionRepository(
        private val locationDataSource: LocationDataSource,
        private val permissionChecker: PermissionChecker
) {
    companion object{
        private const val DEFAULT_REGION = "US"
    }

    suspend fun findLastRegion(): String{
        return if (permissionChecker.check(COARSE_LOCATION)){
            locationDataSource.findLastRegion() ?: DEFAULT_REGION
        }else{
            DEFAULT_REGION
        }
    }


}

