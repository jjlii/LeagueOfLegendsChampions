package com.example.leagueoflegendschampions.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.leagueoflegendschampions.ui.main.MainViewModel.UiModel
import com.example.testshare.mockedChampion
import com.example.usecases.GetChampionsUseCase
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest{


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getChampionsUseCase: GetChampionsUseCase
    @Mock
    private lateinit var observer: Observer<UiModel>

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(getChampionsUseCase, Dispatchers.Unconfined)
    }

    @Test
    fun `when init view model LiveData launches location permission request`() {
        mainViewModel.model.observeForever(observer)

        verify(observer).onChanged(UiModel.RequestLocationPermission)
    }

    @Test
    fun `while requesting data loading should show`() {
        runBlocking {
            val champions = listOf(mockedChampion)
            whenever(getChampionsUseCase.invoke()).thenReturn(champions)
            mainViewModel.model.observeForever(observer)

            mainViewModel.onCoarsePermissionRequested()

            verify(observer).onChanged(UiModel.Loading)
        }
    }

    @Test
    fun `after permission request getChampionUseCase should be invoke`() {
        runBlocking {
            val champions = listOf(mockedChampion)
            whenever(getChampionsUseCase.invoke()).thenReturn(champions)
            mainViewModel.model.observeForever(observer)

            mainViewModel.onCoarsePermissionRequested()


            verify(observer).onChanged(UiModel.Content(champions))
        }
    }
}