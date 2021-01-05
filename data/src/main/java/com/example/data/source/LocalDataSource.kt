package com.example.data.source

import com.example.domain.Champion

interface LocalDataSource {
    suspend fun getAllChampions(): List<Champion>
    suspend fun findChampionById(id: String): Champion
    suspend fun championCount(): Int
    suspend fun insertChampion(champions: List<Champion>)
    suspend fun updateChampion(champion: Champion)
}