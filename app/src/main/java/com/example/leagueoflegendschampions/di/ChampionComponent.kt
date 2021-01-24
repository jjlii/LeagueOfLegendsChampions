package com.example.leagueoflegendschampions.di

import android.app.Application
import com.example.leagueoflegendschampions.ui.detail.DetailViewModel
import com.example.leagueoflegendschampions.ui.main.MainViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, UseCaseModule::class, ViewModelModule::class])
interface ChampionComponent {

    val mainViewModel : MainViewModel
    val detailViewModel : DetailViewModel

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance app: Application): ChampionComponent
    }

}