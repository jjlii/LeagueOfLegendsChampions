package com.example.leagueoflegendschampions.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.Champion
import com.example.leagueoflegendschampions.ui.commun.ScopedViewModel
import com.example.usecases.FindChampionByIdUseCase
import com.example.usecases.ToggleChampionFavoriteUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class DetailViewModel(private val championId: String,
                      private val findChampionByIdUseCase: FindChampionByIdUseCase,
                      private val toggleChampionFavoriteUseCase: ToggleChampionFavoriteUseCase,
                      uiDispatcher: CoroutineDispatcher): ScopedViewModel(uiDispatcher) {

    data class UiModel(val champion: Champion)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null)
                findChampion()
            return _model
        }

    private fun findChampion() =
            launch{
                _model.value = UiModel(
                        findChampionByIdUseCase.invoke(championId)
                )
            }

    fun onFavoriteClicked() = launch {
        _model.value?.champion?.let {
            _model.value = UiModel(toggleChampionFavoriteUseCase.invoke(it))
        }
    }
}