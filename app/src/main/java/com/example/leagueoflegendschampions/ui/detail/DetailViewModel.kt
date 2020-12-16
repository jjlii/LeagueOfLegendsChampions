package com.example.leagueoflegendschampions.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.leagueoflegendschampions.module.database.Champion
import com.example.leagueoflegendschampions.module.server.ChampionRepository
import com.example.leagueoflegendschampions.ui.commun.ScopedViewModel
import kotlinx.coroutines.launch

class DetailViewModel(private val championId: String,
                      private val championRepository: ChampionRepository): ScopedViewModel() {

    class UiModel(val champion: Champion)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null)
                findChampion()
            return _model
        }

    private fun findChampion() =
            launch{
                _model.value = UiModel(championRepository.findChampionById(championId))
            }

    fun onFavoriteClicked() = launch {
        _model.value?.champion?.let {
            val updatedChampion = it.copy(favorite = !it.favorite)
            _model.value = UiModel(updatedChampion)
            championRepository.updateChampion(updatedChampion)
        }
    }
}