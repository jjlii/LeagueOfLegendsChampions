package com.example.leagueoflegendschampions.ui.main

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.data.source.LocalDataSource
import com.example.leagueoflegendschampions.ui.FakeLocalDataSource
import com.example.leagueoflegendschampions.ui.fakeChampionList
import com.example.leagueoflegendschampions.ui.initMockedDi
import com.example.testshare.mockedChampion
import com.example.usecases.GetChampionsUseCase
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelIntegrationTest: AutoCloseKoinTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<MainViewModel.UiModel>
    @Mock
    private lateinit var context: Context

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        val vmModule = module{
            factory { MainViewModel(get(), get()) }
            factory { GetChampionsUseCase(get()) }
        }
        initMockedDi(vmModule, context = context)
        mainViewModel = get()
    }

    @Test
    fun `when init view model should all permission checker then the permission is granted`() {
        mainViewModel.model.observeForever(observer)

        verify(observer).onChanged(MainViewModel.UiModel.RequestLocationPermission)
    }

    @Test
    fun `when onCoarsePermissionRequested and local database is empty then get champion from remote database`() {
        mainViewModel.model.observeForever(observer)

        mainViewModel.onCoarsePermissionRequested()

        verify(observer).onChanged(MainViewModel.UiModel.Loading)
        verify(observer).onChanged(MainViewModel.UiModel.Content(fakeChampionList))
    }

    @Test
    fun `when onCoarsePermissionRequested and local database is not empty then get champion from local database `() {
        val fakeChampions = listOf(mockedChampion.copy(id = "champion 1"),
            mockedChampion.copy(id = "champion 2"))
        val fakeLocalDataSource = get<LocalDataSource>() as FakeLocalDataSource
        fakeLocalDataSource.champions = fakeChampions

        mainViewModel.model.observeForever(observer)
        mainViewModel.onCoarsePermissionRequested()

        verify(observer).onChanged(MainViewModel.UiModel.Loading)
        verify(observer).onChanged(MainViewModel.UiModel.Content(fakeChampions))
    }
}