package com.example.leagueoflegendschampions.di

import com.example.data.PermissionChecker
import com.example.data.repository.ChampionRepository
import com.example.data.repository.RegionRepository
import com.example.data.source.LocalDataSource
import com.example.data.source.LocationDataSource
import com.example.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun championRepositoryProvider(regionRepository: RegionRepository,
                                      localDataSource: LocalDataSource,
                                      remoteDataSource: RemoteDataSource) =
        ChampionRepository(regionRepository, localDataSource, remoteDataSource)

    @Provides
    fun regionRepositoryProvider(locationDataSource: LocationDataSource,
                                 permissionChecker: PermissionChecker
    ) =
        RegionRepository(locationDataSource, permissionChecker)

}