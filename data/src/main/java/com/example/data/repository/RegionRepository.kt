package com.example.data.repository

import com.example.data.repository.PermissionChecker.Permission.COARSE_LOCATION
import com.example.data.source.LocationDataSource

class RegionRepository(private val locationDataSource: LocationDataSource,
                       private val permissionChecker: PermissionChecker){

    companion object{
        private const val DEFAULT_REGION = "US"
    }

    suspend fun findLastLocation(): String{
        return if (permissionChecker.check(COARSE_LOCATION)){
            locationDataSource.findLastLocation() ?: DEFAULT_REGION
        }else{
            DEFAULT_REGION
        }
    }
}

interface PermissionChecker {

    enum class Permission { COARSE_LOCATION }

    suspend fun check(permission: Permission): Boolean
}