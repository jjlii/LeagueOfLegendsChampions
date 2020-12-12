package com.example.leagueoflegendschampions.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.leagueoflegendschampions.module.server.Champion

class DetailViewModel(private val champion: Champion): ViewModel() {

    class UiModel(val champion: Champion)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null)
                _model.value = UiModel(champion)
            return _model
        }
}