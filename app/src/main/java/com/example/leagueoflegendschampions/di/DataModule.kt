package com.example.leagueoflegendschampions.di

import com.example.data.repository.ChampionRepository
import com.example.data.repository.PermissionChecker
import com.example.data.repository.RegionRepository
import com.example.data.source.LocalDataSource
import com.example.data.source.LocationDataSource
import com.example.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun championRepositoryProvider(remoteDataSource: RemoteDataSource,
                                   localDataSource: LocalDataSource,
                                   regionRepository: RegionRepository
    ) = ChampionRepository(remoteDataSource, localDataSource, regionRepository)

    @Provides
    fun regionRepositoryProvider(locationDataSource: LocationDataSource,
                                 permissionChecker: PermissionChecker
    ) = RegionRepository(locationDataSource, permissionChecker)

}