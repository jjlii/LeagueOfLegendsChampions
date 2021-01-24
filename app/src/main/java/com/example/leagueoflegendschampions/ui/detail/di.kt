package com.example.leagueoflegendschampions.ui.detail

import com.example.data.repository.ChampionRepository
import com.example.usecases.FindChampionByIdUseCase
import com.example.usecases.ToggleChampionFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class DetailFragmentModule(private val championId : String){

    @Provides
    fun detailViewModelProvider(findChampionByIdUseCase: FindChampionByIdUseCase,
                                toggleChampionFavoriteUseCase: ToggleChampionFavoriteUseCase
    ) : DetailViewModel =
        DetailViewModel(championId, findChampionByIdUseCase, toggleChampionFavoriteUseCase)

    @Provides
    fun findChampionByIdUseCaseProvider(championRepository: ChampionRepository) =
        FindChampionByIdUseCase(championRepository)

    @Provides
    fun toggleChampionFavoriteUseCaseProvider(championRepository: ChampionRepository)=
        ToggleChampionFavoriteUseCase(championRepository)

}

@Subcomponent(modules = [DetailFragmentModule::class])
interface DetailFragmentComponent{
    val detailViewModel : DetailViewModel
}