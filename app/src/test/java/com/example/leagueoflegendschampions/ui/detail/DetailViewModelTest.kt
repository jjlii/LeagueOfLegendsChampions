package com.example.leagueoflegendschampions.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.testshare.mockedChampion
import com.example.usecases.FindChampionByIdUseCase
import com.example.usecases.ToggleChampionFavoriteUseCase
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var findChampionByIdUseCase: FindChampionByIdUseCase
    @Mock
    private lateinit var toggleChampionFavoriteUseCase: ToggleChampionFavoriteUseCase
    @Mock

    private lateinit var uiModelObserver: Observer<DetailViewModel.UiModel>

    val champion = mockedChampion.copy(id = CHAMPION_ID)


    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(CHAMPION_ID, findChampionByIdUseCase, toggleChampionFavoriteUseCase, Dispatchers.Unconfined)
    }

    @Test
    fun `when init view model live data champion is null then invoke findChampionByIdUseCase`() {
        runBlocking {
            whenever(findChampionByIdUseCase.invoke(CHAMPION_ID)).thenReturn(champion)

            detailViewModel.model.observeForever(uiModelObserver)

            verify(uiModelObserver).onChanged(DetailViewModel.UiModel(champion))
        }
    }

    @Test
    fun `when on favorite is clicked then invoke toggleChampionFavoriteUseCase`() {
        runBlocking {
            val favoriteChampion = champion.copy(favorite = true)
            whenever(findChampionByIdUseCase.invoke(CHAMPION_ID)).thenReturn(champion)
            whenever(toggleChampionFavoriteUseCase.invoke(champion)).thenReturn(favoriteChampion)

            detailViewModel.model.observeForever(uiModelObserver)
            detailViewModel.onFavoriteClicked()

            verify(uiModelObserver).onChanged(DetailViewModel.UiModel(favoriteChampion))
        }
    }

    companion object{
        private const val CHAMPION_ID = "CHAMPION_ID"
    }
}