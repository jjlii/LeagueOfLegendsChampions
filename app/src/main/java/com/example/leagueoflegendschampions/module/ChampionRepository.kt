package com.example.leagueoflegendschampions.module

import android.app.Activity
import android.app.Application

class ChampionRepository(application: Application) {

    private val regionRepository = RegionRepository(application)

    suspend fun getChampions() =
            LolDb.service.listChampionsAsync(
                    Language.getLanguage(regionRepository.getRegionLanguage())
            )

}