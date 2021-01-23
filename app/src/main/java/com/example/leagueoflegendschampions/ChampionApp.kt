package com.example.leagueoflegendschampions

import android.app.Application
import com.example.leagueoflegendschampions.di.ChampionsComponent
import com.example.leagueoflegendschampions.di.DaggerChampionsComponent

class ChampionApp : Application(){

    lateinit var component: ChampionsComponent
        private set



    override fun onCreate() {
        super.onCreate()

        component = DaggerChampionsComponent
                .factory()
                .create(this)
    }
}