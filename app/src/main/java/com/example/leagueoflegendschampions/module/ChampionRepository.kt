package com.example.leagueoflegendschampions.module

import android.app.Activity

class ChampionRepository(activity: Activity) {

    private val regionRepository = RegionRepository(activity)

    suspend fun getChampions() =
            LolDb.service.listChampionsAsync(
                    Language.getLanguage(regionRepository.getRegionLanguage())
            )

}