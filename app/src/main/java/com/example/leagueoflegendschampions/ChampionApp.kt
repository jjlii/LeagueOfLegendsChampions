package com.example.leagueoflegendschampions

import android.app.Application
import androidx.room.Room
import com.example.leagueoflegendschampions.data.database.ChampionDatabase

class ChampionApp : Application(){

    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}