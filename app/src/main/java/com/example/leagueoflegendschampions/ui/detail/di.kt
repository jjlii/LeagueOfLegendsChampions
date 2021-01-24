package com.example.leagueoflegendschampions.ui.detail

import com.example.data.repository.ChampionRepository
import com.example.usecases.FindChampionByIdUseCase
import com.example.usecases.ToggleChampionFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class DetailActivityModule(private val id: String){
    @Provides
    fun detailViewModelProvider(findChampionByIdUseCase: FindChampionByIdUseCase,
                                toggleChampionFavoriteUseCase: ToggleChampionFavoriteUseCase
    ) =
        DetailViewModel(id, findChampionByIdUseCase, toggleChampionFavoriteUseCase)

    @Provides
    fun findChampionByIdUseCaseProvider(championRepository: ChampionRepository) =
        FindChampionByIdUseCase(championRepository)

    @Provides
    fun toggleChampionFavoriteUseCase(championRepository: ChampionRepository) =
        ToggleChampionFavoriteUseCase(championRepository)

}

@Subcomponent(modules = [DetailViewModel::class])
interface DetailActivityComponent{
    val detailViewModel: DetailViewModel
}