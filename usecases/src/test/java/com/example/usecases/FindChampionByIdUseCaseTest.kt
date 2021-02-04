package com.example.usecases

import com.example.data.repository.ChampionRepository
import com.nhaarman.mockitokotlin2.capture
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FindChampionByIdUseCaseTest{

    @Mock
    private lateinit var championRepository: ChampionRepository
    @Captor
    private lateinit var argumentCaptor: ArgumentCaptor<String>

    private lateinit var findChampionByIdUseCase: FindChampionByIdUseCase

    @Before
    fun setUp() {
        findChampionByIdUseCase = FindChampionByIdUseCase(championRepository)
    }

    @Test
    fun `invoke should call findChampionById with champion Id then return a champion`() {
        runBlocking {
            val championId = "championId"
            val champion = mockedChampion.copy(id = championId)
            whenever(championRepository.findChampionById(capture(argumentCaptor))).thenReturn(champion)

            val result = findChampionByIdUseCase.invoke(championId)

            assertEquals(championId, argumentCaptor.value)
            assertEquals(champion, result)
        }
    }
}