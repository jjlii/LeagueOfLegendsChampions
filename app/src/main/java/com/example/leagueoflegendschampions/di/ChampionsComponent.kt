package com.example.leagueoflegendschampions.di

import android.app.Application
import com.example.leagueoflegendschampions.ui.detail.DetailActivityComponent
import com.example.leagueoflegendschampions.ui.detail.DetailActivityModule
import com.example.leagueoflegendschampions.ui.main.MainActivityComponent
import com.example.leagueoflegendschampions.ui.main.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface ChampionsComponent {


    fun plus(module: MainActivityModule): MainActivityComponent
    fun plus(module: DetailActivityModule): DetailActivityComponent

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance app: Application): ChampionsComponent
    }
}