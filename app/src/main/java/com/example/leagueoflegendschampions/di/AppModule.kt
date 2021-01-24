package com.example.leagueoflegendschampions.di

import android.app.Application
import androidx.room.Room
import com.example.data.PermissionChecker
import com.example.data.source.LocalDataSource
import com.example.data.source.LocationDataSource
import com.example.data.source.RemoteDataSource
import com.example.leagueoflegendschampions.data.AndroidPermissionChecker
import com.example.leagueoflegendschampions.data.PlayServicesLocationDataSource
import com.example.leagueoflegendschampions.data.database.ChampionDatabase
import com.example.leagueoflegendschampions.data.database.RoomDataSource
import com.example.leagueoflegendschampions.data.server.ChampionDbDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app,
        ChampionDatabase::class.java,
        "champion-db"
    ).build()

    @Provides
    fun localDataSourceProvider(db: ChampionDatabase) : LocalDataSource=
        RoomDataSource(db)

    @Provides
    fun remoteDataSourceProvider() : RemoteDataSource=
        ChampionDbDataSource()

    @Provides
    fun locationDataSourceProvider(app : Application) : LocationDataSource =
        PlayServicesLocationDataSource(app)

    @Provides
    fun permissionCheckerProvider(app : Application) : PermissionChecker =
        AndroidPermissionChecker(app)

}