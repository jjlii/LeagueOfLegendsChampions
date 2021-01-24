package com.example.data.repository

import com.example.domain.Language.Companion.getLanguage
import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import com.example.domain.Champion

class ChampionRepository(
    private val regionRepository: RegionRepository,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getChampions(): List<Champion>{
        if (localDataSource.championCount()<=0){
            val champions = remoteDataSource.listChampionsAsync(getLanguage(regionRepository.findLastRegion()))
            localDataSource.insertChampion(champions)
        }
        return localDataSource.getAllChampions()
    }

    suspend fun findChampionById(id: String) = localDataSource.findChampionById(id)

    suspend fun updateChampion(champion: Champion) = localDataSource.updateChampion(champion)

}