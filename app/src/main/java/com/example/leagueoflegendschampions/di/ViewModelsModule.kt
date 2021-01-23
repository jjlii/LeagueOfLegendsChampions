package com.example.leagueoflegendschampions.di

import com.example.leagueoflegendschampions.ui.detail.DetailViewModel
import com.example.leagueoflegendschampions.ui.main.MainViewModel
import com.example.usecases.FindChampionByIdUseCase
import com.example.usecases.GetChampionsUseCase
import com.example.usecases.ToggleChampionFavoriteUseCase
import dagger.Module
import dagger.Provides

@Module
class ViewModelsModule {

    @Provides
    fun mainViewModelProvider(getChampionsUseCase: GetChampionsUseCase) =
        MainViewModel(getChampionsUseCase)

    @Provides
    fun detailViewModelProvider(findChampionByIdUseCase: FindChampionByIdUseCase,
                                toggleChampionFavoriteUseCase: ToggleChampionFavoriteUseCase) =
        DetailViewModel("", findChampionByIdUseCase, toggleChampionFavoriteUseCase)
}