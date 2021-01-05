package com.example.usecases

import com.example.data.repository.ChampionRepository
import com.example.domain.Champion


class ToggleChampionFavoriteUseCase(private val championRepository: ChampionRepository) {

    suspend fun invoke(champion: Champion) : Champion =
            with(champion){
                copy(favorite = !favorite )
            }.also {
                championRepository.updateChampion(it)
            }

}