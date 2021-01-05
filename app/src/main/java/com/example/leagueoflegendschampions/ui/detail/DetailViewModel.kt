package com.example.leagueoflegendschampions.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.Champion
import com.example.leagueoflegendschampions.ui.commun.ScopedViewModel
import com.example.usecases.FindChampionByIdUseCase
import com.example.usecases.ToggleChampionFavoriteUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val championId: String,
                      private val findChampionByIdUseCase: FindChampionByIdUseCase,
                      private val toggleChampionFavoriteUseCase: ToggleChampionFavoriteUseCase): ScopedViewModel() {

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
            _champion.value = findChampionByIdUseCase.invoke(championId)
            updateUi()
        }
    }

    fun onFavoriteClicked() = launch {
        champion.value?.let {
            _champion.value = toggleChampionFavoriteUseCase.invoke(it)
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