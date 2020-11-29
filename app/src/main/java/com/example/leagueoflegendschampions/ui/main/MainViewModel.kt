package com.example.leagueoflegendschampions.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.leagueoflegendschampions.module.Champion
import com.example.leagueoflegendschampions.module.ChampionRepository
import com.example.leagueoflegendschampions.ui.commun.Scope
import kotlinx.coroutines.launch

class MainViewModel(private val championRepository: ChampionRepository):
    ViewModel(),Scope by Scope.Impl() {

    sealed class UiModel{
        object Loading : UiModel()
        class Content(val champions : List<Champion>) : UiModel()
        class Navigation(val champion: Champion) : UiModel()
    }

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get(){
            if (_model.value ==null)
                refresh()
            return _model
        }

    init {
        initScope()
    }

    private fun refresh() {
        launch {
            _model.value = UiModel.Loading
            val championList = championRepository.getChampions()
            _model.value = UiModel.Content(championList.data.values.toList())
        }
    }

    override fun onCleared() {
        cancelScope()
        super.onCleared()
    }

    fun onChampionClick(champion: Champion) {
        _model.value = UiModel.Navigation(champion)
    }
}