package com.example.leagueoflegendschampions.module.server

import com.example.leagueoflegendschampions.ChampionApp
import com.example.leagueoflegendschampions.module.Language
import com.example.leagueoflegendschampions.module.RegionRepository
import com.example.leagueoflegendschampions.ui.commun.BaseURL
import com.example.leagueoflegendschampions.module.server.Champion as ServerChampion
import com.example.leagueoflegendschampions.module.database.Champion as DbChampion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChampionRepository(application: ChampionApp) {

    private val regionRepository = RegionRepository(application)
    private val db = application.db

    suspend fun getChampions() =
            withContext(Dispatchers.IO){
                with(db.championDao()){
                    if(championCount() <= 0){
                        val champions = LolDb.service.listChampionsAsync(
                                Language.getLanguage(regionRepository.getRegionLanguage())
                        ).data.values.toList()
                        insertChampion(champions.map(ServerChampion::convertToDbChampion))
                    }
                    getAllChampions()
                }
            }

    suspend fun findChampionById(id : String) = withContext(Dispatchers.IO){
        db.championDao().findChampionById(id)
    }

    suspend fun updateChampion(champion: DbChampion) = withContext(Dispatchers.IO){
        db.championDao().updateChampion(champion)
    }
}

private fun ServerChampion.convertToDbChampion() = DbChampion(
        id = id,
        name = name,
        square = BaseURL.SQUARE_BASE_URL + image.full,
        blurb = blurb,
        title = title,
        partype = partype,
        attack = info.attack,
        defense = info.defense,
        magic = info.magic,
        difficulty = info.difficulty,
        tags = tags,
        stats = stats,
        splash = BaseURL.SPLASH_BASE_URL + id + "_0.jpg",
        favorite = false
)