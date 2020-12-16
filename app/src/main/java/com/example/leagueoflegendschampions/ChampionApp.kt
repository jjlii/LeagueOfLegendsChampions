package com.example.leagueoflegendschampions

import android.app.Application
import androidx.room.Room
import com.example.leagueoflegendschampions.module.database.ChampionDatabase

class ChampionApp : Application(){

    lateinit var db : ChampionDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
                this,
                ChampionDatabase::class.java, "champion-db"
        ).build()
    }
}