package com.example.leagueoflegendschampions.framework.server

import com.example.data.source.RemoteDataSource
import com.example.domain.Champion
import com.example.leagueoflegendschampions.framework.toDomainChampion

class ChampionDataSource(): RemoteDataSource {

    override suspend fun listChampionsAsync(language: String): List<Champion> =
            ChampionDb.service.listChampionsAsync(
                    language
            ).data.values.map {
                it.toDomainChampion()
            }
}