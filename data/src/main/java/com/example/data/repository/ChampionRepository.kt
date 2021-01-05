package com.example.data.repository

import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import com.example.domain.Champion
import com.example.domain.Language.Companion.getLanguage

class ChampionRepository(private val remoteDataSource: RemoteDataSource,
                         private val localDataSource: LocalDataSource,
                         private val regionRepository: RegionRepository
) {

    suspend fun getChampions() : List<Champion> {
        if(localDataSource.championCount() <= 0 ){
            val champions = remoteDataSource
                    .listChampionsAsync(getLanguage(regionRepository.findLastLocation()))
            localDataSource.insertChampion(champions)
        }
        return localDataSource.getAllChampions()
    }

    suspend fun findChampionById(id: String): Champion =
            localDataSource.findChampionById(id)

    suspend fun updateChampion(champion: Champion) =
            localDataSource.updateChampion(champion)


}