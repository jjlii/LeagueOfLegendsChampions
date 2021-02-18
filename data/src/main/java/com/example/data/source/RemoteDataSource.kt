package com.example.data.source

import com.example.domain.Champion

interface RemoteDataSource {
    suspend fun listChampionsAsync(language: String): List<Champion>

    suspend fun languagesAsync(): List<String>
}