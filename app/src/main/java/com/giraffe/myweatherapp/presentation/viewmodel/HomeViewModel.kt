package com.giraffe.myweatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    val state = MutableStateFlow(HomeUiState())
    private val _state = state.asStateFlow()
}