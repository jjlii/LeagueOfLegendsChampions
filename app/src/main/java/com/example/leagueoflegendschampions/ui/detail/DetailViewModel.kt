package com.example.leagueoflegendschampions.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.leagueoflegendschampions.module.database.Champion
import com.example.leagueoflegendschampions.module.server.ChampionRepository
import com.example.leagueoflegendschampions.ui.commun.ScopedViewModel
import kotlinx.coroutines.launch

class DetailViewModel(private val championId: String,
                      private val championRepository: ChampionRepository): ScopedViewModel() {

    private val _champion = MutableLiveData<Champion>()
    val champion: LiveData<Champion> get() = _champion

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    private val _summary = MutableLiveData<String>()
    val summary: LiveData<String> get() = _summary

    private val _splash = MutableLiveData<String>()
    val splash: LiveData<String> get() = _splash

    private val _favorite = MutableLiveData<Boolean>()
    val favorite: LiveData<Boolean> get() = _favorite

    init {
        launch {
            _champion.value = championRepository.findChampionById(championId)
            updateUi()
        }
    }

    fun onFavoriteClicked() = launch {
        champion.value?.let {
            val updatedChampion = it.copy(favorite = !it.favorite)
            _champion.value = updatedChampion
            updateUi()
            championRepository.updateChampion(updatedChampion)
        }
    }

    private fun updateUi(){
        champion.value?.run {
            _title.value = title
            _summary.value = blurb
            _splash.value = splash
            _favorite.value = favorite
        }
    }
}