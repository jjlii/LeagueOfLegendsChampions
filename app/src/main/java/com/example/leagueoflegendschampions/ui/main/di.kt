package com.example.leagueoflegendschampions.ui.main

import com.example.data.repository.ChampionRepository
import com.example.usecases.GetChampionsUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class MainFragmentModule{

    @Provides
    fun mainViewModelProvider(getChampionsUseCase: GetChampionsUseCase) : MainViewModel =
        MainViewModel(getChampionsUseCase)

    @Provides
    fun getChampionUseCaseProvider(championRepository: ChampionRepository) =
        GetChampionsUseCase(championRepository)

}

@Subcomponent(modules = [MainFragmentModule::class])
interface MainFragmentComponent{
    val mainViewModel : MainViewModel
}