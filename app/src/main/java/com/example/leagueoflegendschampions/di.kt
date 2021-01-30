package com.example.leagueoflegendschampions

import android.app.Application
import com.example.data.repository.ChampionRepository
import com.example.data.repository.PermissionChecker
import com.example.data.repository.RegionRepository
import com.example.data.source.LocalDataSource
import com.example.data.source.LocationDataSource
import com.example.data.source.RemoteDataSource
import com.example.leagueoflegendschampions.framework.AndroidPermissionChecker
import com.example.leagueoflegendschampions.framework.PlayServicesLocationDataSource
import com.example.leagueoflegendschampions.framework.database.ChampionDatabase
import com.example.leagueoflegendschampions.framework.database.RoomDadaSource
import com.example.leagueoflegendschampions.framework.server.ChampionDataSource
import com.example.leagueoflegendschampions.ui.detail.DetailActivity
import com.example.leagueoflegendschampions.ui.detail.DetailViewModel
import com.example.leagueoflegendschampions.ui.main.MainActivity
import com.example.leagueoflegendschampions.ui.main.MainViewModel
import com.example.usecases.FindChampionByIdUseCase
import com.example.usecases.GetChampionsUseCase
import com.example.usecases.ToggleChampionFavoriteUseCase
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

private val appModule = module {
    single { ChampionDatabase.build(get()) }
    factory<LocalDataSource> { RoomDadaSource(get()) }
    factory<RemoteDataSource> { ChampionDataSource() }
    factory<LocationDataSource> { PlayServicesLocationDataSource(get()) }
    factory<PermissionChecker> { AndroidPermissionChecker(get()) }
}

private val dataModule = module {
    factory { RegionRepository(get(), get()) }
    factory{ ChampionRepository(get(), get(), get())}
}

private val scopeModule = module {
    scope(named<MainActivity>()){
        viewModel { MainViewModel(get()) }
        scoped { GetChampionsUseCase(get()) }
    }

    scope(named<DetailActivity>()){
        viewModel { (championId: String) -> DetailViewModel(championId, get(), get()) }
        scoped { FindChampionByIdUseCase(get()) }
        scoped { ToggleChampionFavoriteUseCase(get()) }
    }
}

