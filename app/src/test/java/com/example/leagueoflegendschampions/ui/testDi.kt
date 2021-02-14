package com.example.leagueoflegendschampions.ui

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.PermissionChecker
import com.example.data.repository.ChampionRepository
import com.example.data.repository.RegionRepository
import com.example.data.source.LocalDataSource
import com.example.data.source.LocationDataSource
import com.example.data.source.RemoteDataSource
import com.example.domain.Champion
import com.example.leagueoflegendschampions.data.database.ChampionDao
import com.example.leagueoflegendschampions.data.database.ChampionDatabase
import com.example.leagueoflegendschampions.data.toDomainChampion
import com.example.leagueoflegendschampions.data.toRoomChampion
import com.example.leagueoflegendschampions.dataModule
import com.example.testshare.mockedChampion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initMockedDi(vararg modules: Module, context: Context){
    startKoin {
        androidContext(context)
        modules ( listOf(
            mockedAppModule, dataModule
        ) + modules)
    }
}

val fakeChampionList = listOf(
    mockedChampion.copy(id = "mock1"),
    mockedChampion.copy(id = "mock2"),
    mockedChampion.copy(id = "mock3")
)

private val mockedAppModule = module {
    single { FakeChampionDatabase.build(get()) }
    single { Dispatchers.Unconfined }
    //single<LocalDataSource> { FakeRoomDataSource(get()) }
    single<LocalDataSource> { FakeLocalDataSource() }
    single<RemoteDataSource> { FakeRemoteDataSource() }
    single<LocationDataSource> { FakeLocationDataSource() }
    single<PermissionChecker> { FakePermissionChecker() }
}


class FakeRemoteDataSource : RemoteDataSource{

    override suspend fun listChampionsAsync(language: String): List<Champion> =
        fakeChampionList
}


class FakeLocationDataSource : LocationDataSource{
    var region = "ES"
    override suspend fun findLastRegion(): String? = region
}

class FakePermissionChecker : PermissionChecker {
    var permissionGranted = true

    override suspend fun check(permission: PermissionChecker.Permission): Boolean=
        permissionGranted

}

abstract class FakeChampionDatabase: RoomDatabase(){
    companion object{
        fun build(context: Context) = Room.inMemoryDatabaseBuilder(
            context, FakeChampionDatabase::class.java
        ).build()
    }
    abstract fun championDao(): ChampionDao
}

class FakeRoomDataSource(db: FakeChampionDatabase): LocalDataSource{

    private val dao = db.championDao()

    override suspend fun getAllChampions(): List<Champion> =
        withContext(Dispatchers.Unconfined){
            dao.getAllChampions().map {
                it.toDomainChampion()
            }

        }


    override suspend fun findChampionById(id: String): Champion =
        withContext(Dispatchers.Unconfined){
            dao.findChampionById(id).toDomainChampion()
        }

    override suspend fun championCount(): Int =
        withContext(Dispatchers.Unconfined){
            dao.championCount()
        }

    override suspend fun insertChampion(champions: List<Champion>) {
        withContext(Dispatchers.Unconfined){
            dao.insertChampion(
                champions.map {
                    it.toRoomChampion()
                }
            )
        }
    }

    override suspend fun updateChampion(champion: Champion) {
        withContext(Dispatchers.Unconfined){
            dao.updateChampion(
                champion.toRoomChampion()
            )
        }
    }

}

class FakeLocalDataSource : LocalDataSource {

    var champions: List<Champion> = emptyList()

    override suspend fun getAllChampions(): List<Champion> = champions

    override suspend fun findChampionById(id: String): Champion = champions.first { it.id == id }

    override suspend fun championCount(): Int = champions.size

    override suspend fun insertChampion(champions: List<Champion>)  {this.champions = champions}

    override suspend fun updateChampion(champion: Champion) {
        champions = champions.filterNot {
            it.id == champion.id
        } + champion
    }
}







