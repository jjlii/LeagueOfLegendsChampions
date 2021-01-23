package com.example.leagueoflegendschampions.di

import com.example.data.repository.ChampionRepository
import com.example.usecases.FindChampionByIdUseCase
import com.example.usecases.GetChampionsUseCase
import com.example.usecases.ToggleChampionFavoriteUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun findChampionByIdUseCaseProvider(championRepository: ChampionRepository) =
        FindChampionByIdUseCase(championRepository)

    @Provides
    fun getChampionsUseCase(championRepository: ChampionRepository) =
        GetChampionsUseCase(championRepository)

    @Provides
    fun toggleChampionFavoriteUseCase(championRepository: ChampionRepository) =
        ToggleChampionFavoriteUseCase(championRepository)
}