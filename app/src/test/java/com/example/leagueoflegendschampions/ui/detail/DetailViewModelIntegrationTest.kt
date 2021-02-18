package com.example.leagueoflegendschampions.ui.detail

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.data.source.LocalDataSource
import com.example.leagueoflegendschampions.ui.FakeLocalDataSource
import com.example.leagueoflegendschampions.ui.initMockedDi
import com.example.testshare.mockedChampion
import com.example.usecases.FindChampionByIdUseCase
import com.example.usecases.ToggleChampionFavoriteUseCase
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
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
class DetailViewModelIntegrationTest: AutoCloseKoinTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<DetailViewModel.UiModel>
    @Mock
    private lateinit var context: Context

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var fakeLocalDataSource: FakeLocalDataSource

    @Before
    fun setUp() {
        val vmModule = module {
            factory { DetailViewModel(CHAMPION_ID, get(), get(), get()) }
            factory { FindChampionByIdUseCase(get()) }
            factory { ToggleChampionFavoriteUseCase(get()) }
        }
        initMockedDi(vmModule, context = context)
        detailViewModel = get()
        fakeLocalDataSource = get<LocalDataSource>() as FakeLocalDataSource

    }

    @Test
    fun `when init detail viewModel should get champion associated with the passed champion Id from local database`() {
        fakeLocalDataSource.champions = fakeChampions
        detailViewModel.model.observeForever(observer)

        verify(observer).onChanged(DetailViewModel.UiModel(fakeChampions[0]))
    }

    @Test
    fun `when onFavoriteClicked should update champion favorite to true`() {
        fakeLocalDataSource.champions = fakeChampions
        detailViewModel.model.observeForever(observer)

        detailViewModel.onFavoriteClicked()

        runBlocking {
            assertTrue(fakeLocalDataSource.findChampionById(CHAMPION_ID).favorite)
        }
    }

    @Test
    fun `when onFavoriteClicked should update champion favorite to false`() {
        val newFakeChampions = listOf(mockedChampion.copy(id = CHAMPION_ID, favorite = true),
            mockedChampion.copy(id = "Champion 2"),
            mockedChampion.copy(id = "Champion 3"))
        fakeLocalDataSource.champions = newFakeChampions
        detailViewModel.model.observeForever(observer)

        detailViewModel.onFavoriteClicked()

        runBlocking {
            assertFalse(fakeLocalDataSource.findChampionById(CHAMPION_ID).favorite)
        }
    }

    companion object{
        private const val CHAMPION_ID = "Champion 1"
        private val fakeChampions = listOf(mockedChampion.copy(id = CHAMPION_ID),
            mockedChampion.copy(id = "Champion 2"),
            mockedChampion.copy(id = "Champion 3"))
    }
}