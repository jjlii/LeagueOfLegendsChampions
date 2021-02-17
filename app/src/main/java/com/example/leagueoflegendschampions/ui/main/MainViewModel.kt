package com.example.leagueoflegendschampions.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.Champion
import com.example.leagueoflegendschampions.ui.commun.Event
import com.example.leagueoflegendschampions.ui.commun.ScopedViewModel
import com.example.leagueoflegendschampions.ui.main.MainViewModel.UiModel.*
import com.example.usecases.GetChampionsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel(private val getChampionsUseCase: GetChampionsUseCase
                    , uiDispatcher: CoroutineDispatcher):
    ScopedViewModel(uiDispatcher) {

    sealed class UiModel{
        object Loading : UiModel()
        data class Content(val champions : List<Champion>) : UiModel()
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
            _model.value = Content(getChampionsUseCase.invoke())
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