package com.example.usecases

import com.example.data.repository.ChampionRepository
import com.example.domain.Champion

class GetChampionsUseCase(private val championRepository: ChampionRepository) {

    suspend fun invoke() : List<Champion> =
            championRepository.getChampions()

}