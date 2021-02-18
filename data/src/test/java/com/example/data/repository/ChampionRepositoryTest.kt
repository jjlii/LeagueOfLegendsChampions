package com.example.data.repository

import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import com.example.domain.Champion
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ChampionRepositoryTest{

    @Mock
    private lateinit var repository: RegionRepository
    @Mock
    private lateinit var localDataSource: LocalDataSource
    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    private lateinit var championRepository: ChampionRepository

    private val mockedChampion = Champion(
        id = "",
        name = "",
        square = "",
        blurb = "",
        title = "",
        partype = "",
        attack = 0,
        defense = 0,
        magic = 0,
        difficulty = 0,
        tags = listOf(),
        stats = mapOf(),
        favorite = false,
    )

    @Before
    fun setUp() {
        championRepository = ChampionRepository(repository, localDataSource, remoteDataSource)
    }

    @Test
    fun `getChampions should return remoteDataSource champions when localDataSource is empty`() {
        runBlocking {
            val championList = listOf(mockedChampion)
            val languages = listOf("es_ES", "en_US")
            whenever(localDataSource.championCount()).thenReturn(0)
            whenever(repository.findLastRegion()).thenReturn(REGION)
            whenever(remoteDataSource.languagesAsync()).thenReturn(languages)
            whenever(remoteDataSource.listChampionsAsync(any())).thenReturn(championList)
            whenever(localDataSource.getAllChampions()).thenReturn(championList)

            val result = championRepository.getChampions()

            verify(localDataSource).insertChampion(championList)
            assertEquals(championList, result)

        }
    }

    @Test
    fun `getChampions should return localDataSource when is not empty`() {
        runBlocking {
            val championList = listOf(mockedChampion)
            whenever(localDataSource.championCount()).thenReturn(1)
            whenever(localDataSource.getAllChampions()).thenReturn(championList)

            val result = championRepository.getChampions()

            assertEquals(championList, result)
        }
    }

    @Test
    fun `findChampionById called with championId should return champion with passed championId`() {
        runBlocking {
            val championId = "championId"
            val champion  = mockedChampion.copy(id = championId)
            whenever(localDataSource.findChampionById(championId)).thenReturn(champion)

            val result = championRepository.findChampionById(championId)

            assertEquals(champion, result)
        }
    }

    @Test
    fun `updateChampion should update localDataSource champion and return it`() {
        runBlocking {
            val champion = mockedChampion.copy(id = "championId")

            championRepository.updateChampion(champion)

            verify(localDataSource).updateChampion(champion)
        }
    }

    companion object{
        private const val REGION = "es"
    }
}