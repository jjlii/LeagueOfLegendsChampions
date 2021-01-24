package com.example.leagueoflegendschampions

import android.app.Application
import androidx.room.Room
import com.example.leagueoflegendschampions.data.database.ChampionDatabase
import com.example.leagueoflegendschampions.di.ChampionComponent
import com.example.leagueoflegendschampions.di.DaggerChampionComponent

class ChampionApp : Application(){

    lateinit var component: ChampionComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerChampionComponent
            .factory()
            .create(this)

    }
}