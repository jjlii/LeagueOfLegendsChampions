package com.example.leagueoflegendschampions.ui.main

import com.example.data.repository.ChampionRepository
import com.example.usecases.GetChampionsUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class MainActivityModule{


    @Provides
    fun mainViewModelProvider(getChampionsUseCase: GetChampionsUseCase) =
            MainViewModel(getChampionsUseCase)

    @Provides
    fun getChampionsUseCase(championRepository: ChampionRepository) =
            GetChampionsUseCase(championRepository)


}

@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent{
    val mainViewModel: MainViewModel
}