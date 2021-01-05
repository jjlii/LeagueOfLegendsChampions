package com.example.leagueoflegendschampions.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.Champion
import com.example.leagueoflegendschampions.ui.commun.Event
import com.example.leagueoflegendschampions.ui.commun.ScopedViewModel
import com.example.usecases.GetChampionsUseCase
import kotlinx.coroutines.launch

class MainViewModel(private val getChampionsUseCase: GetChampionsUseCase):
    ScopedViewModel() {

    private val _champions = MutableLiveData<List<Champion>>()
    val champions: LiveData<List<Champion>> get() = _champions

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _requestLocationPermission = MutableLiveData<Event<Unit>>()
    val requestLocationPermission: LiveData<Event<Unit>> get() = _requestLocationPermission

    private val _navigation = MutableLiveData<Event<String>>()
    val navigation: LiveData<Event<String>> = _navigation

    init {
        initScope()
        refresh()
    }

    private fun refresh(){
        _requestLocationPermission.value = Event(Unit)
    }

    fun onCoarsePermissionRequested() {
        launch {
            _loading.value = true
            _champions.value = getChampionsUseCase.invoke()
            _loading.value = false
        }
    }

    override fun onCleared() {
        cancelScope()
        super.onCleared()
    }

    fun onChampionClick(champion: Champion) {
        _navigation.value = Event(champion.id)
    }
}