package com.example.data.repository

import com.example.data.PermissionChecker
import com.example.data.source.LocationDataSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RegionRepositoryTest{

    @Mock
    private lateinit var locationDataSource: LocationDataSource
    @Mock
    private lateinit var permissionChecker: PermissionChecker

    private lateinit var regionRepository: RegionRepository

    @Before
    fun setUp() {
        regionRepository = RegionRepository(locationDataSource, permissionChecker)
    }

    @Test
    fun `coarse location permission is granted findLastRegion should return user region`() {
        runBlocking {
            val region = "ES"
            whenever(permissionChecker.check(any())).thenReturn(true)
            whenever(locationDataSource.findLastRegion()).thenReturn(region)

            val result = regionRepository.findLastRegion()

            assertEquals(region, result)
        }
    }

    @Test
    fun `coarse location permission is granted locationDataSource return null then findLastRegion return default region`() {
        runBlocking {
            val region = "US"
            whenever(permissionChecker.check(any())).thenReturn(true)
            whenever(locationDataSource.findLastRegion()).thenReturn(null)

            val result = regionRepository.findLastRegion()

            assertEquals(region, result)
        }
    }

    @Test
    fun `coarse location permission is not granted findLastRegion should return default region`() {
        runBlocking {
            val region = "US"
            whenever(permissionChecker.check(any())).thenReturn(false)

            val result = regionRepository.findLastRegion()

            assertEquals(region, result)
        }
    }
}