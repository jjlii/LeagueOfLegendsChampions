package com.example.leagueoflegendschampions.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.leagueoflegendschampions.module.server.Champion
import com.example.leagueoflegendschampions.module.server.ChampionRepository
import com.example.leagueoflegendschampions.ui.commun.Event
import com.example.leagueoflegendschampions.ui.commun.Scope
import com.example.leagueoflegendschampions.ui.main.MainViewModel.UiModel.*
import kotlinx.coroutines.launch

class MainViewModel(private val championRepository: ChampionRepository):
    ViewModel(),Scope by Scope.Impl() {

    sealed class UiModel{
        object Loading : UiModel()
        class Content(val champions : List<Champion>) : UiModel()
        object RequestLocationPermission : UiModel()
    }

    private val _navigation = MutableLiveData<Event<Champion>>()
    val navigation: LiveData<Event<Champion>> = _navigation

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

    private fun refresh(){
        _model.value = RequestLocationPermission
    }

    fun onCoarsePermissionRequested() {
        launch {
            _model.value = Loading
            val championList = championRepository.getChampions()
            _model.value = Content(championList.data.values.toList())
        }
    }

    override fun onCleared() {
        cancelScope()
        super.onCleared()
    }

    fun onChampionClick(champion: Champion) {
        _navigation.value = Event(champion)
    }
}