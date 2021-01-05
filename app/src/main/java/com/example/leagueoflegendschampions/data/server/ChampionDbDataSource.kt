package com.example.leagueoflegendschampions.data.server

import com.example.data.source.RemoteDataSource
import com.example.domain.Champion
import com.example.leagueoflegendschampions.data.toDomainChampion

class ChampionDbDataSource: RemoteDataSource{

    override suspend fun listChampionsAsync(language: String): List<Champion> =
        ChampionsDb.service
                .listChampionsAsync(language)
                .data
                .values
                .map {
                    it.toDomainChampion()
                }

}