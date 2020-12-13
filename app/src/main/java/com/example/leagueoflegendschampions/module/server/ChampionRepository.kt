package com.example.leagueoflegendschampions.module.server

import android.app.Application
import com.example.leagueoflegendschampions.module.Language
import com.example.leagueoflegendschampions.module.RegionRepository

class ChampionRepository(application: Application) {

    private val regionRepository = RegionRepository(application)

    suspend fun getChampions() =
            LolDb.service.listChampionsAsync(
                    Language.getLanguage(regionRepository.getRegionLanguage())
            )

}