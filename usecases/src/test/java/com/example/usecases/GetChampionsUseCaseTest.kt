package com.example.usecases

import com.example.data.repository.ChampionRepository
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetChampionsUseCaseTest{

    @Mock
    private lateinit var championRepository: ChampionRepository

    private lateinit var getChampionsUseCase: GetChampionsUseCase

    @Before
    fun setUp() {
        getChampionsUseCase = GetChampionsUseCase(championRepository)
    }

    @Test
    fun `invoke should call getChampions then return a list of champions`() {
        runBlocking {
            val championsList = listOf(mockedChampion)
            whenever(championRepository.getChampions()).thenReturn(championsList)

            val result = getChampionsUseCase.invoke()

            assertEquals(championsList, result)
        }
    }
}