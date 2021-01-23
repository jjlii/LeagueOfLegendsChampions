package com.example.leagueoflegendschampions.framework.database

import com.example.data.source.LocalDataSource
import com.example.domain.Champion
import com.example.leagueoflegendschampions.framework.toDatabaseChampion
import com.example.leagueoflegendschampions.framework.toDomainChampion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDadaSource(championDatabase: ChampionDatabase) : LocalDataSource{

    private val championsDao = championDatabase.championDao()

    override suspend fun getAllChampions(): List<Champion> = withContext(Dispatchers.IO){
        championsDao.getAllChampions().map {
            it.toDomainChampion()
        }
    }


    override suspend fun findChampionById(id: String): Champion = withContext(Dispatchers.IO){
        championsDao.findChampionById(id).toDomainChampion()
    }


    override suspend fun championCount(): Int = withContext(Dispatchers.IO){
        championsDao.championCount()
    }

    override suspend fun insertChampion(champions: List<Champion>) = withContext(Dispatchers.IO){
        championsDao.insertChampion(champions.map { it.toDatabaseChampion() })
    }

    override suspend fun updateChampion(champion: Champion) = withContext(Dispatchers.IO){
        championsDao.updateChampion(champion.toDatabaseChampion())
    }
}