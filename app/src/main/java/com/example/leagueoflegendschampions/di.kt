package com.example.leagueoflegendschampions

import android.app.Application
import com.example.data.PermissionChecker
import com.example.data.repository.ChampionRepository
import com.example.data.repository.RegionRepository
import com.example.data.source.LocalDataSource
import com.example.data.source.LocationDataSource
import com.example.data.source.RemoteDataSource
import com.example.leagueoflegendschampions.data.AndroidPermissionChecker
import com.example.leagueoflegendschampions.data.PlayServicesLocationDataSource
import com.example.leagueoflegendschampions.data.database.ChampionDatabase
import com.example.leagueoflegendschampions.data.database.RoomDataSource
import com.example.leagueoflegendschampions.data.server.ChampionDbDataSource
import com.example.leagueoflegendschampions.ui.detail.DetailViewModel
import com.example.leagueoflegendschampions.ui.detail.DetailsFragment
import com.example.leagueoflegendschampions.ui.main.MainFragment
import com.example.leagueoflegendschampions.ui.main.MainViewModel
import com.example.usecases.FindChampionByIdUseCase
import com.example.usecases.GetChampionsUseCase
import com.example.usecases.ToggleChampionFavoriteUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI(){
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopeModule))
    }
}

val dataModule = module {
    factory { ChampionRepository(get(), get(), get()) }
    factory { RegionRepository(get(), get()) }
}

private val appModule = module {
    single { ChampionDatabase.build(get()) }
    single<CoroutineDispatcher> { Dispatchers.Main }
    factory <LocalDataSource>{ RoomDataSource(get()) }
    factory <RemoteDataSource>{ ChampionDbDataSource() }
    factory <LocationDataSource>{ PlayServicesLocationDataSource(get()) }
    factory <PermissionChecker>{ AndroidPermissionChecker(get()) }
}

private val scopeModule = module {
    scope(named<MainFragment>()){
        viewModel { MainViewModel(get(), get()) }
        scoped { GetChampionsUseCase(get()) }
    }

    scope(named<DetailsFragment>()){
        viewModel { (id: String ) ->DetailViewModel(id, get(), get(), get()) }
        scoped { FindChampionByIdUseCase(get()) }
        scoped { ToggleChampionFavoriteUseCase(get()) }
    }
}