package com.example.usecases

import com.example.data.repository.ChampionRepository
import com.example.domain.Champion

class FindChampionByIdUseCase(private val championRepository: ChampionRepository) {
    suspend fun invoke(id: String): Champion = championRepository.findChampionById(id)
}