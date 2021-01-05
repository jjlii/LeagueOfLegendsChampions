package com.example.leagueoflegendschampions.framework

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.app.Application
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.example.data.repository.PermissionChecker
import com.example.data.repository.PermissionChecker.Permission
import com.example.data.repository.PermissionChecker.Permission.COARSE_LOCATION

class AndroidPermissionChecker(private val application: Application): PermissionChecker {

    override suspend fun check(permission: Permission): Boolean =
            ContextCompat.checkSelfPermission(
                    application,
                    permission.toAndroidId()
            ) == PackageManager.PERMISSION_GRANTED
}

private fun Permission.toAndroidId() = when (this) {
    COARSE_LOCATION -> ACCESS_COARSE_LOCATION
}