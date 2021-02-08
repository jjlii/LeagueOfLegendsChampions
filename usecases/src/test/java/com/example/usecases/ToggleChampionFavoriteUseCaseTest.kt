package com.example.usecases

import com.example.data.repository.ChampionRepository
import com.example.testshare.mockedChampion
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ToggleChampionFavoriteUseCaseTest{

    @Mock
    private lateinit var championRepository: ChampionRepository

    private lateinit var toggleChampionFavoriteUseCase: ToggleChampionFavoriteUseCase

    @Before
    fun setUp() {
        toggleChampionFavoriteUseCase = ToggleChampionFavoriteUseCase(championRepository)
    }

    @Test
    fun `invoke should call championRepository updateChampion then return champion`() {
        runBlocking {
            val result = toggleChampionFavoriteUseCase.invoke(mockedChampion)

            verify(championRepository).updateChampion(result)
        }
    }

    @Test
    fun `invoke should make a favorite champion became a not favorite champion`() {
        runBlocking {
            val champion = mockedChampion.copy(favorite = true)

            val result = toggleChampionFavoriteUseCase.invoke(champion)

            assertEquals(champion.copy(favorite = false), result)
        }
    }

    @Test
    fun `invoke should make a not favorite champion became a favorite champion`() {
        runBlocking {
            val champion = mockedChampion.copy(favorite = false)

            val result = toggleChampionFavoriteUseCase.invoke(champion)

            assertEquals(champion.copy(favorite = true), result)
        }
    }
}