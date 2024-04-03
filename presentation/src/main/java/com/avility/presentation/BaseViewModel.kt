package com.avility.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<STATE, ACTION>(initialState: STATE) : ViewModel() {

    private val _uiState = mutableStateOf(initialState)
    val uiState: State<STATE> = _uiState

    abstract fun dispatchAction(action: ACTION)

    fun setState(state: STATE) {
        _uiState.value = state
    }
}