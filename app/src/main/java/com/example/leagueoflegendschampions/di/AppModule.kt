package com.example.leagueoflegendschampions.di

import android.app.Application
import androidx.room.Room
import com.example.data.repository.PermissionChecker
import com.example.data.source.LocalDataSource
import com.example.data.source.LocationDataSource
import com.example.data.source.RemoteDataSource
import com.example.leagueoflegendschampions.framework.AndroidPermissionChecker
import com.example.leagueoflegendschampions.framework.PlayServicesLocationDataSource
import com.example.leagueoflegendschampions.framework.database.ChampionDatabase
import com.example.leagueoflegendschampions.framework.database.RoomDadaSource
import com.example.leagueoflegendschampions.framework.server.ChampionDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app,
        ChampionDatabase::class.java, "champion-db"
    )

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = ChampionDataSource()

    @Provides
    fun localDataSourceProvider(db: ChampionDatabase): LocalDataSource = RoomDadaSource(db)

    @Provides
    fun permissionCheckerProvider(app: Application): PermissionChecker = AndroidPermissionChecker(app)

    @Provides
    fun locationProvider(app: Application): LocationDataSource = PlayServicesLocationDataSource(app)
}