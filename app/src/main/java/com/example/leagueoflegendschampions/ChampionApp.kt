package com.example.leagueoflegendschampions

import android.app.Application

class ChampionApp : Application(){

    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}