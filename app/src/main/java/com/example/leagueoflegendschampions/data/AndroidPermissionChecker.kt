package com.example.leagueoflegendschampions.data

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.example.data.PermissionChecker
import com.example.data.PermissionChecker.Permission
import com.example.data.PermissionChecker.Permission.COARSE_LOCATION

class AndroidPermissionChecker(private val application: Application): PermissionChecker {

    override suspend fun check(permission: Permission): Boolean =
        ContextCompat.checkSelfPermission(
                application,
                permission.toAndroidId()
        ) == PackageManager.PERMISSION_GRANTED

}

private fun Permission.toAndroidId()= when(this){
    COARSE_LOCATION -> Manifest.permission.ACCESS_COARSE_LOCATION
}