package com.example.leagueoflegendschampions.data.server

import com.example.data.source.RemoteDataSource
import com.example.domain.Champion
import com.example.leagueoflegendschampions.data.toDomainChampion

class ChampionDbDataSource(private val championDb: ChampionsDb): RemoteDataSource{

    override suspend fun listChampionsAsync(language: String): List<Champion> =
        championDb.service
            .listChampionsAsync(language)
            .data
            .values
            .map {
                it.toDomainChampion()
            }

}