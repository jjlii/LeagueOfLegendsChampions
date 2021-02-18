package com.example.data.repository

import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import com.example.domain.Champion
import com.example.domain.getLanguage

class ChampionRepository(
        private val repository: RegionRepository,
        private val localDataSource: LocalDataSource,
        private val remoteDataSource: RemoteDataSource
) {
    suspend fun getChampions(): List<Champion>{
        if (localDataSource.championCount()<=0){
            val languages = remoteDataSource.languagesAsync()
            val champions = remoteDataSource.listChampionsAsync(
                getLanguage(repository.findLastRegion(), languages)
            )
            localDataSource.insertChampion(champions)
        }
        return localDataSource.getAllChampions()
    }

    suspend fun findChampionById(id: String) = localDataSource.findChampionById(id)

    suspend fun updateChampion(champion: Champion) = localDataSource.updateChampion(champion)

}