package com.example.leagueoflegendschampions.di

import android.app.Application
import com.example.leagueoflegendschampions.ui.detail.DetailFragmentComponent
import com.example.leagueoflegendschampions.ui.detail.DetailFragmentModule
import com.example.leagueoflegendschampions.ui.main.MainFragmentComponent
import com.example.leagueoflegendschampions.ui.main.MainFragmentModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface ChampionComponent {

    fun plus(mainActivityModule: MainFragmentModule) : MainFragmentComponent
    fun plus(detailFragmentModule: DetailFragmentModule) : DetailFragmentComponent

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance app: Application): ChampionComponent
    }

}