package com.example.leagueoflegendschampions.data.database

import com.example.data.source.LocalDataSource
import com.example.domain.Champion
import com.example.leagueoflegendschampions.data.toDomainChampion
import com.example.leagueoflegendschampions.data.toRoomChampion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: ChampionDatabase): LocalDataSource {

    private val championDao = db.championDao()

    override suspend fun getAllChampions(): List<Champion> =
            withContext(Dispatchers.IO){
                championDao.getAllChampions().map {
                    it.toDomainChampion()
                }
            }

    override suspend fun findChampionById(id: String): Champion =
            withContext(Dispatchers.IO){
                championDao.findChampionById(id).toDomainChampion()
            }

    override suspend fun championCount(): Int =
            withContext(Dispatchers.IO){
                championDao.championCount()
            }

    override suspend fun insertChampion(champions: List<Champion>) {
        withContext(Dispatchers.IO){
            championDao.insertChampion(champions.map { it.toRoomChampion() })
        }
    }

    override suspend fun updateChampion(champion: Champion) {
        withContext(Dispatchers.IO){
            championDao.updateChampion(champion.toRoomChampion())
        }
    }
}